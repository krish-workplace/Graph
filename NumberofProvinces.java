import java.util.LinkedList;
import java.util.Queue;

public class NumberofProvinces {

    private void bfs(int startVertex,boolean[] visited,int[][] adj,Queue<Integer> queue)
    {
        visited[startVertex] = true;
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int src = queue.poll();

            // print the traversal

            for(int i=0;i<adj.length;i++)
            {
                if(src != i && adj[src][i] == 1 && !visited[i] )
                {
                    //maer as visited
                    visited[i] = true;
                    queue.offer(i);

                }
            }
        }

    }
    
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int cnt = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();


        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                cnt++;
                bfs(i, visited, isConnected,queue);
            }
        }
        return cnt;   
    }
}
