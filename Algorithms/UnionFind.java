// Metadata Header (MANDATORY)
// -----------------------------
// Program Title: Union-Find (Disjoint Set Union)
// Author: [KotlinKing]
// Date: 2025-10-10
//
// Description: Implements the Disjoint Set Union data structure, or Union-Find.
// It includes the two essential optimizations: Path Compression and Union by Rank.
//
// Language: Java
//
// Time Complexity: O(α(n)) for find/union.
// Space Complexity: O(n).
// -----------------------------

public class UnionFind {
    private int[] parent;
    private int[] rank;

    // Initialize n disjoint sets
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find root of x with path compression
    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union two sets by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false; // already in same set

        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println("Are 0 and 2 connected? " + (uf.find(0) == uf.find(2))); // true
        System.out.println("Are 0 and 3 connected? " + (uf.find(0) == uf.find(3))); // false
    }
}
