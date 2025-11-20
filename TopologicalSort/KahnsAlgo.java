package TopologicalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnsAlgo {
    
    static void bfs(List<List<Integer>> adj, Queue<Integer> queue, int[] indegree, int[] res, int ind) {
        while (!queue.isEmpty()) {
            int src = queue.poll();

            // add src to res array
            res[ind++] = src;

            for (int i = 0; i < adj.get(src).size(); i++) {
                --indegree[adj.get(src).get(i)];
                if (indegree[adj.get(src).get(i)] == 0)
                    queue.offer(adj.get(src).get(i));
            }
        }
    }

    public int[] topoSort(int V, List<List<Integer>> adj) {

        // find indegree
        int[] indegree = new int[V];
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                ++indegree[adj.get(i).get(j)];
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) // independent node
                queue.offer(i);
        }

        int[] res = new int[V];

        // apply BFS once (no need for visited[])
        bfs(adj, queue, indegree, res, 0);

        return res;
    }

}
