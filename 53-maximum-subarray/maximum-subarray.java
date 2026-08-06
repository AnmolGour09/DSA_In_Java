class Solution {
    public int maxSubArray(int[] nums) {
        int maxsum=-100000;
        int current=0;
        for(int i=0;i<nums.length;i++)
        {
            current+=nums[i];
            if(maxsum<current)
            {
                maxsum=current;
            }

            if(current<0)
            {
                current=0;
            }            
        }
        return maxsum;
    }
}