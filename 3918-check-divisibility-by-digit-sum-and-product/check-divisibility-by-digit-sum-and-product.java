class Solution {
    public boolean checkDivisibility(int n) {
        int s=0;
        int m=1;
        int t=n;
        
        while(n>0)
        {
            s+=n%10;
            m*=n%10;
            n=n/10;
        }
        if(t%(s+m)==0)
        {
            return true;
        }
        else{
            return false;
        }
    }
}