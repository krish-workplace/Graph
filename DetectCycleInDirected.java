public class DetectCycleInDirected {

    private boolean dfs(int[][] edges, boolean[] visited, boolean[] pathVisited, int src) {
        // make the visited
        visited[src] = true;
        // mark the path visited
        pathVisited[src] = true;

        // go to depth
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (u == src) {
                if (!visited[v]) {
                    if (dfs(edges, visited, pathVisited, v)) {
                        return true;
                    }
                } else if (pathVisited[v]) {
                    return true;
                }
            }
        }

        // when path is finished from an src so then mark as false
        pathVisited[src] = false;

        return false;
    }

    public boolean isCyclic(int V, int[][] edges) {

        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        // code here
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(edges, visited, pathVisited, i) == true)
                    return true;
            }
        }

        return false;
    }

}
