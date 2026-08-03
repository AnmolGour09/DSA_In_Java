class Solution {
    public int largestAltitude(int[] gain) {
        int c=0;
        int max=0;
        int t=0;
        for(int i=0;i<gain.length;i++)
        {
            t+=gain[i];
            if(max<t)
            {
                max=t;
            }
        }
        return max;
        
    }
}