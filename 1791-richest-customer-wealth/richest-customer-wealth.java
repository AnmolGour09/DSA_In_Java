class Solution {
    public int maximumWealth(int[][] a) {
        int s=0;
        int[] ans=new int[a.length];
        for(int i=0;i<a.length;i++)
        {
            int sum=0;
            for(int j=0;j<a[i].length;j++)
            {
                sum+=a[i][j];
            }
            ans[i]=sum;
        }
        int max=0;
        for(int i=0;i<ans.length;i++)
        {
            if(ans[i]>max||ans[i]==max)
            {
                max=ans[i];
            }
        }
        return max;
    }
}