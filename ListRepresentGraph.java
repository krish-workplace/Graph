import java.util.ArrayList;
import java.util.Scanner;

class Graph
{
    ArrayList<ArrayList<Integer>> graph;
    int vertices,edges;
    Graph(int vertices, int edges)
    {
        this.vertices = vertices;
        this.edges = edges;
        graph = new ArrayList<>();

        for(int v = 0; v <= vertices;v++) // from 0 to verties 
        {
            graph.add(v, new ArrayList<>());
        }
    }

    void addEdge(int source,int destination)
    {
        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    void printGraph()
    {
        for(int s=1;s <= vertices;s++)
        {
            System.out.print(s + " -> ");
            for(int d=0; d < graph.get(s).size();d++)
            {
                System.out.print(graph.get(s).get(d) + " ");
            }
            System.out.println();
        }
    }
}


public class ListRepresentGraph {
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
