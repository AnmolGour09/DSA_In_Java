class Solution {
    public int minCostToMoveChips(int[] a) {
        int n=a.length;
        int o=0;
        int e=0;
        for(int i=0;i<n;i++)
        {
            if(a[i]%2==0)
            {
                e++;
            }
            else{
                o++;
            }
        }
        
        if(o>e)
        {
            return e;
        }
        else
        {
            return o;
        }
        
    }
}