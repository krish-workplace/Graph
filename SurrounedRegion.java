public class SurrounedRegion {


    private void dfs(char[][] board,boolean[][] visited,int row,int col)
    {
        //make the visited
        visited[row][col] = true;

        //go to depth surrounding to it 
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        for(int k=0;k<4;k++)
        {
            int nrow = row + drow[k];
            int ncol = col + dcol[k];

            //check for surrounding 
            if(nrow >=0 && nrow<board.length && ncol>=0 && ncol<board[0].length && !visited[nrow][ncol] && board[nrow][ncol] == 'O')
            {
                dfs(board, visited, nrow, ncol);
            }
        }
    }

    public void solve(char[][] board) {
     
        boolean[][] visited = new boolean[board.length][board[0].length];

        //first find all boundary level o's then apply an dfs

        for(int i=0;i<board.length;i++)
        {
            //first column
            if(!visited[i][0] && board[i][0]=='O')
            {
                dfs(board, visited, i, 0);
            }

            //last column
            if(!visited[i][board[0].length-1] && board[i][board[0].length - 1] == 'O')
            {
                dfs(board, visited, i, board[0].length-1);
            }
        }
        
        for(int j=0;j<board[0].length;j++)
        {
            //first row
            if(!visited[0][j] && board[0][j]=='O')
            {
                dfs(board, visited, 0, j);
            }

            //last row
            if(!visited[board.length-1][j] && board[board.length-1][j] == 'O')
            {   
                dfs(board, visited, board.length-1, j);
            }
        }

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j] == 'O' && !visited[i][j])
                    board[i][j] = 'X';
            }
        }

    }

}