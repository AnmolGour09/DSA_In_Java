class Solution {
    public boolean isGoodArray(int[] nums) {
        int n=nums.length;
        int res=nums[0];
        for(int i=0;i<n;i++)
        {
            res=gcd(res,nums[i]);
        }
        return res==1;
    }

    public int gcd(int a,int b)
    {
        if(a==0)
        {
            return b;
        }
        else{
            return gcd(b%a,a);
        }
    }

}