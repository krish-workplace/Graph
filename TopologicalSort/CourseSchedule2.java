package TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule2 {

    private int bfs(ArrayList<ArrayList<Integer>> adj,Queue<Integer> queue,int[] indegree,int[] res)
    {
        int ind=0;
        while (!queue.isEmpty()) {
            int src = queue.poll();
            res[ind++] = src;

            //reduce indegree for  first level depth
            for(int i=0;i<adj.get(src).size();i++)
            {
                --indegree[adj.get(src).get(i)];
                if(indegree[adj.get(src).get(i)] == 0)
                    queue.offer(adj.get(src).get(i));
            }
        }
        return ind;
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++)
        {
            adj.add(new ArrayList<>());        
        }

        //find the indegree
        int[] indegree = new int[numCourses];
        for(int[] edge : prerequisites)
        {
            ++indegree[edge[0]]; // here like [0,1] means first complete 1 then take 0 like reverse dependency
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++)
        {
            if(indegree[i] == 0)
                queue.offer(i);
        }

        int[] res = new int[numCourses];
        

        return bfs(adj, queue, indegree, res) == numCourses ? res : new int[0];
    }


}
