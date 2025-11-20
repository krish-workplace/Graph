public class NoOfEnclaves {


    private void dfs(int[][] grid,boolean[][] visited,int row,int col)
    {

        // make the visited
        visited[row][col] = true;

        // got ot its depth
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        for(int k=0;k<4;k++)
        {
            int nrow = row + drow[k];
            int ncol = col + dcol[k];

            //check the condition
            if(nrow >= 0 && nrow<grid.length && ncol >=0 && ncol<grid[0].length && grid[nrow][ncol] == 1 && !visited[nrow][ncol])
                dfs(grid, visited, nrow, ncol);
        }
    }

    public int numEnclaves(int[][] grid) {
     
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        //travverse boundary row
        for(int j=0;j<grid[0].length;j++)
        {
            //first row
            if(grid[0][j] == 1 && !visited[0][j])
                dfs(grid, visited, 0, j);
            
            //last row
            if(grid[grid.length-1][j] == 1 && !visited[grid.length-1][j])
                dfs(grid, visited, grid.length-1, j);
        }

        //traverse boundary column
        for(int i=0;i<grid.length;i++)
        {
            if(grid[i][0] == 1 && !visited[i][0])
                dfs(grid, visited, i, 0);

            if(grid[i][grid[0].length-1] == 1 && !visited[i][grid[0].length-1])
                dfs(grid, visited, i, grid[0].length-1);
        }

        int cnt = 0;
        for(int i=1;i<grid.length-1;i++)
        {
            for(int j=1;j<grid[0].length-1;j++)
            {
                if(grid[i][j] == 1 && !visited[i][j])
                    cnt++;
            }
        }

        return cnt;
    }

}