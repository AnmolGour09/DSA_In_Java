class Solution {
    public int[] countBits(int n) {
        int[] a=new int[n+1];
        for(int i=0;i<=n;i++)
        {
            int j=i;
            int c=0;

                while(j>0)
                {
                    if((j&1)==1)
                    {
                        c++;
                    }
                    j>>=1;
                }
            a[i]=c;
        }
        return a;
        
    }
}