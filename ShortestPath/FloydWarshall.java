package ShortestPath;

public class FloydWarshall {
    
    public int[][] shortest_distance(int[][] matrix)
    {
        int n = matrix.length;

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i == j)
                    matrix[i][j] = 0;

                else if(matrix[i][j] == -1)
                    matrix[i][j] = (int)(1e9);
            }
        }

        //^explore all vertex and their all edges 
        for(int via=0;via<n;via++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    //^shortest path from i to j via some
                    int newDistance = matrix[i][via] + matrix[via][j]; 
                    matrix[i][j] = Math.min(matrix[i][j],newDistance);
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j] == (int)(1e9))
                    matrix[i][j] = -1;  
            }
        }

        return matrix;
    }

}
