package ShortestPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int price, node;

    Pair(int price, int node) {
        this.price = price;
        this.node = node;
    }
}

class StepPair {
    int node, price, step;

    StepPair(int node, int price, int step) {
        this.node = node;
        this.step = step;
        this.price = price;
    }
}

public class CheapestFlightsK_Stops {

    private void dijkstra(ArrayList<ArrayList<Pair>> adj, int[] shortestPrice, int src, int dst, int k) {
        
        // create Queue
        //! here we use simple queue because we sort based on step and in bfs stop increase by liner like 1 at every level we increase step by 1 thats why we need an queue not priorityQueue
        Queue<StepPair> queue = new LinkedList<>(); 

        queue.offer(new StepPair(src, 0, 0));
        shortestPrice[src] = 0;

        while (!queue.isEmpty()) {

            StepPair pair = queue.poll();
            int source = pair.node;
            int step = pair.step;
            int price = pair.price;

            //first check for within step 
            if (step <= k) 
            {
                for (Pair adjacentPair : adj.get(source)) {
                    
                    int newPrice = price + adjacentPair.price;
                    if (newPrice < shortestPrice[adjacentPair.node] && step <= k) 
                    {
                        shortestPrice[adjacentPair.node] = newPrice;
                        queue.offer(new StepPair(adjacentPair.node, newPrice,step+1));
                    }
                    
                }
            }
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] shortestPrice = new int[n];

        // create an adjaecncy list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            shortestPrice[i] = Integer.MAX_VALUE;
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            int source = flights[i][0];
            int destination = flights[i][1];
            int price = flights[i][2];

            adj.get(source).add(new Pair(price, destination));
        }

        dijkstra(adj, shortestPrice, src, dst, k);

        return shortestPrice[dst] == Integer.MAX_VALUE ? -1 : shortestPrice[dst];
    }

}
