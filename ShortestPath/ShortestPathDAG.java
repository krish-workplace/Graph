package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Pair{
    int weight,dest;

    Pair(int dest,int weight)
    {
        this.dest = dest;
        this.weight = weight;
    }
}

public class ShortestPathDAG {
    
    private void toposort(ArrayList<ArrayList<Pair>> adj,Stack<Integer> stack,boolean[] visited,int src)
    {
        //mark as visited
        visited[src] = true;

        //visit it all neighbour
        for(int i=0;i<adj.get(src).size();i++)
        {
            int adjacent = adj.get(src).get(i).dest;
            if(!visited[adjacent])
                toposort(adj,stack, visited, adjacent);
        }

        //push the src into stack
        stack.push(src);
    }


    public int[] shortestPath(int V, int E, int[][] edges) {


        // for int[][] edges
        //where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

        int[] shortPath = new int[V];
        Arrays.fill(shortPath, Integer.MAX_VALUE);

        //create an adjacency List
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i=0;i<V;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++)
        {
            int src = edges[i][0];
            int dest = edges[i][1];
            int weight = edges[i][2];

            adj.get(src).add(new Pair(dest, weight));
        }
        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++)
        {
            if(!visited[i])
                toposort(adj, stack, visited, i);
        }
        
        shortPath[0] = 0;

        while (!stack.isEmpty()) {
            
            int src = stack.pop();

            if (shortPath[src] != Integer.MAX_VALUE) {
                for(int i=0;i<adj.get(src).size();i++)
                {
                    Pair adjacentPair = adj.get(src).get(i);
    
                    if(shortPath[adjacentPair.dest] > (shortPath[src] + adjacentPair.weight))
                    {
                        shortPath[adjacentPair.dest] = shortPath[src] + adjacentPair.weight;
                    }
                }
            }
        }

        for(int i=0;i<V;i++)
        {
            if(shortPath[i] == Integer.MAX_VALUE)
                shortPath[i] = -1;
        }

        return shortPath;
    }

}
