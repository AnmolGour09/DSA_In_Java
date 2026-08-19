class Solution {
    public int splitArray(int[] a, int k) {
        int s=0;
        int e=0;
        for(int i=0;i<a.length;i++)
        {
            s=Math.max(s,a[i]);
            e+=a[i];
        }

        while(s<e)
        {
            int mid=s+(e-s)/2;
            int sum=0;
            int p=1;
            for(int i=0;i<a.length;i++)
            {
                if(sum+a[i]>mid)
                {
                    sum=a[i];
                    p++;
                }
                else{
                    sum+=a[i];
                }
            }

            if(p>k)
            {
                s=mid+1;
            }
            else{
                e=mid;
            }
        }
        return e;

    }
}