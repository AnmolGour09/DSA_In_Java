class Solution {
    public int findNumbers(int[] nums) {
        int[] a=new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            a[i]=String.valueOf(nums[i]).length();
        }
        int c=0;
        for(int i=0;i<nums.length;i++)
        {
            if(a[i]%2==0)
            {
                c++;
            }
        }
        return c;
    }
}