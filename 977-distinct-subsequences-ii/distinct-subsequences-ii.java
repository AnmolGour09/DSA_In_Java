class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;
        long[] end = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            long newCount = (total + 1) % MOD;
            total = (total - end[c] + newCount + MOD) % MOD;
            end[c] = newCount;
        }

        return (int) total;
    }
}
