/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int t, MountainArray a) {
        int p=peak(a);
        int f=binarya(a,t,p);
        if(f!=-1) return f;
        int s=binaryd(a,t,p);
        if(s!=-1) return s;

        return -1;
    }
    public static int peak(MountainArray a)
    {
        int n=a.length();
        int s=0;
        int e=n-1;
        while(s<e)
        {
            int m=s+(e-s)/2;
            if(a.get(m)>a.get(m+1))
            {
                e=m;
            }
            else{
                s=m+1;
            }
        }
        return s;
    }

    public static int binarya(MountainArray a,int t, int p)
    {
        int s=0;
        int e=p;
        while(s<=e)
        {
            int m=s+(e-s)/2;
            if(a.get(m)==t)
            {
                return m;
            }
            else if(a.get(m)<t)
            {
                s=m+1;
            }
            else{
                e=m-1;
            }
        }
        return -1;
    }

    public static int binaryd(MountainArray a,int t, int p)
    {
        int n=a.length();
        int s=p;
        int e=n-1;
        while(s<=e)
        {
            int m=s+(e-s)/2;
            if(a.get(m)==t)
            {
                return m;
            }
            else if(a.get(m)>t)
            {
                s=m+1;
            }
            else{
                e=m-1;
            }
        }
        return -1;
    }
    
}