package TopologicalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionBFS {

    private int bfs(List<List<Integer>> adj, Queue<Integer> queue, int[] indegree, int[] res, int ind) // ✅ return new index
    {
        while (!queue.isEmpty()) {
            // poll the front 
            int src = queue.poll();
            res[ind++] = src;   // ✅ now safe, since ind starts from 0

            // visit the neighbour 
            for (int i = 0; i < adj.get(src).size(); i++) {
                // reduce the indegree
                indegree[adj.get(src).get(i)]--;

                if (indegree[adj.get(src).get(i)] == 0)
                    queue.offer(adj.get(src).get(i));
            }
        }
        return ind; // ✅ return how many nodes were processed
    }

    public boolean isCyclic(int N, List<List<Integer>> adj) {
        // here we use Kahn's algorithm 

        Queue<Integer> queue = new LinkedList<>();

        // find indegree
        int[] indegree = new int[N];
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                indegree[adj.get(i).get(j)]++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int[] res = new int[N];
        
        int processedCount = bfs(adj, queue, indegree, res, 0);

        return processedCount != N;
    }

}
