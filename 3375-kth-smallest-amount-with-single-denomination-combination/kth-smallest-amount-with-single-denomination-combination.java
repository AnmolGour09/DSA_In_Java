class Solution {
    int[] coins;
    long k;

    public long findKthSmallest(int[] coins, long k) {
        this.coins = coins;
        this.k = k;

        long lo = 1, hi = (long) coins[0] * k;

        for (int c : coins)
            hi = Math.min(hi, (long) c * k);

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (count(mid) >= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    long count(long x) {
        long ans = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);

                    if (lcm > x) break;
                }
            }

            if (lcm <= x) {
                if (bits % 2 == 1)
                    ans += x / lcm;
                else
                    ans -= x / lcm;
            }
        }

        return ans;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}