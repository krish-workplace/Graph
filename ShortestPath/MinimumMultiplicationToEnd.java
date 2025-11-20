package ShortestPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int node,step;
    Pair(int node,int step)
    {
        this.node = node;
        this.step = step;
    }
}

public class MinimumMultiplicationToEnd {
    
    void dijkstra(int[] arr,int[] shortestPath,int start,int end)
    {
        //create an Queue
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(start, 0));
        shortestPath[start] = 0;

        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();
            int node = pair.node;
            int step = pair.step;

            //visit first level neighbour by multiply pair.node with every value of arr
            for(int i=0;i<arr.length;i++)
            {
                int adjacentNode = (arr[i] * node) % 100000;
                if(shortestPath[adjacentNode] > (step + 1))
                {
                    shortestPath[adjacentNode] = (step + 1);
                    queue.offer(new Pair(adjacentNode, (step + 1)));
                }

                if(adjacentNode == end)
                    return;
            }
        }


    }

    int minimumMultiplications(int[] arr, int start, int end) {
        
        if(end >= 100000)
            return -1;

        int[] shortestPath = new int[100000];

        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        dijkstra(arr, shortestPath, start, end);

        return shortestPath[end] != Integer.MAX_VALUE ? shortestPath[end] : -1;
    }

}
