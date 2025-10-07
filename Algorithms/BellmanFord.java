import java.util.Arrays;

public class BellmanFord {

    public static void bellmanFord(int[][] graph, int vertices, int source) {
        // Step 1: Initialize distances from source to all vertices as INFINITE
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0; // Distance from source to itself is always 0

        // Step 2: Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (int u = 0; u < vertices; u++) {
                for (int v = 0; v < vertices; v++) {
                    if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                        distance[v] = distance[u] + graph[u][v];
                    }
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int u = 0; u < vertices; u++) {
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        // Print the distances from source to all other vertices
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t\t " + (distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]));
        }
    }

    public static void main(String[] args) {
        // Example graph: graph[u][v] represents the weight of the edge from u to v
        int vertices = 5;
        int[][] graph = {
            {0, -1, 4, 0, 0},
            {0, 0, 3, 2, 2},
            {0, 0, 0, 0, 0},
            {0, 1, 5, 0, 0},
            {0, 0, 0, -3, 0}
        };

        int source = 0; 
        bellmanFord(graph, vertices, source);
    }
}
