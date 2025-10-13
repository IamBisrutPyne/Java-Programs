// src/graph/a_star/AStar.java

import java.util.*;

public class AStar {

    public static class Node implements Comparable<Node> {
        public final String id;
        public double g;   // cost from start
        public double f;   // g + heuristic
        public Node parent;

        public Node(String id) {
            this.id = id;
            g = Double.POSITIVE_INFINITY;
            f = Double.POSITIVE_INFINITY;
            parent = null;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.f, other.f);
        }

        @Override
        public String toString() { return id; }
    }

    // Adjacency list with edge weights
    public static Map<String, Map<String, Double>> graph = new HashMap<>();

    public static void addEdge(String u, String v, double w) {
        graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, w);
        graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, w);
    }

    public interface Heuristic {
        double estimate(String a, String b);
    }

    public static List<String> aStar(String start, String goal, Heuristic h) {
        Map<String, Node> nodes = new HashMap<>();
        for (String id : graph.keySet()) nodes.put(id, new Node(id));
        nodes.putIfAbsent(start, new Node(start));
        nodes.putIfAbsent(goal, new Node(goal));

        PriorityQueue<Node> open = new PriorityQueue<>();
        Node s = nodes.get(start);
        s.g = 0;
        s.f = h.estimate(start, goal);
        open.add(s);

        Set<String> closed = new HashSet<>();

        while (!open.isEmpty()) {
            Node current = open.poll();
            if (current.id.equals(goal)) {
                LinkedList<String> path = new LinkedList<>();
                for (Node it = current; it != null; it = it.parent) path.addFirst(it.id);
                return path;
            }
            closed.add(current.id);

            for (var e : graph.getOrDefault(current.id, Collections.emptyMap()).entrySet()) {
                String neighId = e.getKey();
                double w = e.getValue();
                if (closed.contains(neighId)) continue;

                Node neighbor = nodes.computeIfAbsent(neighId, Node::new);
                double tentativeG = current.g + w;
                if (tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.f = tentativeG + h.estimate(neighId, goal);
                    open.remove(neighbor);
                    open.add(neighbor);
                }
            }
        }
        return Collections.emptyList(); // no path found
    }

    public static void main(String[] args) {
        addEdge("A", "B", 1);
        addEdge("B", "C", 2);
        addEdge("A", "C", 4);

        List<String> path = aStar("A", "C", (a, b) -> 0); // heuristic=0 => Dijkstra
        System.out.println("Shortest path: " + path);
    }
}