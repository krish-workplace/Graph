package ShortestPath;

import java.util.Arrays;
import java.util.PriorityQueue;

class Pair{
    //here we have an matrix so it should be row and column
    int row,col,weight;
    Pair(int row,int col,int weight)
    {
        this.row = row;
        this.col = col;
        this.weight = weight;
    }
}

public class ShortestPathBinaryMatrix {
    /*
     * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
     * All the visited cells of the path are 0.
     * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
     */

    private int dijkstra(int[][] grid,int[][] shortestPath)
    {
        int[] drow = {-1,0,1,0,-1,-1,1,1};
        int[] dcol = {0,1,0,-1,-1,1,1,-1};

        //create an priority Queue
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x,y)-> x.weight - y.weight);

        //push if top left is 0
        if(grid[0][0] == 0)
        {
            pQueue.offer(new Pair(0, 0, 0));
            shortestPath[0][0] = 0;
        }

        while (!pQueue.isEmpty()) {
            
            //poll the front 
            Pair pair = pQueue.poll();
            

            //visit first level neighbour in 8 direction 
            for(int i=0;i<8;i++)
            {
                int nrow = pair.row + drow[i];
                int ncol = pair.col + dcol[i];

                //check for valid neighbours
                if(nrow >= 0 && nrow < grid.length && ncol >=0 && ncol < grid.length && grid[nrow][ncol] == 0 && (shortestPath[nrow][ncol] > (shortestPath[pair.row][pair.col] + 1)))
                {
                    //update the shotest path
                    shortestPath[nrow][ncol] = (shortestPath[pair.row][pair.col] + 1);
                    pQueue.offer(new Pair(nrow, ncol, shortestPath[nrow][ncol]));
                }
            }

            //if pair node is bottom rigth node
            if( (pair.row == grid.length-1 && pair.col == grid.length-1) )
            {
                return shortestPath[pair.row][pair.col] + 1;
            }
        }
        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        int[][] shortestPath = new int[n][n];
        
        for(int i=0;i<n;i++)
        {
            Arrays.fill(shortestPath[i], Integer.MAX_VALUE); // or any default value
        }

        return dijkstra(grid, shortestPath);
    }

}
