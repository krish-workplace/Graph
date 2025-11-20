package ShortestPath;

import java.util.PriorityQueue;

class Pair{
    int row,col,diff;
    Pair(int row,int col,int diff)
    {
        this.row = row;
        this.col = col;
        this.diff = diff;
    }
}

public class PathWithMinimumEffort {
    
    private void dijkstra(int[][] heights,int[][] shortestPathEffort)
    {
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x,y)-> x.diff - y.diff);
        //here source is top left 
        pQueue.offer(new Pair(0, 0, 0));
        shortestPathEffort[0][0] = 0;

        //visit it first level neighbour
        while (!pQueue.isEmpty()) {
            Pair pair = pQueue.poll();
            int row = pair.row;
            int col = pair.col;
            int diff = pair.diff;

            //check in four direction
            for(int i=0;i<4;i++)
            {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                //check for valid neighbour
                if(nrow >=0 && nrow < heights.length && ncol >=0 && ncol < heights[0].length)
                {
                    int absDiff = Math.abs(heights[row][col]-heights[nrow][ncol]);

                    /*
                     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route. so we keep an max(diff,absDiff)

                     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
                     */

                    //^ first we find an maximum effort
                    int effort = Math.max(diff,absDiff);

                    //^ second check for that effort is minimum 
                    if(effort < shortestPathEffort[nrow][ncol])
                    {
                        shortestPathEffort[nrow][ncol]  = effort;
                        pQueue.offer(new Pair(nrow, ncol, shortestPathEffort[nrow][ncol]));
                    }
                }
                
            }
        }

    }
    
    public int minimumEffortPath(int[][] heights) {
        
        int[][] shortestPathEffort = new int[heights.length][heights[0].length];
        
        for(int i=0;i<heights.length;i++)
        {
            for(int j=0;j<heights[0].length;j++)
            {
                shortestPathEffort[i][j] = Integer.MAX_VALUE;
            }
        }

        dijkstra(heights, shortestPathEffort);

        return shortestPathEffort[heights.length-1][heights[0].length-1];
    }

}
