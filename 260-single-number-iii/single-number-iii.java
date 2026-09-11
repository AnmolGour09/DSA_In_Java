class Solution {
    public int[] singleNumber(int[] nums) {

        int un = 0;

        for (int n : nums) {
            un ^= n;
        }

        int bit = un & -un;

        int a = 0;
        int b = 0;

        for (int n : nums) {

            if ((n & bit) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }

        return new int[]{a, b};
    }
}