class Solution {
    public int totalNQueens(int n) {
        
        boolean[][] board=new boolean[n][n];
        int k=queen(board,0);
        return k;
    }

    public static int queen(boolean[][] board,int row)
    {
        if(row==board.length)
        {
            return 1;
        }
        int c=0;
        for(int col=0;col<board.length;col++)
        {
            if(isSafe(board,row,col))
            {
                board[row][col]=true;
                c+=queen(board,row+1);
                board[row][col]=false;
            }
        }
        return c;
        
    }

    private static boolean isSafe(boolean[][] board,int row,int col)
    {
        for(int i=0;i<row;i++)
        {
            if(board[i][col])
            {
                return false;
            }
        }

        int minleft=Math.min(row,col);
        for(int i=1;i<=minleft;i++)
        {
            if(board[row-i][col-i])
            {
                return false;
            }
        }

        int minright=Math.min(row,board.length-col-1);
        for(int i=1;i<=minright;i++)
        {
            if(board[row-i][col+i])
            {
                return false;
            }
        }

        return true;
    }

    
    
}