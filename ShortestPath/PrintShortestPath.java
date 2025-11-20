package ShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Pair{
    int node,weight;

    Pair(int node,int weight)
    {
        this.node = node;
        this.weight = weight;
    }
}

public class PrintShortestPath {

    private void dijkstra(ArrayList<ArrayList<Pair>> adj,int src,int[] shortestPath,int[] parent)
    {
        //create an Priority Queue
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x,y)-> x.weight - y.weight);
        pQueue.add(new Pair(src, 0));
        shortestPath[src] = 0;
        parent[src] = src;

        while (!pQueue.isEmpty()) {
            
            Pair pair = pQueue.poll();
            
            int source = pair.node;
            int distance = pair.weight;            

            for(int i=0;i<adj.get(source).size();i++)
            {
                Pair adjacentPair = adj.get(source).get(i);

                if(shortestPath[adjacentPair.node] > (distance + adjacentPair.weight))
                {
                    //here i found shortest path like where i coming from 

                    //here we need to update the parent of adjacent
                    parent[adjacentPair.node] = source;

                    shortestPath[adjacentPair.node] = (distance + adjacentPair.weight);
                    pQueue.offer(new Pair(adjacentPair.node, shortestPath[adjacentPair.node]));
                }
            }
        }

    }

    public List<Integer> shortestPath(int n, int m, int edges[][]) {

        ArrayList<Integer> path = new ArrayList<>();
        int[] shortestPath = new int[n+1];
        int[] parent = new int[n+1];

        //create an Adjacency List
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++)
        {
            adj.add(new ArrayList<>());

            shortestPath[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<m;i++)
        {
            int src = edges[i][0];
            int dest = edges[i][1];
            int weight = edges[i][2];

            adj.get(src).add(new Pair(dest, weight));
            adj.get(dest).add(new Pair(src, weight));
        }

        dijkstra(adj, 1, shortestPath, parent);

        if(shortestPath[n] == Integer.MAX_VALUE)
        {
            path.add(-1);
            return path;
        }

        int node = n;
        while (parent[node] != node) {

            path.add(0, node);
            node = parent[node];
        }

        //insert src 
        path.add(0, 1);

        return path;
    }
        
}
