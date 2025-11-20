import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pair{
    int vertex,comeFrom;
    Pair(int vertex,int comeFrom)
    {
        this.vertex = vertex;
        this.comeFrom = comeFrom;
    }
}

public class DetectCycle {
    

    private boolean bfs(List<Integer>[] adj,boolean[] visited,int startVertex)
    {
        //create an Queue
        Queue<Pair> queue = new LinkedList<>();

        // push stratVertex
        queue.offer(new Pair(startVertex, -1));
        //make it visited
        visited[startVertex] = true;
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            //check neighbour at same level

            for(int i=0;i<adj[pair.vertex].size();i++)
            {
                int vertex = adj[pair.vertex].get(i);

                // if visited and not comeFrom same vertex so it means someone who already visit and that create an cycle
                if(visited[vertex] && pair.comeFrom != vertex)
                    return true;
                
                //check for if already visited or not 
                if(!visited[vertex])
                {
                    //offer each neighbour
                    visited[vertex] = true;
                    queue.offer(new Pair(vertex, pair.vertex));
                }
            }            
        }

        return false;
    }


    private boolean dfs(List<Integer>[] adj,boolean[] visited,int stratVertex,int comeFrom)
    {
        // make the visited 
        visited[stratVertex] = true;

        // got to depth 
        for(int i=0;i<adj[stratVertex].size();i++)
        {
            int neighbourVertex = adj[stratVertex].get(i);
            if(!visited[neighbourVertex])
            {
                boolean result = dfs(adj, visited, neighbourVertex,stratVertex);

                if(result == true) return true;
            }       
            else if(neighbourVertex != comeFrom)
                return true;
        }
        return false;
    }


    public boolean isCycle(int V, List<Integer>[] adj) {
        
        boolean[] visited = new boolean[V];
        
        for(int i=0;i<V;i++)
        {
            if(!visited[i])
            {
                if(bfs(adj, visited, i) == true)
                    return true;
            }

        }
        return false;
    }

}
