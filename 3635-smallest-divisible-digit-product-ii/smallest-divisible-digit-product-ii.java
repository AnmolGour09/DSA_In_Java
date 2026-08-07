import java.util.*;

class Solution {
    int A, B, C, D;
    int[] e2 = new int[10], e3 = new int[10], e5 = new int[10], e7 = new int[10];

    public String smallestNumber(String num, long t) {
        long tt = t;
        A = 0; while (tt % 2 == 0) { tt /= 2; A++; }
        B = 0; while (tt % 3 == 0) { tt /= 3; B++; }
        C = 0; while (tt % 5 == 0) { tt /= 5; C++; }
        D = 0; while (tt % 7 == 0) { tt /= 7; D++; }
        if (tt != 1) return "-1";

        for (int d = 1; d <= 9; d++) {
            int x = d, a=0,b=0,c=0,dd=0;
            while (x % 2 == 0) { x/=2; a++; }
            while (x % 3 == 0) { x/=3; b++; }
            while (x % 5 == 0) { x/=5; c++; }
            while (x % 7 == 0) { x/=7; dd++; }
            e2[d]=a; e3[d]=b; e5[d]=c; e7[d]=dd;
        }

        char[] digits = num.toCharArray();
        int n = digits.length;

        // suffixMin[i] = smallest suffix string of length (n-i) (zero-free) achieving 
        // required exponents starting from (0,0,0,0), stored as best achievable capped exponents reduction
        // We compute, for a needed (a,b,c,d) capped state, the smallest free suffix of a GIVEN length iteratively.
        // Since exact exponents needed vary by prefix choice, precompute a generic greedy table:
        // bestSuffix[len] = array mapping capped state -> digit string, too large in general.
        //
        // Instead: since digit contributions are bounded (max exponent from a single digit is small),
        // and required A,B,C,D are bounded (A<=46,B<=29,C<=20,D<=17), we do iterative DP over length
        // using only reachable states, with arrays sized by (A+1)*(B+1)*(C+1)*(D+1).

        int capA = A, capB = B, capC = C, capD = D;
        int SA = capA+1, SB = capB+1, SC = capC+1, SD = capD+1;
        int totalStates = SA*SB*SC*SD;

        // canAchieve[len % 2][state] = true if length `len` suffix can achieve remaining state fully (reduce to 0)
        // bestDigit[len][state] = smallest first digit achieving it (for reconstruction) -- but storing per length is O(n*states), too much memory for n=2e5 and states up to ~47*30*21*18 ~ 532,000.
        //
        // Key insight: once a state's exponents are all 0, it's satisfied for ANY further length (use digit 1s).
        // So define minLenToSatisfy[state] = minimum suffix length needed to reduce `state` to all-zero.
        // This is small: at most needed is bounded by max(A,B,C,D) roughly (using digit 8 for 2s, 9 for 3s, 5 for 5s, 7 for 7s)
        // Compute via BFS/DP over states (not over length!) since state space is small.

        int[] minLen = new int[totalStates];
        Arrays.fill(minLen, -1);
        int zeroState = 0;
        minLen[zeroState] = 0;

        // BFS from zero state backwards: minLen[s] = 1 + min over digit of minLen[s'] where s' = s reduced by digit
        // Forward BFS: process states in increasing total-length order using 0-1 style multi-source BFS
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(zeroState);
        // We need reverse transition: from state s, applying digit dg gives new state s2 (closer to zero).
        // We want minLen[s] = min steps to reach zero via forward digit application.
        // So do BFS from zero using REVERSE edges: edge dg goes from s2 -> s (adding requirement) which is hard to invert cleanly
        // Simpler: standard forward DP by increasing distance (BFS layers) using state transition function directly.
        
        // Do multi-source-like Dijkstra/BFS: since all edges have weight 1, do BFS where we expand state s 
        // by CONSUMING a digit to move toward zero, i.e., transition(s, dg) = max(0, exponent-reduction).
        // We want minLen[s] = shortest path from s to zeroState using edges s -> transition(s,dg).
        // BFS from s using standard forward exploration for ALL states is O(states*9), fine.
        
        int[] dist = new int[totalStates];
        Arrays.fill(dist, -1);
        dist[zeroState] = 0;
        ArrayDeque<Integer> bfsQ = new ArrayDeque<>();
        bfsQ.add(zeroState);
        
        // Precompute encode/decode
        // state = ((a*SB)+b)*SC+c)*SD+d
        
        // Build reverse adjacency implicitly: for BFS from zeroState using reverse edges,
        // reverse of (s -> s' via digit dg) is (s' -> s via digit dg) meaning s = "s' plus digit dg's exponents, capped"
        // So from s', predecessors s are: for each digit dg, s = increase(s', dg) capped at (A,B,C,D)
        while (!bfsQ.isEmpty()) {
            int cur = bfsQ.poll();
            int d0 = cur % SD;
            int rem = cur / SD;
            int c0 = rem % SC;
            rem /= SC;
            int b0 = rem % SB;
            int a0 = rem / SB;
            
            for (int dg = 1; dg <= 9; dg++) {
                int na = Math.min(capA, a0 + e2[dg]);
                int nb = Math.min(capB, b0 + e3[dg]);
                int nc = Math.min(capC, c0 + e5[dg]);
                int nd = Math.min(capD, d0 + e7[dg]);
                int pred = ((na*SB+nb)*SC+nc)*SD+nd;
                if (dist[pred] == -1) {
                    dist[pred] = dist[cur] + 1;
                    bfsQ.add(pred);
                }
            }
        }
        // dist[state] now = minimum number of digits needed to reduce `state` down to zero
        
        // For reconstruction of smallest suffix achieving a given state in exactly `length` digits (length >= dist[state]),
        // greedily pick smallest digit dg such that dist[newState] <= length-1, then recurse.
        
        StringBuilder sb = new StringBuilder();
        boolean[] tightBroken = {false};
        
        // Try tight path
        int a = A, b = B, c = C, d = D;
        boolean success = false;
        
        for (int i = 0; i < n; i++) {
            int origDigit = digits[i] - '0';
            int remain = n - i - 1;
            int curState = ((a*SB+b)*SC+c)*SD+d;
            
            boolean placed = false;
            
            if (origDigit >= 1) {
                int na = Math.max(0, a - e2[origDigit]);
                int nb = Math.max(0, b - e3[origDigit]);
                int nc = Math.max(0, c - e5[origDigit]);
                int nd = Math.max(0, d - e7[origDigit]);
                int newState = ((na*SB+nb)*SC+nc)*SD+nd;
                if (dist[newState] != -1 && dist[newState] <= remain) {
                    // check exact feasibility (dist gives min length; any length >= dist works since we can pad with '1's after satisfied... 
                    // but '1' doesn't change state so it's fine to pad after satisfied)
                    sb.append((char)('0'+origDigit));
                    a=na; b=nb; c=nc; d=nd;
                    placed = true;
                }
            }
            
            if (!placed) {
                int startDigit = Math.max(origDigit+1, 1);
                for (int dg = startDigit; dg <= 9; dg++) {
                    int na = Math.max(0, a - e2[dg]);
                    int nb = Math.max(0, b - e3[dg]);
                    int nc = Math.max(0, c - e5[dg]);
                    int nd = Math.max(0, d - e7[dg]);
                    int newState = ((na*SB+nb)*SC+nc)*SD+nd;
                    if (dist[newState] != -1 && dist[newState] <= remain) {
                        sb.append((char)('0'+dg));
                        a=na; b=nb; c=nc; d=nd;
                        // fill rest greedily using free reconstruction
                        appendFreeSuffix(sb, remain, a, b, c, d, dist, SA, SB, SC, SD, capA, capB, capC, capD, e2,e3,e5,e7);
                        placed = true;
                        success = true;
                        break;
                    }
                }
                if (placed) break;
                else {
                    // backtrack
                    return backtrackAndBuild(digits, i, dist, SA, SB, SC, SD, capA, capB, capC, capD, e2,e3,e5,e7, A,B,C,D);
                }
            }
        }
        
        if (!success) {
            // exact match through entire string worked
            if (a==0 && b==0 && c==0 && d==0) {
                return sb.toString();
            } else {
                return backtrackAndBuild(digits, n, dist, SA, SB, SC, SD, capA, capB, capC, capD, e2,e3,e5,e7, A,B,C,D);
            }
        }
        
        return sb.toString();
    }
    
    // Backtrack from position pos-1 upward to find an earlier position to increment
    String backtrackAndBuild(char[] digits, int failPos, int[] dist, int SA,int SB,int SC,int SD,
                              int capA,int capB,int capC,int capD,int[] e2,int[] e3,int[] e5,int[] e7,
                              int A,int B,int C,int D) {
        int n = digits.length;
        // Recompute prefix states up to each position by simulating original digits (must recompute from scratch using original digits)
        // We need cumulative required-remaining state at each prefix position using ORIGINAL digits (tight path).
        int[] aArr = new int[n+1], bArr = new int[n+1], cArr = new int[n+1], dArr = new int[n+1];
        aArr[0]=A; bArr[0]=B; cArr[0]=C; dArr[0]=D;
        for (int i = 0; i < n; i++) {
            int dg = digits[i]-'0';
            if (dg == 0) { 
                // shouldn't happen since num has no leading zero and this loop only used up to failPos where all were valid tight matches
            }
            aArr[i+1] = Math.max(0, aArr[i]-e2[dg]);
            bArr[i+1] = Math.max(0, bArr[i]-e3[dg]);
            cArr[i+1] = Math.max(0, cArr[i]-e5[dg]);
            dArr[i+1] = Math.max(0, dArr[i]-e7[dg]);
        }
        
        for (int pos = Math.min(failPos, n) - 1; pos >= 0; pos--) {
            int origDigit = digits[pos]-'0';
            int remain = n - pos - 1;
            int a = aArr[pos], b = bArr[pos], c = cArr[pos], d = dArr[pos];
            int startDigit = origDigit+1;
            for (int dg = startDigit; dg <= 9; dg++) {
                int na = Math.max(0, a - e2[dg]);
                int nb = Math.max(0, b - e3[dg]);
                int nc = Math.max(0, c - e5[dg]);
                int nd = Math.max(0, d - e7[dg]);
                int newState = ((na*SB+nb)*SC+nc)*SD+nd;
                if (dist[newState] != -1 && dist[newState] <= remain) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(digits, 0, pos);
                    sb.append((char)('0'+dg));
                    appendFreeSuffix(sb, remain, na, nb, nc, nd, dist, SA,SB,SC,SD,capA,capB,capC,capD,e2,e3,e5,e7);
                    return sb.toString();
                }
            }
        }
        
        // No same-length solution; try increasing length
        int len = n + 1;
        while (len <= n + 60) {
            int state0 = ((A*SB+B)*SC+C)*SD+D;
            if (dist[state0] != -1 && dist[state0] <= len) {
                StringBuilder sb = new StringBuilder();
                appendFreeSuffix(sb, len, A, B, C, D, dist, SA,SB,SC,SD,capA,capB,capC,capD,e2,e3,e5,e7);
                return sb.toString();
            }
            len++;
        }
        return "-1";
    }
    
    void appendFreeSuffix(StringBuilder sb, int length, int a, int b, int c, int d, int[] dist,
                           int SA,int SB,int SC,int SD,int capA,int capB,int capC,int capD,
                           int[] e2,int[] e3,int[] e5,int[] e7) {
        for (int pos = 0; pos < length; pos++) {
            int remain = length - pos - 1;
            for (int dg = 1; dg <= 9; dg++) {
                int na = Math.max(0, a - e2[dg]);
                int nb = Math.max(0, b - e3[dg]);
                int nc = Math.max(0, c - e5[dg]);
                int nd = Math.max(0, d - e7[dg]);
                int newState = ((na*SB+nb)*SC+nc)*SD+nd;
                if (dist[newState] != -1 && dist[newState] <= remain) {
                    sb.append((char)('0'+dg));
                    a=na; b=nb; c=nc; d=nd;
                    break;
                }
            }
        }
    }
}