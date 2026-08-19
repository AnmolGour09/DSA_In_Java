class Solution {
    public int hIndex(int[] a) {
        int n=a.length;
        int s=0;
        int e=n-1;
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]>=n-mid)
            {
                e=mid-1;
            }
            else{
                s=mid+1;
            }
        }
        return n-s;
        
    }
}