class Solution {
    public int countNegatives(int[][] a) {
        int c=0;
        for(int i=0;i<a.length;i++)
        {
            Arrays.sort(a[i]);
            if(a[i][0]<0)
            {
                for(int j=0;j<a[i].length;j++)
                {
                    if(a[i][j]<0)
                    {
                        c++;
                    }
                }
            }
        }
        return c;
        
    }
}