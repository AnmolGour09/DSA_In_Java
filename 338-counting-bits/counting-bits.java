class Solution {
    public int[] countBits(int n) {
        int[] a=new int[n+1];
        for(int i=0;i<=n;i++)
        {
            int j=i;
            int c=0;

                while(j>0)
                {
                    c++;
                    j-=(j&-j);
                }
            a[i]=c;
        }
        return a;
        
    }
}