/**
 * Program Title: Prim's Algorithm for Minimum Spanning Tree (MST)
 * Author: [@chaanakyaaM]
 * Date: [2025-10-07]
 *
 * Description: Finds the Minimum Spanning Tree (MST) of a connected, undirected
 * weighted graph using a Priority Queue (Min-Heap) to efficiently select the
 * next cheapest edge.
 * Time Complexity: O(E log V) (where E is edges, V is vertices)
 * Space Complexity: O(V + E)
 */

import java.util.*;

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class PrimsAlgorithm {
    public static int prim(int n, List<List<Edge>> adj) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(0, 0)); // Start from node 0
        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to]) continue;

            visited[current.to] = true;
            mstWeight += current.weight;

            for (Edge neighbor : adj.get(current.to)) {
                if (!visited[neighbor.to]) {
                    pq.offer(neighbor);
                }
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes
        List<List<Edge>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding undirected edges
        addEdge(adj, 0, 1, 2);
        addEdge(adj, 0, 3, 6);
        addEdge(adj, 1, 2, 3);
        addEdge(adj, 1, 3, 8);
        addEdge(adj, 1, 4, 5);
        addEdge(adj, 2, 4, 7);
        addEdge(adj, 3, 4, 9);

        int result = prim(n, adj);
        System.out.println("Total weight of MST: " + result);
    }

    static void addEdge(List<List<Edge>> adj, int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w)); // Since the graph is undirected
    }
}
