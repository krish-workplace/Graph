import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int row,col;
    Pair(int row,int col)
    {
        this.row = row;
        this.col = col;
    }
}

public class NoOfIsland {

    /*
     *             [i-1][j] 
     *           
     * [i][j-1]  <- [i][j]       ->   [i][j+1]
     * 
     *             [i+1][j]
     * 
     */

    private void bfs(int row,int col,char[][] grid,boolean[][] visited)
    {
        Queue<Pair> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.offer(new Pair(row, col));

        while (!queue.isEmpty()) {
            //visit all level
            Pair pair = queue.poll();
          
            /*
            * this is for 8 direction 
            for(int delRow = -1;delRow <= 1;delRow++)
            {
                for(int delCol = -1;delCol <= 1;delCol++)
                {
                    int nrow = pair.row + delRow;
                    int ncol = pair.col + delCol;

                    //check if eighbout is visited or not
                    if( (nrow>=0 && nrow <grid.length) && (ncol >=0 && ncol <grid[0].length) && !visited[nrow][ncol] && (grid[nrow][ncol] == '1'))
                    {
                        visited[nrow][ncol] = true;
                        queue.offer(new Pair(nrow, ncol));
                    }
                }
            }*/

            // for 4 direction 
            int[] drwo = {-1,0,1,0};
            int[] dcol = {0,1,0,-1};
            for(int k = 0;k<4;k++)
            {
                int nrow = pair.row + drwo[k];
                int ncol = pair.col + dcol[k];

                if( (nrow>=0 && nrow <grid.length) && (ncol >=0 && ncol <grid[0].length) && !visited[nrow][ncol] && (grid[nrow][ncol] == '1'))
                   {
                       visited[nrow][ncol] = true;
                       queue.offer(new Pair(nrow, ncol));
                   }

            }
        }
    }

    public int numIslands(char[][] grid) {
        int cnt = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(!visited[i][j] && grid[i][j] == '1')
                {
                    ++cnt;
                    // call the bfs or dfs
                    bfs(i, j, grid, visited);
                }
            }
        }

        return cnt;
        
    }
    
}