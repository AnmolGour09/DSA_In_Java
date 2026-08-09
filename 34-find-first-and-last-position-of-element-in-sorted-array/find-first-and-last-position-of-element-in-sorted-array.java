class Solution {
    public int[] searchRange(int[] a, int t) {
        

        int l=-1;
        int f=-1;
        int n=a.length;
        int s=0;
        int e=n-1;
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]==t)
            {
                f=mid;
                e=mid-1;
            }
            else if(a[mid]<t)
            {
                s=mid+1;
            }
            else{
                e=mid-1;
            }
        }
        s=0;
        e=n-1;
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]==t)
            {
                l=mid;
                s=mid+1;
            }
            else if(a[mid]<t)
            {
                s=mid+1;
            }
            else{
                e=mid-1;
            }
        }

        return new int[]{f,l};
    }
}