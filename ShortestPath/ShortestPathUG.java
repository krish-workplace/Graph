package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int node,dist;

    Pair(int node,int dist)
    {
        this.node = node;
        this.dist = dist;
    }
}

public class ShortestPathUG {

    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here

        int[] shortPath = new int[adj.size()];
        Arrays.fill(shortPath, Integer.MAX_VALUE);

        //create an Queue
        Queue<Pair> queue = new LinkedList<>();

        //push the src
        queue.offer(new Pair(src, 0));
        shortPath[src] = 0;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            //visit node first level neighbour
            for(int i=0;i<adj.get(pair.node).size();i++)
            {
                int adjacent = adj.get(pair.node).get(i);
             
                if((shortPath[adjacent] == Integer.MAX_VALUE) || (shortPath[adjacent] > pair.dist + 1))
                {
                    shortPath[adjacent] = pair.dist + 1;
                    queue.offer(new Pair(adjacent, shortPath[adjacent]));
                }
            }
        }

        // Replace unreachable nodes with -1
        for (int i = 0; i < adj.size(); i++) {
            if (shortPath[i] == Integer.MAX_VALUE) {
                shortPath[i] = -1;
            }
        }

        return shortPath;
    }

}

