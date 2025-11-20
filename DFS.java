import java.util.ArrayList;
import java.util.List;

public class DFS {

    public void dfs(int src,List<List<Integer>> adj,boolean visited[],List<Integer> traversal)
    {
        // append to the traversal list 
        visited[src] = true;
        traversal.add(src);

        for(int i=0;i<adj.get(src).size();i++)
        {
            int vertex = adj.get(src).get(i);
            if(!visited[vertex])
            {
                // call its neighbout
                dfs(vertex,adj,visited,traversal);
            }
        }
    }


    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {

        boolean[] visited = new boolean[V];
        List<Integer> traversal = new ArrayList<>();
        
        //start with any node
        // go to depth
        for(int i=0;i<V;i++) // handle disconnected graph as well
        {
            if(!visited[i])
                dfs(0, adj, visited, traversal);
        }

        return traversal;
    }


    public static void main(String[] args) {
        
    }
}
