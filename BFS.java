import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BFS {
    

    private void bfs(int startVertex,boolean[] visited,List<List<Integer>> adj,List<Integer> traversal)
    {
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int src = queue.poll();

            //add the src to traversal
            traversal.add(src);

            //go to its all neighbour
            for(int i=0;i<adj.get(src).size();i++)
            {  
                int vertex = adj.get(src).get(i);
                if(!visited[vertex])
                {
                    // make it visted and add to the queue
                    visited[vertex] = true;
                    queue.offer(vertex);
                }
            }
        }
        
    }

    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {

        boolean[] visited = new boolean[V];

        // Queue<Integer> queue = new LinkedList<>();
        // queue.offer(0);
        // visited[0] = true;

        List<Integer> traversal = new ArrayList<>();

        for(int i=0;i<V;i++) // handle disconnected graph as well
        {
            if(!visited[i])
                bfs(i, visited, adj,traversal);
        }
        return traversal;
    }

    public static void main(String[] args) {
        
    }
}
