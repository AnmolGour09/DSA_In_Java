class Solution {
    public int search(int[] a, int t) {
        int s=0;
        int e=a.length-1;
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]==t)
            {
                return mid;
            }
            else if(a[mid]<t)
            {
                s=mid+1;
            }
            else{
                e=mid-1;
            }
        }
        return -1;
        
    }
}