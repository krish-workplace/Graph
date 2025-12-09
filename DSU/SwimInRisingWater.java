package DSU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DSUByRank{

    private int parent[],rank[];
    int components;

    DSUByRank(int components)
    {
        this.components  =components;
        parent = new int[components];
        rank = new int[components];

        for(int i=0;i<components;i++)
        {
            parent[i] = i;
        }
    }

    void union(int u,int v)
    {
        int rootParentU = getRootParent(u);
        int rootParentV = getRootParent(v);

        if(rootParentU == rootParentV)
            return;

        if(rank[rootParentU] >  rank[rootParentV])
        {
            parent[rootParentV] = rootParentU;
        }
        else if(rank[rootParentV] > rank[rootParentU])
        {
            parent[rootParentU] = rootParentV;
        }
        else
        {
            parent[rootParentV] = rootParentU;
            rank[rootParentU]++; 
        }
        this.components--;
    }

    int getRootParent(int u)
    {
        if(u == parent[u])
            return u;

        parent[u] = getRootParent(parent[u]); // path compression
        return parent[u];
    }
}

public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean[][] activate = new boolean[n][n];

        DSUByRank dsuByRank = new DSUByRank(n*n);

        List<int[]> list =  new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                list.add(new int[]{grid[i][j],i,j});
            }
        }

        Collections.sort(list,(a,b) -> a[0] - b[0]);

        int start  = 0;
        int end = (n*n) -1;
        for(int[] cell : list)
        {
            int time = cell[0];
            int row = cell[1];
            int col = cell[2];

            //activate cell
            activate[row][col] = true;

            int node = (row * n) + col;

            int[] drow = {-1,0,1,0};
            int[] dcol = {0,1,0,-1};

            for(int k=0;k<4;k++)
            {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<n && activate[nrow][ncol])
                {
                    int neighbourNode = (nrow * n) + ncol;
                    dsuByRank.union(node, neighbourNode);
                }
            }

            if(dsuByRank.getRootParent(start) == dsuByRank.getRootParent(end))
                return time;
        }

        return -1;
    }

}
