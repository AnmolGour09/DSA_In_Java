class Solution {
    public char nextGreatestLetter(char[] a, char t) 
    {

        int n=a.length;
        int s=0;
	    int e=n-1;
	    while(s<=e)
        {
            int mid=s+(e-s)/2;
            if(a[mid]<=t)
            {
                s=mid+1;
	        }
            else{
                e=mid-1;
            }
        }
	    if(s<n)
        {
	        return a[s];
	    }
        return a[0];
    }
}