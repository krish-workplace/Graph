package TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
   
    static ArrayList<Integer> kahnTopoAlgo(ArrayList<ArrayList<Integer>> adj,int[] indegree,int k)
    {
        ArrayList<Integer> res = new ArrayList<>();
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<k;i++)
        {
            if(indegree[i] == 0)
                queue.offer(i);    
        }

        while (!queue.isEmpty()) {
            int src = queue.poll();
            res.add(src);

            for(int i=0;i<adj.get(src).size();i++)
            {
                --indegree[adj.get(src).get(i)];
                if(indegree[adj.get(src).get(i)] == 0)
                    queue.offer(adj.get(src).get(i));
            }
        }

        return res;
    }
   
    public static String getAlienLanguage(String []dictionary, int k) {
        // Write your code here.
   
        int N = dictionary.length;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[k];

        for(int i=0;i<k;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++)
        {
            String s1 = dictionary[i];
            String s2 = dictionary[i+1];
            
            int len = Math.min(s1.length(), s2.length());
            for(int j=0;j<len;j++)
            {
                if(s1.charAt(j) != s2.charAt(j))
                {
                    // here we can say s1.charAt(j) comes before s2.charAt(j)
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    ++indegree[s2.charAt(j) - 'a'];
                    break;
                }
            }
        }

        String res = "";
        for(int it : kahnTopoAlgo(adj, indegree, k))
        {
            res = res + (char)(it + (int)'a');
        }

        return res;
    }

}
