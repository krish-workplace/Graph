import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int row,col,dist;
    Pair(int row,int col,int dist)
    {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

public class Matrix01 {
    
    public int[][] updateMatrix(int[][] mat) {

        boolean[][] visited = new boolean[mat.length][mat[0].length];

        //create an Queue
        Queue<Pair> queue = new LinkedList<>();

        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++)
            {
                if(mat[i][j] == 0)
                {
                    visited[i][j] = true;
                    queue.offer(new Pair(i, j, 0));
                }
            }
        }


        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();

            // explore all the four direction
            int[] drow = {-1,0,1,0};
            int[] dcol = {0,1,0,-1};

            for(int k=0;k<4;k++)
            {
                int nrow = pair.row + drow[k];
                int ncol = pair.col + dcol[k];

                //check for valid neighbour row/col
                if(nrow >=0 && nrow<mat.length && ncol >= 0 && ncol<mat[0].length && !visited[nrow][ncol] && mat[nrow][ncol] == 1)
                {
                    visited[nrow][ncol] = true;
                    mat[nrow][ncol] = pair.dist + 1;
                    queue.offer(new Pair(nrow, ncol, pair.dist + 1));
                }
            }

        }

        return mat;
    }
}
