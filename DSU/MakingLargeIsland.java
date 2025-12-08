package DSU;

import java.util.HashSet;
import java.util.Set;

class DSUByRank {

    private int rank[], parent[];
    int rootParentSize[];
    int components;

    DSUByRank(int components) {
        this.components = components;

        parent = new int[components];
        rank = new int[components];
        rootParentSize = new int[components];

        for (int i = 0; i < components; i++) {
            rootParentSize[i] = 1;
            parent[i] = i;
        }
    }

    void union(int u, int v) {
        // 1: find the parent f both u and v
        //2: find rank of those parent 
        // 3: union smaller to larger one 

        //4: update the rank

        int rootParentU = getRootParent(u);
        int rootParentV = getRootParent(v);

        //check if aleady in same component 
        if (rootParentU == rootParentV)
            return;

        //compare rank of both rootparent 
        if (rank[rootParentU] > rank[rootParentV]) {
            parent[rootParentV] = rootParentU;
            // rank[rootParentU]++;
            rootParentSize[rootParentU] += rootParentSize[rootParentV];
        } else if (rank[rootParentU] < rank[rootParentV]) {
            parent[rootParentU] = rootParentV;
            // rank[rootParentV]++;
            rootParentSize[rootParentV] += rootParentSize[rootParentU];
        } else {
            //we can add in any one but create standard like add in U
            parent[rootParentV] = rootParentU;
            rank[rootParentU]++; //! increse rank whne both have same rank
            rootParentSize[rootParentU] += rootParentSize[rootParentV];
        }
        components--;

    }

    int getRootParent(int u) {
        if (u == parent[u])
            return u;

        parent[u] = getRootParent(parent[u]);
        return parent[u];
    }
}

public class MakingLargeIsland {

    public int largestIsland(int[][] grid) {

        int cnt = 0;
        int n = grid.length;
        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };

        DSUByRank dsuByRank = new DSUByRank(n * n);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int node = (i * n) + j;
                if (grid[i][j] == 1) {
                    // check all four neighbbour
                    for (int k = 0; k < 4; k++) {
                        int nrow = i + drow[k];
                        int ncol = j + dcol[k];

                        int neighbourNode = (nrow * n) + ncol;

                        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 1) {
                            // union
                            dsuByRank.union(node, neighbourNode);
                        }
                    }
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    // check all four neighbour
                    for (int k = 0; k < 4; k++) {
                        int nrow = i + drow[k];
                        int ncol = j + dcol[k];
                        int neighbourNode = (nrow * n) + ncol;
                        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 1) {

                            // use Set Data Structure
                            int rootParent = dsuByRank.getRootParent(neighbourNode);
                            set.add(rootParent);
                        }
                    }
                    int totalSize = 0;
                    for (Integer parent : set) {
                        totalSize += dsuByRank.rootParentSize[parent];
                    }
                    cnt = Math.max(cnt, (1 + totalSize));
                    set.clear();
                }
            }
        }
        // After checking all 0's
        if (cnt == 0) {
            return n * n; // grid already full of 1s
        }

        return cnt;

    }
}
