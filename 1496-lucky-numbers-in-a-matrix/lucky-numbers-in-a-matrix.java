class Solution {
    public List<Integer> luckyNumbers(int[][] a) {
                
        List<Integer> ans=new ArrayList<>();
        
        int m=a.length;
        int n=a[0].length;

        for(int i=0;i<m;i++)
        {
            int min=a[i][0];
            int c=0;

            for(int j=1;j<n;j++)
            {
                if(a[i][j]<min)
                {
                    min=a[i][j];
                    c=j;
                }
            }
            boolean lucky=true;

            for(int k=0;k<m;k++)
            {
                if(a[k][c]>min)
                {
                    lucky=false;
                    break;
                }
            }

            if(lucky)
            {
                ans.add(min);
            }
        }
        return ans;
    }
}