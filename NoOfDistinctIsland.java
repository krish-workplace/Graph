import java.util.ArrayList;
import java.util.HashSet;

public class NoOfDistinctIsland {
    
    private static void dfs(int[][] arr,boolean[][] visited,int row,int col,int baseRow,int baseCol,ArrayList<String> vec)
    {
        //make them visited
        visited[row][col] = true;   
        vec.add((row - baseRow) + "," + (col - baseCol));

        // go to) depth and visit its neoghbour
        int drow[] = {-1,0,1,0};
        int dcol[] = {0,1,0,-1};
        for(int k=0;k<4;k++)
        {
            int nrow = row + drow[k];
            int ncol = col + dcol[k];

            // check for valid and neighbour is land
            if(nrow >=0 && nrow<arr.length && ncol>=0 && ncol < arr[0].length  &&  !visited[nrow][ncol] && arr[nrow][ncol] == 1)
                dfs(arr, visited, nrow, ncol,baseRow,baseCol,vec);
        }
    }
    
    public static int distinctIsland(int [][] arr, int n, int m) 
	{
        boolean[][] visited = new boolean[n][m];
        
        HashSet<ArrayList<String>> set = new HashSet<>(); 
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                // if foud land then apply an traversal technique
                if(arr[i][j] == 1 && !visited[i][j])
                {
                    ArrayList<String> vec = new ArrayList<>();
                    dfs(arr, visited, i, j,i,j,vec);
                    set.add(vec);
                }
            }
        }
        
        
        return set.size();
	}

}
