class Solution {
    public int search(int[] a, int t) {
    int p = 0;

    for (int i = 1; i < a.length; i++) {
        if (a[i] > a[p]) {
            p = i;
        }
    }
    int f = binary(a, t, p);
    if (f != -1) {
        return f;
    }

    int s = binary2(a, t, p);
    if (s != -1) {
        return s;
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
        int n=a.length;
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

    public static int binary2(int[] a,int t, int p)
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