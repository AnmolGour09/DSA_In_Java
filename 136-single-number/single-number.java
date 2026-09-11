class Solution {
    public int singleNumber(int[] nums) {
        int un=0;
        for(int n: nums)
        {
            un^=n;
        }
        return un;
    }
}