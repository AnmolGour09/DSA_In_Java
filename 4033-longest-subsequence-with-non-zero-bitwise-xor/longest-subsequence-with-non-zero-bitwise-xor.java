class Solution {
    public int longestSubsequence(int[] a) {
        int n=a.length;
        int x=0;
        boolean h=false;
        for(int num:a)
        {
            x^=num;
            if(x!=0){
                h=true;
            }
        }
        if(x!=0)
        {
            return n;
        }

        if(h)
        {
           return n-1;
        }

        return 0;
    }
}