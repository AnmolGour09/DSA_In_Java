class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] f=new int[101];
        int c=0;
        for(int i:nums)
        {
            c+=f[i];
            f[i]++;
        }
        return c;
    }
}