class Solution {
    public int duplicateNumbersXOR(int[] nums) {
    int c=0;
    int un=0;
    boolean[] seen=new boolean[51];
    for(int n: nums)
    {
        if(seen[n])
        {
            c^=n;
        }
        seen[n]=true;
    }
    return c;
        
    }
}