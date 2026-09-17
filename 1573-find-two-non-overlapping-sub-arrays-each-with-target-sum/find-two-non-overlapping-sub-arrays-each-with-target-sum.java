class Solution {
    int ans = Integer.MAX_VALUE;

    public int minSumOfLengths(int[] nums, int target) {
        int n = nums.length;
        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            best[i] = Integer.MAX_VALUE;
        }

        solve(nums, target, 0, 0, 0, best);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void solve(int[] nums, int target,
                       int left, int right, long sum,
                       int[] best) {

        if (right == nums.length) {
            return;
        }

        sum += nums[right];

        while (left <= right && sum > target) {
            sum -= nums[left];
            left++;
        }

        if (sum == target) {
            int len = right - left + 1;

            if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                ans = Math.min(ans, len + best[left - 1]);
            }

            best[right] = len;
        }

        if (right > 0) {
            best[right] = Math.min(best[right], best[right - 1]);
        }

        solve(nums, target, left, right + 1, sum, best);
    }
}

