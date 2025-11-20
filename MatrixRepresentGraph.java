import java.util.Scanner;

class Graph
{
    int graph[][];
    int vertices,edges;
    Graph(int vertexes, int edges)
    {
        this.vertices = vertexes;
        this.edges = edges;
        graph = new int[vertexes + 1][vertexes + 1];
    }

    void addEdge(int source,int destination)
    {
        graph[source][destination] = 1;
        graph[destination][source] = 1;
    }

    void printGraph()
    {
        for(int s=1;s <= vertices;s++)
        {
            for(int d = 1;d <= vertices;d++)
            {
                System.out.print(graph[s][d] + " ");
            }
            System.out.println();
        }
    }
}

public class MatrixRepresentGraph {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter No of vertices : ");
        int vertices = sc.nextInt();

        System.out.println("Enter No of edges : ");
        int edges = sc.nextInt();

        Graph graph = new Graph(vertices, edges);

        System.out.println("Now enter in this format (source,destination) for craeteing edge between these two vertex");

        for(int e=1;e<=edges;e++)
        {
            // System.out.println("Source veretx is " + v);
            int src = sc.nextInt();
            int dest = sc.nextInt();

            graph.addEdge(src, dest);
        }

        graph.printGraph();

        sc.close();
    }
    
}