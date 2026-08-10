class Solution {
    public boolean isPerfectSquare(int num) {
        int s=1;
        int e=num/2;
        if(num==1)
        {
            return true;
        }
        while(s<=e)
        {
            int mid=s+(e-s)/2;
            if((long)mid*mid==num)
            {
                return true;
            }
            else if((long)mid*mid<num)
            {
                s=mid+1;
            }
            else{
                e=mid-1;
            }

        }
        return false;
    }
}