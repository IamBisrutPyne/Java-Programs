// Tarjan’s Algorithm in Java
// ------------------------------------------------------
// Author: Jatin Vishwakarma
// Description: Implementation of Tarjan’s Algorithm for finding
// Strongly Connected Components (SCCs) in a directed graph.
// This algorithm uses DFS and low-link values to efficiently
// detect all SCCs in O(V + E) time complexity.

import java.util.*;

public class TarjanSCC {
    private final int V; 
    private final List<List<Integer>> adj; 
    private int time; 
    private final int[] disc; 
    private final int[] low; 
    private final boolean[] stackMember; 
    private final Stack<Integer> stack; 

    public TarjanSCC(int vertices) {
        V = vertices;
        adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        disc = new int[vertices];
        low = new int[vertices];
        stackMember = new boolean[vertices];
        stack = new Stack<>();
        Arrays.fill(disc, -1); 
        Arrays.fill(low, -1);
    }

    // Add a directed edge from u to v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    private void SCCUtil(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        stackMember[u] = true;

        for (int v : adj.get(u)) {
            if (disc[v] == -1) { // If v is not visited
                SCCUtil(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) { // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // If u is the root of an SCC
        if (low[u] == disc[u]) {
            System.out.print("SCC: ");
            while (true) {
                int v = stack.pop();
                stackMember[v] = false;
                System.out.print(v + " ");
                if (v == u) break;
            }
            System.out.println();
        }
    }

    public void findSCCs() {
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                SCCUtil(i);
        }
    }

    public static void main(String[] args) {
        TarjanSCC g = new TarjanSCC(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 3);
        g.addEdge(5, 6);

        System.out.println("Strongly Connected Components in the given graph:");
        g.findSCCs();
    }
}
