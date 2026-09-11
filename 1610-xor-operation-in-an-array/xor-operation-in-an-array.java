class Solution {
    public int xorOperation(int n, int s) {
        int[] nums= new int[n];
        for(int i=0;i<n;i++)
        {
            nums[i]=s+2*i;
        }
        int un=0;
        for(int a:nums)
        {
            un^=a;
        }
        return un;
    }
}