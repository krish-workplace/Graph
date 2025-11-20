package ShortestPath;

public class FindCityWithSmallestThresold {
    
    private void wershallAlgo(int[][] matrix,int n)
    {

        for(int via=0;via<n;via++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(matrix[i][via]== Integer.MAX_VALUE || matrix[via][j] == Integer.MAX_VALUE)
                        continue;

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                }
            }
        }
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    
        //create an adjacency matrix
        int[][] matrix = new int[n][n];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if( i==j) continue;

                matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int[] edge : edges)
        {
            int source = edge[0];
            int dst = edge[1];
            int weight = edge[2];

            matrix[source][dst] = weight;
            matrix[dst][source] = weight;
        }

        wershallAlgo(matrix, n);

        int cityCnt = n; //^ geater than no of city could be Int.MAX any
        int city = -1; //^ city from 0 to n so initially -1 
        for(int i=0;i<n;i++)
        {
            int cnt = 0;
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j] <= distanceThreshold)
                    cnt++;
            }

            if(cnt <= cityCnt)
            {
                cityCnt = cnt;
                city = i;
                System.out.println(cityCnt + " " + city);
            }
        }

        return city;
    }

}
