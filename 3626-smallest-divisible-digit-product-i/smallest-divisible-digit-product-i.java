class Solution {
    public int smallestNumber(int n, int t) {
        int k=0;
        while(true)
        {
            int l=n;
            int p=1;
            while(l>0)
            {
                k=l%10;
                p*=k;
                l/=10;
            }
                
            if(p%t==0)
            {
                return n;
            }     
            else{
                n++;
            }       
        }       
    }
}