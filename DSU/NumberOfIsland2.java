package DSU;

class DSUByRank{

    private int rank[],parent[];
    int components;

    DSUByRank(int components)
    {
        this.components = components;
        rank  = new int[components];
        parent = new int[components];
        
        //intially all are it self parent 
        for(int i=0;i<components;i++)
        {
            parent[i] = i;
        }
    }

    void union(int u,int v)
    {
        // 1: find the parent f both u and v
        //2: find rank of those parent 
        // 3: union smaller to larger one 

        //4: update the rank

        int rootParentU = getRootParent(u);
        int rootParentV = getRootParent(v);

        //check if aleady in same component 
        if(rootParentU == rootParentV)
            return;

        //compare rank of both rootparent 
        if(rank[rootParentU] > rank[rootParentV])
        {
            parent[rootParentV] = rootParentU;
            // rank[rootParentU]++;
        }
        else if(rank[rootParentU] < rank[rootParentV])
        {
            parent[rootParentU] = rootParentV;
            // rank[rootParentV]++;
        }
        else
        {
            //we can add in any one but create standard like add in U
            parent[rootParentV] = rootParentU;
            rank[rootParentU]++;
        }
        components--;
        
    }

    int getRootParent(int u)
    {
        if(u == parent[u])
            return u;

        parent[u] = getRootParent(parent[u]); // path compression
        return parent[u];
    }
}

public class NumberOfIsland2 {

    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        // Write your code here.

        boolean[][] island = new boolean[n][m];
        int cnt = 0;

        int[] ans = new int[q.length];

        DSUByRank dsuByRank = new DSUByRank(m*n);

        for(int i=0;i<q.length;i++)
        {
            int row = q[i][0];
            int col = q[i][1];
            
            int node = (row * m) + col;

            if(island[row][col])
            {
                //already visited so no need to do anything 
                ans[i] = cnt;
            }
            else
            {
                island[row][col] = true;
                cnt++;

                int[] drow = {-1,0,1,0};
                int[] dcol = {0,1,0,-1};
                for(int k=0;k<4;k++)
                {
                    int nrow = row + drow[k];
                    int ncol = col + dcol[k];

                    if(nrow >=0 && nrow<n && ncol>=0 && ncol<m && island[nrow][ncol])
                    {
                        int neighbourNode = (nrow * m) + ncol;

                        //check if node is not already a part of it via different neoghbour 
                        if(dsuByRank.getRootParent(node) != dsuByRank.getRootParent(neighbourNode))
                        {
                            dsuByRank.union(node, neighbourNode);
                            cnt--;
                        }
                    }
                }
                ans[i] = cnt;
            }
        }

        return ans;
    }

}
