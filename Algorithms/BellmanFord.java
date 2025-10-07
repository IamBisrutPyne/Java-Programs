import java.util.Arrays;

class BellmanFord {

    // Class to represent an edge
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    // Function to run the Bellman-Ford algorithm
    public static void bellmanFord(Edge[] edges, int V, int source) {
        // Step 1: Initialize distances from source to all other vertices as INFINITE
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight < dist[edge.v]) {
                    dist[edge.v] = dist[edge.u] + edge.weight;
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight < dist[edge.v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Step 4: Print the distance array
        System.out.println("Vertex Distance from Source (" + source + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INFINITY" : dist[i]));
        }
    }

    public static void main(String[] args) {
        // Example graph (edges with weights)
        Edge[] edges = {
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3),
            new Edge(4, 5, 1),
            new Edge(5, 4, -2)
        };

        int V = 6; // Number of vertices
        int source = 0; // Source vertex

        BellmanFord bellmanFord = new BellmanFord();
        bellmanFord.bellmanFord(edges, V, source);
    }
}
