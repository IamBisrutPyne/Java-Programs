/**
 * Program Title: Bellman-Ford Shortest Path Algorithm
 * Author: [chaanakyaaM]
 * Date: 2025-10-08
 *
 * Description: Computes the shortest path from a single source vertex to all other
 * vertices in a weighted, directed graph. It handles graphs with negative edge
 * weights but is crucial for detecting and reporting negative cycles.
 *
 * Time Complexity: O(V * E) (Vertices * Edges)
 * Space Complexity: O(V + E) (for the distance array and edge list)
 */

import java.util.Arrays;

public class BellmanFord {

    // Structure to represent a weighted edge
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Function that implements the Bellman-Ford algorithm
    public static void bellmanFord(Edge[] edges, int V, int E, int source) {
        // Initialize distances from source to all vertices as INFINITE
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].source;
                int v = edges[j].destination;
                int weight = edges[j].weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (int i = 0; i < E; i++) {
            int u = edges[i].source;
            int v = edges[i].destination;
            int weight = edges[i].weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print the distances
        printSolution(distance);
    }

    // Utility function to print the distance array
    public static void printSolution(int[] distance) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println(i + "        INF");
            } else {
                System.out.println(i + "        " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        // Number of vertices and edges
        int V = 5;  // Number of vertices
        int E = 8;  // Number of edges

        // List of edges (source, destination, weight)
        Edge[] edges = new Edge[E];
        edges[0] = new Edge(0, 1, -1);
        edges[1] = new Edge(0, 2, 4);
        edges[2] = new Edge(1, 2, 3);
        edges[3] = new Edge(1, 3, 2);
        edges[4] = new Edge(1, 4, 2);
        edges[5] = new Edge(3, 2, 5);
        edges[6] = new Edge(3, 1, 1);
        edges[7] = new Edge(4, 3, -3);

        // Source vertex
        int source = 0;

        // Run Bellman-Ford algorithm
        bellmanFord(edges, V, E, source);
    }
}
