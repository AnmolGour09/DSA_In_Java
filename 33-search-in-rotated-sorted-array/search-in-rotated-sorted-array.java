class Solution 
{
    public int search(int[] a, int t) 
    {
        int p=peak(a);
        int f=binary(a,p,t);
        if(f!=-1)
        {
            return f;
        }
        int s=binary2(a,p,t);
        if(s!=-1)
        {
            return s;
        }

        return -1;
    }

    public static int peak(int[] a)
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
            if(a[mid]<=a[s])  //[4,5,6,7,0,1,2] like a[mid]=0 and a[s]=7 then remove that extra right side of arrya because peak is lie in left side of the array
            {
                e=mid-1;
            }
            else{
                s=mid+1;
            }
        }
        return -1;
    }

    public static int binary(int[] a, int p, int  t)
    {
        int s=0;
        int e=p;
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
    public static int binary2(int[] a, int p, int  t)
    {
        int n=a.length;
        int s=p+1;
        int e=n-1;
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