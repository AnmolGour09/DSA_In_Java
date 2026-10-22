class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int inc = -1, dec = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                inc = i;
            }
            if (nums[i] == n - 1) {
                dec = i;
            }
        }

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[(inc + i) % n] != i) {
                increasing = false;
            }
            if (nums[(dec + i) % n] != n - 1 - i) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            ans = Math.min(inc, n - inc + 2);
        }

        if (decreasing) {
            ans = Math.min(ans, Math.min(dec + 1, n - dec + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}