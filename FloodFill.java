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

public class FloodFill {
    
    private void bfs(int[][] image,int color,boolean[][] visited,int r,int c,int initColor)
    {
        //create an Queue
        Queue<Pair> queue = new LinkedList<>();
        visited[r][c] = true;
        queue.offer(new Pair(r, c));
        image[r][c] = color;

        while (!queue.isEmpty()) {
            //poll the front element
            Pair pair = queue.poll();

            //visite all same level neighbour 
            int drow[] = {-1,0,1,0};
            int dcol[] = {0,1,0,-1};

            for(int k=0;k<4;k++)
            {
                int nrow = pair.row + drow[k];
                int ncol = pair.col + dcol[k];

                // here we have to check the condition and this is called how to solve the this problem and this is frontend and backend 

                /*                 
                 * 1) check a valid neighbour 
                 * 2) not visited 
                 * 3) check if have an same color 
                 */


                if(nrow >=0 && nrow< image.length && ncol>=0 && ncol<image[0].length && !visited[nrow][ncol] && image[nrow][ncol] == initColor)
                {
                    visited[nrow][ncol] = true;
                    queue.offer(new Pair(nrow, ncol));
                    image[nrow][ncol] = color;
                }
            }

        }
    }
    
    private void dfs(int[][] image,int color,boolean[][] visited,int r,int c,int initColor)
    {
        //base condition
        if(r<0 || r>=image.length || c<0 || c>=image[0].length || visited[r][c] || image[r][c] != initColor)
        {
            return;
        }

        //make this as visited
        visited[r][c] = true;
        image[r][c] = color;

        // go to depth
        /*
         * here there are four neighbour we have to check 
         */
        dfs(image,color,visited,r-1,c,initColor);// top
        dfs(image,color,visited,r,c+1,initColor);// right 
        dfs(image,color,visited,r+1,c,initColor);// bottom
        dfs(image,color,visited,r,c-1,initColor);//left
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    
        boolean[][] visited = new boolean[image.length][image[0].length];

        // bfs(image, color, visited, sr, sc, image[sr][sc]);

        dfs(image, color, visited, sr, sc, image[sr][sc]);// this is most optimal because we does not need an Pair 
        
        return image;
    }
}
