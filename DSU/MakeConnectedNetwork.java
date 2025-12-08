package DSU;

class DSUByRank{
    private int parent[],rank[],components;

    DSUByRank(int n)
    {
        parent = new int[n];
        rank = new int[n];

        for(int i=0;i<n;i++)
        {
            parent[i] = i;
        }
        this.components = n;
    }

    void union(int u,int v)
    {
        //* 1 : find root parent of both u and v
        //* 2 : find the rank of root parent 
        //* 3 : union lower parent to higher parent and 
        //* 4 : update the parent and update the rank 
        
        int rootParentU = getRootParent(u);
        int rootParentV = getRootParent(v);

        if(rootParentU == rootParentV)
            return;
        
        if(rank[rootParentU] >= rank[rootParentV])
        {
            parent[rootParentV] = rootParentU;
            rank[rootParentU]++;
        }
        else
        {
            parent[rootParentU] = rootParentV;
            rank[rootParentV]++;
        }
        this.components--;
        
    }

    int getRootParent(int u)
    {
        if(u == parent[u])
            return u;

        parent[u] = getRootParent(parent[u]);
        return parent[u];
    }

    int getComponents()
    {
        return this.components;
    }

}


public class MakeConnectedNetwork {
    public int makeConnected(int n, int[][] connections) {
        
        
        if(connections.length < n-1)
            return -1;
        
        DSUByRank dsuByRank = new DSUByRank(n);
        // for make a connected component we required atleast n-1 edges if we have it then we can connected but for how many steps so we required component -1 steps 

        for(int i=0;i<connections.length;i++)
        {
            int u = connections[i][0];
            int v = connections[i][1];

            dsuByRank.union(u, v); 
        }

        return dsuByRank.getComponents() - 1;
    }

}
