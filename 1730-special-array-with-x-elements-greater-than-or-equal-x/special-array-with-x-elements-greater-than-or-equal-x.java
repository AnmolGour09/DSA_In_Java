class Solution {
    public int specialArray(int[] a) {
        for(int i=0;i<=a.length;i++)
        {
            int c=0;
            for(int j=0;j<a.length;j++)
            {
                if(a[j]>=i)
                {
                    c++;
                }
            }

            if(c==i)
            {
                return c;
            }
        }
        return -1;
        
    }
}