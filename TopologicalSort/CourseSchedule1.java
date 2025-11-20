package TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule1 {
    
    private int bfs(List<List<Integer>> adj,int[] indegree,Queue<Integer> queue,int[] res)
    {
        int cnt = 0;
        while (!queue.isEmpty()) {
            //pop the front
            int src = queue.poll();
            res[cnt] = src;
            cnt++;

            //visit 1 level neighbour 
            for(int i=0;i<adj.get(src).size();i++)
            {
                --indegree[adj.get(src).get(i)];
                if(indegree[adj.get(src).get(i)] == 0)
                    queue.offer(adj.get(src).get(i));
            }
        }
        return cnt; // for case I return cnt and for case II return res(toposort)
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        //here use an kahn's algorithm for (BFS)

        //create an adjacentcy list
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<numCourses;i++)
        {
            adj.add(new ArrayList<>());
        }

        //find an indegree
        int[] indegree = new int[numCourses];
        for(int[] edge : prerequisites )
        {
            adj.get(edge[0]).add(edge[1]);
            ++indegree[edge[1]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++)
        {
            if(indegree[i] == 0)
                queue.offer(i);
        }

        int[] res = new int[numCourses];

        return bfs(adj, indegree, queue,res) == numCourses ? true : false;

    }   

}
