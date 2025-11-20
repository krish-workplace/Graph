package TopologicalSort;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {

    static void dfs(ArrayList<ArrayList<Integer>> adj,boolean[] visited,Stack<Integer> stack,int src)
    {
        //make as visited
        visited[src] = true;

        //go to depth
        for(int adjacent : adj.get(src))
        {
            if(!visited[adjacent])
            {
                dfs(adj, visited, stack, adjacent);
            }
        }

        // once depth finish push into stack
        stack.push(src);
    }

    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here

        //we use an stack to maintain the order it means we store an elememt first that have least dependenacy(no outgoinf edges)

        //here we have an edges array in unorder so we use an adjList for optimize result
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<V;i++) adj.add(new ArrayList<>());

        for(int[] edge : edges) adj.get(edge[0]).add(edge[1]);

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();

        for(int i=0;i<V;i++)
        {
            if(!visited[i])
            {
                dfs(adj, visited, stack, i);
            }
        }

        //once we complete just pop from stack
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        
        return res;
    }
    
}