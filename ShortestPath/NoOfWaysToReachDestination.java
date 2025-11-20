package ShortestPath;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Pair {
    int node;
    long time; // <-- use long
    Pair(int node, long time) {
        this.node = node;
        this.time = time;
    }
}

public class NoOfWaysToReachDestination {

    private final int MOD = (int)1e9 + 7;

    private int dijkstra(ArrayList<ArrayList<Pair>> adj, long[] shortestTime, int src, int dst) {
        int[] ways = new int[adj.size()];

        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x, y) -> Long.compare(x.time, y.time));
        pQueue.offer(new Pair(src, 0));
        shortestTime[src] = 0;
        ways[src] = 1;

        while (!pQueue.isEmpty()) {
            Pair pair = pQueue.poll();
            int source = pair.node;
            long time = pair.time;            

            //reduce oldtime value that is push early but shortestTime is updated before pop
            if (time > shortestTime[source]) continue; // skip stale

            for (Pair adjacePair : adj.get(source)) {
                int nextNode = adjacePair.node;
                long newTime = time + adjacePair.time; // long addition

                if (shortestTime[nextNode] > newTime) {
                    shortestTime[nextNode] = newTime;
                    ways[nextNode] = ways[source];
                    pQueue.offer(new Pair(nextNode, newTime));
                } else if (shortestTime[nextNode] == newTime) {
                    ways[nextNode] = (ways[nextNode] + ways[source]) % MOD;
                }
            }
        }
        return ways[dst] % MOD;
    }

    public int countPaths(int n, int[][] roads) {
        long[] shortestTime = new long[n]; // long array
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            shortestTime[i] = Long.MAX_VALUE;
        }

        for (int[] road : roads) {
            int src = road[0], dst = road[1];
            long time = road[2];
            adj.get(src).add(new Pair(dst, time));
            adj.get(dst).add(new Pair(src, time));
        }

        return dijkstra(adj, shortestTime, 0, n - 1);
    }

}

    



