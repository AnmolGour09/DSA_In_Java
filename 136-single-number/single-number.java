class Solution {
    public int singleNumber(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int p=nums[0];
        int c=0;
        for(int num: nums)
        {
            if(num == p)
            {
                c++;
            }
            else{
                if(c==1)
                {
                    return p;
                }
                p=num;
                c=1;
            }
            
        }
        return p;
    }
}