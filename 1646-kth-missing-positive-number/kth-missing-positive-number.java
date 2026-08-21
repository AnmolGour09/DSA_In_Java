class Solution {
    public int findKthPositive(int[] a, int k) {
        int n=a.length;
        int s=0;
        int e=n;

        while(s<e)
        {
            int mid=s+(e-s)/2;
            int miss=a[mid]-(mid+1);
            if(miss<k)
            {
                s=mid+1;
            }
            else{
                e=mid;
            }
        }
        return s+k;
    }
}