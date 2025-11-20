import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvaluateSafeStates {


    private boolean dfs(int[][] graph,boolean[] visited,boolean[] pathVisited,List<Integer> safeNodes,int src)
    {
        //make it visited
        visited[src] = true;
        pathVisited[src] = true;

        //go to depth
        for(int i=0;i<graph[src].length;i++)
        {
            int adjacent = graph[src][i];

            if(!visited[adjacent])
            {
                if(dfs(graph, visited, pathVisited, safeNodes,adjacent)== true)
                    return true;
            }

            else if(pathVisited[adjacent])
            {
                //here we found an loop
                //safe node means a node that can not lead to loop 
                //this is not a safeNode
                return true;
            }
        }

        //reset the pathVisited
        pathVisited[src] = false;

        //if there is no futher child and we did not detect any loop so that node is also an safe node
        safeNodes.add(src);

        return false;

    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] pathVisited = new boolean[n];

        List<Integer> safeNodes = new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                dfs(graph, visited, pathVisited,safeNodes, i);
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }

}