package ShortestPath;

import java.util.Arrays;

public class BellmanFord {

    private boolean bellmanForAlgo(int[][] edges,int V,int[] shortestDistance,int src)
    {
        shortestDistance[src] = 0;

        //relax edges for n-1 time 
        for(int i=1;i<=V-1;i++)
        {
            //relax the all edges 
            for(int[] edge : edges)
            {
                int source = edge[0];
                int dst = edge[1];
                int weight = edge[2];

                if(shortestDistance[source] == Integer.MAX_VALUE) continue;

                int newDistance = shortestDistance[source] + weight;
                if(shortestDistance[dst] > newDistance)
                {
                    shortestDistance[dst] = newDistance;
                }
            }
        }

         // ✅ Check for negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (shortestDistance[u] != Integer.MAX_VALUE &&
                shortestDistance[u] + wt < shortestDistance[v]) // distance is still reduce so there is negative cycle 
            {
                return true; // negative cycle detected
            }
        }

        return false;
    }

    public int[] bellmanFord(int V, int[][] edges, int src) {
        
        int[] shortestDistance = new int[V];

        Arrays.fill(shortestDistance, Integer.MAX_VALUE);

        boolean hasNegativeCycle = bellmanForAlgo(edges, V, shortestDistance, src);

        // ✅ If negative cycle found
        if (hasNegativeCycle) {
            return new int[]{-1};
        }

        // ✅ Replace unreachable distances with 108
        for (int i = 0; i < V; i++) 
        {
            if (shortestDistance[i] == Integer.MAX_VALUE) {
                shortestDistance[i] =  (int)1e8;;
            }
        }

        return shortestDistance;
    }

}
