class Solution {
    public int findPeakElement(int[] a) {
        int n=a.length;
        int s=0;
        int e=n-1;
        while(s<e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]>a[mid+1])
            {
                e=mid;
            }
            else{
                s=mid+1;
            }
        }
        return s;
    }
}