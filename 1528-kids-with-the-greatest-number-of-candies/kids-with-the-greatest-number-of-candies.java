class Solution {
    public List<Boolean> kidsWithCandies(int[] a, int e) {
        List<Boolean> ans=new ArrayList<>();
        int max=0;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]>max)
            {
                max=a[i];
            }
        }
        
        
            
            for(int i=0;i<a.length;i++)
            {
               
                if(a[i]+e>=max)
                {
                    ans.add(true);
                }
                else
                {
                    ans.add(false);
                }
            }
        
        return ans;
        
    }
}