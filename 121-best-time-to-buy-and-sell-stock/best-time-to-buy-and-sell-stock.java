class Solution {
    public int maxProfit(int[] a) {
        int minp=a[0];
        int max=0;
        for(int i=1;i<a.length;i++)
        {
            int p=a[i]-minp;
            if(p>max)
            {
                max=p;
            }
            if(a[i]<minp)
            {
                minp=a[i];
            }
        }
        return max;
    }
}
