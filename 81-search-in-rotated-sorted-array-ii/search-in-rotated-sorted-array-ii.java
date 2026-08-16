class Solution {
    public boolean search(int[] a, int t) {		
	
	    int p=pivot(a);
        if(a[0]==t)
        {
            return true;
        }
        int f=binary(a,p,t);
        if(f!=-1)
        {
            return true;
        }
        int s=binary2(a,p,t);
        if(s!=-1)
        {
            return true;
        }
        return false;

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
