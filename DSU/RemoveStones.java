package DSU;

public class RemoveStones {

    private void dfs(int stoneIndex,int[][] stones,boolean[] visited)
    {
       visited[stoneIndex] = true;

       //explore all stone 
       for (int j = 0; j < stones.length; j++) {
            if (!visited[j]) {
                // share row or column
                if (stones[stoneIndex][0] == stones[j][0] || stones[stoneIndex][1] == stones[j][1]) {
                    dfs(j, stones, visited);
                }
            }
        }


    }


    public int removeStones(int[][] stones) {
     
        int n = stones.length;

        boolean[] visited = new boolean[n];
        int connectedComponent = 0;
       
        // use simple dfs approach 

        for(int i=0;i<n;i++)
        {
            if(!visited[i])
            {
                connectedComponent++;
                dfs(i, stones, visited);
            }
        }

        return n - connectedComponent;
    }

}
