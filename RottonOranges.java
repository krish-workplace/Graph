import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int row,col,time;

    Pair(int row,int col,int time)
    {
        this.row = row;
        this.time = time;
        this.col = col;
    }
}

public class RottonOranges {

    public int orangesRotting(int[][] grid) {
        
        int[][] visited = new int[grid.length][grid[0].length];
        int cntFresh = 0,noOfFreshRotton = 0;
        int time = 0;

        Queue<Pair> queue = new LinkedList<>();

        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j] == 1)
                    cntFresh++;

                else if(grid[i][j] == 2)
                {
                    visited[i][j] = 2;
                    queue.offer(new Pair(i,j,0));
                }
            }
        }

        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        while (!queue.isEmpty()) {
            //poll the front 
            Pair pair = queue.poll();

            for(int k=0;k<4;k++)
            {
                int nrow = pair.row + drow[k];
                int ncol = pair.col + dcol[k];
                time = pair.time;

                if(nrow >=0 && nrow<grid.length && ncol>=0 && ncol<grid[0].length && visited[nrow][ncol] != 2  && grid[nrow][ncol] == 1)
                {
                    //make the neighbour visited 
                    visited[nrow][ncol] = 2;
                    //add to queue
                    queue.offer(new Pair(nrow, ncol, pair.time + 1));
                    noOfFreshRotton++;
                }
            }
        }

        return cntFresh == noOfFreshRotton ? time : -1;
    }

}