import java.util.Arrays;

class BellmanFord {

    // Function to run the Bellman-Ford algorithm
    public static void bellmanFord(int graph[][], int V, int E, int source) {
        // Step 1: Initialize distances from source to all other vertices as INFINITE
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        // Step 4: Print the distance array
        System.out.println("Vertex Distance from Source (" + source + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INFINITY" : dist[i]));
        }
    }

    public static void main(String[] args) {
        // Example graph (adjacency matrix)
        int graph[][] = {
            {0, -1, 4, 0, 0, 0},
            {-1, 0, 3, 2, 2, 0},
            {4, 3, 0, 0, 0, 0},
            {0, 2, 0, 0, 3, 0},
            {0, 2, 0, 3, 0, 1},
            {0, 0, 0, 0, 1, 0}
        };

        int V = 6; // Number of vertices
        int E = 8; // Number of edges

        int source = 0; // Source vertex

        BellmanFord bellmanFord = new BellmanFord();
        bellmanFord.bellmanFord(graph, V, E, source);
    }
}
