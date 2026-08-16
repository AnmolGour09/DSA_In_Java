class Solution {
    public int findMin(int[] a) {
        int p=pivot(a);
        if(p>=0&&a[p]>a[p+1])
        {
            return a[p+1];
        }
        else{
            return a[0];
        }
    }
    public static int pivot(int[] a)
    {
        int n=a.length;
        int s=0;
        int e=n-1;
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(mid<e && a[mid]>a[mid+1])// case 1:- [7>0] if a[mid]=7
            {
                return mid;
            }
            if(mid>s && a[mid]<a[mid-1])// case 2:-[0<7] if a[mid]=0
            {
                return mid-1;
            }
            if(a[mid]==a[s]&&a[mid]==a[e])
            {
                if(s<e && a[s]>a[s+1])
                {
                    return s;
                }
                s++;

                if(s<e && a[e]<a[e-1])
                {
                    return e-1;
                }
                e--;
            }else{
                if(a[mid]>a[e])
                {
                    s=mid+1;
                }
                else{
                    e=mid-1;
                }
            }
        }
        return -1;
    }
}