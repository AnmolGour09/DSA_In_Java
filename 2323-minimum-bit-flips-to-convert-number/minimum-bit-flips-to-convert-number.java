class Solution {
    public int minBitFlips(int s, int g) {
        int x=s^g;
        int c=0;
        while(x!=0)
        {
            c+=x&1;
            x=x>>1;
        }
        return c;
    }
}