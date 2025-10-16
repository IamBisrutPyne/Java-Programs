/**
 * Program Title: A-Star Search (A*) for 2D Grid
 * Author: v-technoid
 * Date: 2025-10-13
 *
 * Description: Implements the A* pathfinding algorithm to find the shortest path between two points 
 * in a 2D grid with obstacles, utilizing the Manhattan distance as the heuristic (h-cost).
 *
 * Language: Java
 *
 * Time Complexity: O(N log N) (where N is the number of cells/nodes)
 * Space Complexity: O(N)
 */

import java.util.*;

/**
 * Represents a node in the search grid. Each node has coordinates (row, col),
 * costs (g, h, f), and a parent node to reconstruct the path.
 * The class implements Comparable to be used in the PriorityQueue, ordering
 * nodes by their f-cost.
 */

class Node implements Comparable<Node> {
    int row, col;
    int g; // Cost from the start node to this node
    int h; // Heuristic: estimated cost from this node to the end node
    int f; // Total cost: g + h
    Node parent;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.g = Integer.MAX_VALUE;
        this.h = 0;
        this.f = Integer.MAX_VALUE;
        this.parent = null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

/**
 * Implements the A* (A-star) search algorithm to find the shortest path in a 2D grid
 * * ---
 * Complexity
 * ---
 * Time Complexity: O(E log V)
 * 1. Where V is the number of vertices (nodes/cells) and E is the number of edges
 * 2. The log V factor comes from the PriorityQueue operations (add/poll).
 * 3. On a standard grid, this simplifies to O(N log N), where N is the total number of cells
 *
 * Space Complexity: O(V)
 * The space is required to store the nodes in the open and closed lists.
 * In the worst case, this could be all nodes. On a grid, this simplifies to O(N), where N is the total number of cells.
 */

public class AStarSearch {

    // Cost for moving horizontally or vertically
    private static final int HV_COST = 10;

    private Node[][] grid;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private int startRow, startCol;
    private int endRow, endCol;

    public AStarSearch(int rows, int cols, int startRow, int startCol, int endRow, int endCol, int[][] blocks) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        // Initialize grid and nodes
        this.grid = new Node[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Node(i, j);
            }
        }

        // Set blocked cells
        for (int[] block : blocks) {
            grid[block[0]][block[1]] = null; // Mark blocked cells as null
        }

        // Initialize the open list (priority queue) and closed set
        this.openList = new PriorityQueue<>();
        this.closedSet = new HashSet<>();
    }

    /**
     * Calculates the heuristic (Manhattan distance) from a node to the end node
     */
    private int calculateHeuristic(Node node) {
        return Math.abs(node.row - endRow) + Math.abs(node.col - endCol);
    }

    /**
     * Executes the A* search algorithm.
     * @return A list of nodes representing the shortest path, or null if no path is found
     */
    public List<Node> findPath() {
        Node startNode = grid[startRow][startCol];
        Node endNode = grid[endRow][endCol];

        // Initialize the start node
        startNode.g = 0;
        startNode.h = calculateHeuristic(startNode);
        startNode.f = startNode.g + startNode.h;
        openList.add(startNode);

        // Main search loop
        while (!openList.isEmpty()) {
            // Get the node with the lowest f-cost from the priority queue
            Node currentNode = openList.poll();

            // If we reached the end, reconstruct and return the path
            if (currentNode.equals(endNode)) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            // Explore neighbors
            exploreNeighbors(currentNode, endNode);
        }

        // No path found
        return null;
    }

    private void exploreNeighbors(Node currentNode, Node endNode) {
        int[] dr = {-1, 1, 0, 0}; // Directions: Up, Down, Left, Right
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = currentNode.row + dr[i];
            int newCol = currentNode.col + dc[i];

            // Check if neighbor is valid
            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                || grid[newRow][newCol] == null || closedSet.contains(grid[newRow][newCol])) {
                continue;
            }

            Node neighbor = grid[newRow][newCol];
            int tentativeG = currentNode.g + HV_COST;

            // If this path to the neighbor is better, update it
            if (tentativeG < neighbor.g) {
                neighbor.parent = currentNode;
                neighbor.g = tentativeG;
                neighbor.h = calculateHeuristic(neighbor);
                neighbor.f = neighbor.g + neighbor.h;

                if (!openList.contains(neighbor)) {
                    openList.add(neighbor);
                }
            }
        }
    }

    /**
     * Reconstructs the path from the end node by following parent pointers
     */
    private List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // --- Define the Grid and Obstacles ---
        int rows = 10;
        int cols = 10;
        int startRow = 0, startCol = 0;
        int endRow = 7, endCol = 8;
        
        // Blocked cells are defined by {row, col}
        int[][] blocks = new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 4}, {3, 4}, {4, 4}, {5, 4}, {5, 3}, {5, 2}, {5, 1}, {7, 6}, {7, 7}};

        // --- Run A* Search ---
        AStarSearch aStar = new AStarSearch(rows, cols, startRow, startCol, endRow, endCol, blocks);
        List<Node> path = aStar.findPath();

        // --- Print the Result ---
        char[][] displayGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(displayGrid[i], '.');
        }
        displayGrid[startRow][startCol] = 'S';
        displayGrid[endRow][endCol] = 'E';
        for (int[] block : blocks) {
            displayGrid[block[0]][block[1]] = '#';
        }

        if (path != null) {
            System.out.println("Path found!");
            for (Node node : path) {
                if (displayGrid[node.row][node.col] == '.') {
                    displayGrid[node.row][node.col] = '*';
                }
            }
        } else {
            System.out.println("No path found.");
        }

        // Print the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(displayGrid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
