package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Triplet
    {
        int node, parent, weight;
        Triplet(int parent,int node,int weight)
        {
            this.node = node;
            this.parent = parent;
            this.weight = weight;
        }
    }

class Pair{
        int node,weight;
        Pair(int node,int weight)
        {
            this.node = node;
            this.weight = weight;
        }
    }

public class PrimsAlgo {
    
    private int prims(int V,ArrayList<ArrayList<Pair>> adj,boolean[] visited,int start)
    {
        int[][] mst = new int[V-1][2];
        int sum = 0;
        int edgeIndex = 0;

        //start from any node
        PriorityQueue<Triplet> pQueue = new PriorityQueue<>((x,y)-> x.weight - y.weight);

        //⁡⁢⁣offer start in Queue
        pQueue.offer(new Triplet(-1, start, 0));

        while (!pQueue.isEmpty()) {
            
            Triplet triple = pQueue.poll();
            int src = triple.node;
            int parent = triple.parent;
            int weight = triple.weight;

            if(visited[src]) continue;

            //else mark as visit then also visit it neighbour
            visited[src] = true;

            //add edge in MST
            if(parent != -1)
            {
                mst[edgeIndex++] = new int[]{parent,src};
                
                //update the mst sum value
                sum = sum + weight;
            }

            for(int i=0;i<adj.get(src).size();i++)
            {
                Pair adjacentPair = adj.get(src).get(i);

                if(!visited[adjacentPair.node])
                    pQueue.offer(new Triplet(src, adjacentPair.node, adjacentPair.weight));
            }

        }
        return sum;
    }

    public int spanningTree(int V, int[][] edges) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        boolean[] visited = new boolean[V];

        for(int i=0;i<V;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges)
        {
            int src = edge[0];
            int dst = edge[1];
            int weight = edge[2];

            adj.get(src).add(new Pair(dst, weight));
            adj.get(dst).add(new Pair(src, weight));
        }
        
        return prims(V, adj, visited, 0);
    }

}
