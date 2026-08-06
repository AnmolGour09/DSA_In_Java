class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) 
    {
        int or= mat.length;
        int oc=mat[0].length;
        int [][] ans=new int[r][c];
        if(or*oc==r*c)
        {
            int k=0;
            int l=0;
            for(int i=0;i<mat.length;i++)
            {
                for(int j=0;j<mat[i].length;j++)
                {
                    ans[k][l]=mat[i][j];
                    l++;
                    if (l == c) 
                    {
                        k++;
                        l = 0;
                    }
                }
            }
            return ans;
        }

        return mat;

    }
}