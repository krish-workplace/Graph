import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnection {

    private int low[],discover[];
    private int timer = 1;

    private void dfs(int node,int parent ,List<List<Integer>> adj,List<List<Integer>> bridges,boolean[] visited)
    {
        //mark node as visited
        visited[node] = true;
        
        //set low and discover
        low[node] = discover[node] = timer++;

        //visit depth
        for(int adjacentNode : adj.get(node))
        {
            if(adjacentNode == parent)
                continue;

            if(!visited[adjacentNode])
            {
                dfs(adjacentNode, node, adj, bridges, visited);
            }

            //*here we are backtrack 
            low[node] = Math.min(low[node],low[adjacentNode]);

            //check for this edge is bridge or not 
            if(low[adjacentNode] > discover[node]) //here bridge condition 
            {
                bridges.add(Arrays.asList(adjacentNode,node));
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    
        List<List<Integer>> bridges = new ArrayList<>();

        List<List<Integer>> adj = new ArrayList<>();

        this.discover = new int[n];
        this.low = new int[n];

        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }

        //create an undirected graph
        for(int i=0;i<connections.size();i++)
        {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        // applt dfs
        // when we traceback update the low and also check for bridge   
        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                dfs(i, -1, adj, bridges,visited);
            }
        }

        return bridges;
    }

}
