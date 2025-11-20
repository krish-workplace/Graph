import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Pair
{
    String str;
    int step;

    Pair(String str,int step)
    {
        this.str = str;
        this.step = step;
    }
}

public class WordLadder1 {

    private boolean differByOneLet(String str1,String str2)
    {
        //check for smaller length
        int len = Math.min(str1.length(),str2.length());
        int cnt = 0;
        for(int i=0;i<len;i++)
        {
            if(str1.charAt(i) != str2.charAt(i))
                cnt++;
            
            if(cnt > 1)
                return false;
        }

        return cnt == 1;
    }


    private int bfs(Map<String,ArrayList<String>> adj,List<String> wordList,String beginWord,String endWord)
    {
        Set<String> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();

        //push the beginWord
        queue.offer(new Pair(beginWord, 1));
        visited.add(beginWord);

        //visit one level neighbour
        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();
            String str = pair.str;
            int step = pair.step;

            for(int i=0;i<adj.get(str).size();i++)
            {
                String adjacent = adj.get(str).get(i);

                if(adjacent.equals(endWord))
                    return step + 1;
                
                //if not same then push adjacent 
                if(!visited.contains(adjacent))
                {
                    visited.add(adjacent);
                    queue.offer(new Pair(adjacent, step + 1));
                }

            }
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        //add beginWord at start of wordList
        if(!wordList.contains(beginWord))
            wordList.add(beginWord);
            
        int n = wordList.size();

        //first of all we have to create an graph
        //here we have an string so we need an map for direct access string 
        Map<String,ArrayList<String>> adj = new HashMap<>();

        for(int i=0;i<n;i++)
        {
            adj.put(wordList.get(i), new ArrayList<String>());
        }
        
        for(int i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                String w1 = wordList.get(i);
                String w2 = wordList.get(j);
                if (differByOneLet(w1, w2)) 
                {
                    adj.get(w1).add(w2);
                    adj.get(w2).add(w1);
                }
            }
        }
        return bfs(adj, wordList, beginWord, endWord);
    }

}
