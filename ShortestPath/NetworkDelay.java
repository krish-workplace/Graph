package ShortestPath;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Pair{
    int node,delay;
    Pair(int node,int delay)
    {
        this.node = node;
        this.delay = delay;
    }
}

public class NetworkDelay {

    private void dijkstra(ArrayList<ArrayList<Pair>> adj,int src,int[] shortestDelay)
    {

        //create an priorityQueue
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x,y)-> x.delay - y.delay);
        pQueue.offer(new Pair(src, 0));
        shortestDelay[src] = 0;

        while (pQueue.isEmpty()) {
            
            Pair pair = pQueue.poll();
            int source = pair.node;
            int delay = pair.delay;

            for(int i=0;i<adj.get(source).size();i++)
            {
                Pair adjacentPair = adj.get(source).get(i);

                if(shortestDelay[adjacentPair.node] > (delay + adjacentPair.delay))
                {
                    shortestDelay[adjacentPair.node] = (delay + adjacentPair.delay);

                    pQueue.offer(new Pair(adjacentPair.node, shortestDelay[adjacentPair.node]));
                }
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        
        //create an adjacency List
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        int[] shortestDelay = new int[n+1];

        for(int i=0;i<=n;i++)
        {
            shortestDelay[i] = Integer.MAX_VALUE;
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<times.length;i++)
        {
            int src = times[i][0];
            int dst = times[i][1];
            int delay = times[i][2];

            adj.get(src).add(new Pair(dst, delay));
        }

        dijkstra(adj, k, shortestDelay);

        int maxi = Integer.MIN_VALUE;
        for(int i=1;i<shortestDelay.length;i++)
        {
            if(shortestDelay[i] == Integer.MAX_VALUE)
                return -1;
            
            maxi = Math.max(maxi,shortestDelay[i]);
        }

        return maxi;
    }

}