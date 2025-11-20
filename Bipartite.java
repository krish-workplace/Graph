import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {

    private boolean dfs(int[][] graph,int[] colored,int src,int color)
    {

        //mark the color
        colored[src] = color;

        //go to the depth

        for(int i=0;i<graph[src].length;i++)
        {
            int adjacent = graph[src][i];

            if(colored[adjacent] == -1)
            {
               return dfs(graph, colored, adjacent, color == 1 ? 0 : 1);
            }
            else if(colored[adjacent] == colored[src])
                return false;

        }

        return true;
    }

    private boolean bfs(int[][] graph, Queue<Integer> queue, int[] colored,int src)
    {
        // push the first node
        queue.offer(src);
        // mark the color
        colored[src] = 0;

        while (!queue.isEmpty()) 
        {

            // pop the fornt
            int node = queue.poll();

            for (int i = 0; i < graph[node].length; i++) 
            {
                // here adjacent comes
                int adjacent = graph[node][i];

                if (colored[adjacent] == -1) 
                {
                    // push into queue and mark its color
                    queue.offer(adjacent);
                    colored[adjacent] = colored[node] == 0 ? 1 : 0;
                } 
                else if (colored[node] == colored[adjacent]) 
                    return false;

            }
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {

        int n = graph.length;

        Queue<Integer> queue = new LinkedList<>();
        int colored[] = new int[n];

        Arrays.fill(colored, -1);

        for(int i=0;i<n;i++)
        {
            if(colored[i] == -1)
            {
                if(dfs(graph, colored, i, 0) == false)
                    return false;
                // if(bfs(graph, queue, colored,i) == false)
                //     return false;
            }
        }

        return true;
    }


}
