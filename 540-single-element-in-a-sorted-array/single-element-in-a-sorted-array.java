class Solution {
    public int singleNonDuplicate(int[] a) {
        
        for(int i=0;i<a.length-1;i+=2)
        {
            if(a[i]!=a[i+1])
            {
                return a[i];
            }

        }

        return a[a.length-1];
        
    }
}