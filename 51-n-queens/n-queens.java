class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result =new ArrayList<>();
        boolean[][] board=new boolean[n][n];
        queen(board,0,result);
        return result;
    }

    public static void queen(boolean[][] board,int row,List<List<String>> result)
    {
        if(row==board.length)
        {
            result.add(display(board));
            return;
        }

        for(int col=0;col<board.length;col++)
        {
            if(isSafe(board,row,col))
            {
                board[row][col]=true;
                queen(board,row+1,result);
                board[row][col]=false;
            }
        }
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

    public static List<String> display(boolean[][] board)
    {
        List<String> list=new ArrayList<>();

        for(int r=0;r<board.length;r++)
        {
            StringBuilder row = new StringBuilder();
            for(int c=0;c<board.length;c++)
            {
                if(board[r][c])
                {
                    row.append("Q");
                }
                else{
                     row.append(".");
                }
            }
            list.add(row.toString());
        }
        return list;
    }
}