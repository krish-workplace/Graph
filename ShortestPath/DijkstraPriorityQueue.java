package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Pair{
    int node,weight;

    Pair(int node,int weight)
    {
        this.node = node;
        this.weight = weight;
    }
}

public class DijkstraPriorityQueue {
   
    public int[] dijkstra(int V, int[][] edges, int src) {
    
        //create adjacent List
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<V;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++)
        {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            adj.get(u).add(new Pair(v, w));
        }


        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        PriorityQueue<Pair> pQueue = new PriorityQueue<Pair>((x,y) ->  x.weight - y.weight);
        pQueue.offer(new Pair(src, 0));
        shortestPath[src] = 0;

        while (!pQueue.isEmpty()) {
            
            Pair pair = pQueue.poll();

            int source = pair.node;
            int distance = pair.weight;
            //visit first level adjacent 
            for(int i=0;i<adj.get(source).size();i++)
            {
                Pair adjacentPair = adj.get(source).get(i);

                if(shortestPath[adjacentPair.node] > (distance + adjacentPair.weight))
                {
                    shortestPath[adjacentPair.node] = distance + adjacentPair.weight;
                    pQueue.offer(new Pair(adjacentPair.node, shortestPath[adjacentPair.node]));
                }
            }

        }
        return shortestPath;
    }
}
