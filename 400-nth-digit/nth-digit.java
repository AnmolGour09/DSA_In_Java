class Solution {
    public int findNthDigit(int n) {
        long d=1;
        long c=9;
        long s=1;
        while(n>d*c)
        {
            n-=d*c;
            d++;
            c*=10;
            s*=10;
            

        }

        long num=s+(n-1)/d;
        int in=(int)((n-1)%d);

        return String.valueOf(num).charAt(in)-'0';
    }
}