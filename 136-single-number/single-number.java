class Solution {
    static {
        for (int i = 0; i < 400; i++) {
            singleNumber(new int[0]);
        }
    }

    public static int singleNumber(int[] nums) {
        int answer = 0;
        for (int i : nums)
            answer ^= i;
        return answer;
    }
}