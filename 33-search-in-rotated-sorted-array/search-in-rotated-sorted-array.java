class Solution {
    public int search(int[] a, int t) {
        int n=a.length;
        int p=peak(a);
        int f=binary(a,t,p);
        if(f!=-1)
        { 
            return f;
        }
        else
        {
            int s=binary2(a,t,p);
            if(s!=-1)
            {
                return s;
            }
        }

        return -1;        
    }

    public static int peak(int[] a)
    {
        int s=0;
        int e=a.length-1;
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

    public static int binary(int[] a,int t, int p)
    {
        int s = 0;
        int e = a.length - 1;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (a[mid] == t) {
                return mid;
            }

            if (a[s] <= a[mid]) {

                if (a[s] <= t && t < a[mid]) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }

            }
            else {

                if (a[mid] < t && t <= a[e]) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int binary2(int[] a,int t, int p)
    {
        int s=p+1;
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