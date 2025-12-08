package DSU;

class DSUByRank {

    private int rank[],parent[],components;

    DSUByRank(int nodes)
    {
        rank = new int[nodes];
        parent = new int[nodes];

        for(int i=0;i<nodes;i++)
        {
            parent[i] = i;
        }
        this.components = nodes;
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

    int getComponents()
    {
        return this.components;
    }

    int getRootParent(int u)
    {
        // repeat untill got node parent of it self
        if(u == parent[u])
        {
            return u;
        }
        parent[u] = getRootParent(parent[u]); // do the path compression 
        return parent[u];
    }

    public static void main(String[] args) {
        
    }    
}