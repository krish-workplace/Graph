package StronglyConnectedComponents;

import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju_StronglyConnectedComponent {

    private void dfs(int node,boolean[] visited,ArrayList<ArrayList<Integer>> adj,Stack<Integer> stack)
    {
        //mark the node as visited
        visited[node] = true;

        //visit its all depth
        for(int i=0;i<adj.get(node).size();i++)
        {
            int adjacentNode = adj.get(node).get(i);

            if(!visited[adjacentNode])
                dfs(adjacentNode, visited, adj,stack);
        }
        stack.push(node);
    }

    private void dfsStep3(int node,boolean[] visited,ArrayList<ArrayList<Integer>> adjT)
    {
        //mark as visited
        visited[node] = true;

        for(int i=0;i<adjT.get(node).size();i++)
        {
            int adjacentNode = adjT.get(node).get(i);
            if(!visited[adjacentNode])
                dfsStep3(adjacentNode, visited, adjT);   
        }

    }

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        
        //*1 : sort all vertext based on their finish time 
        //*2 : reverse all edges
        //*3 : apply DFS
  
        int components = 0;

        //^ 1:  sort all vertext based on their finished time 
        Stack<Integer> stack = new Stack<>();
        int n = adj.size();
        boolean[] visited = new boolean[n];

        for(int i=0;i<n;i++)
        {
            if(!visited[i])
                dfs(i, visited, adj,stack);
        }
        
        
        //^ 2 : reverse all edges 
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            adjT.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++)
        {
            for(int adjacentNode : adj.get(i))
            {
                adjT.get(adjacentNode).add(i);
            }
        }

        //^ 3 : apply DFS from stack
        visited = new boolean[n];
        while (!stack.isEmpty()) {

            int node = stack.pop();

            if(!visited[node])
            {
                components++;
                dfsStep3(node, visited, adjT);
            }
        }

        return components;
    }

}
