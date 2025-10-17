/**
 * Program Title: Prim's Algorithm Interactive Visualizer
 * Author: [@Vishrut99]
 * Date: [2025-10-17]
 *
 * Description: Interactive Java Swing GUI application visualizing Prim's Algorithm 
 * for computing Minimum Spanning Trees on grid-based graphs with real-time edge 
 * weight calculation based on Euclidean distance.
 * 
 * Features:
 * - Interactive node selection and placement on grid
 * - Real-time adjacency matrix display
 * - Step-by-step MST construction visualization
 * - Color-coded edge highlighting during algorithm execution
 * - Total weight calculation and display
 * - Modern responsive UI with algorithm state management
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Represents a node/vertex in the graph with position coordinates
 */
class Node {
    int id;
    int x;
    int y;
    
    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Calculate Euclidean distance to another node
     */
    public double distanceTo(Node other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * Check if a point is within the node's bounds
     */
    public boolean contains(int px, int py, int nodeRadius) {
        int dx = px - x;
        int dy = py - y;
        return dx * dx + dy * dy <= nodeRadius * nodeRadius;
    }
}

/**
 * Represents an edge in the graph with weight
 */
class GraphEdge {
    int from;
    int to;
    double weight;
    boolean inMST;
    
    public GraphEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.inMST = false;
    }
}

/**
 * Manages the adjacency matrix and graph structure
 */
class AdjacencyMatrix {
    private double[][] matrix;
    private int size;
    
    public AdjacencyMatrix(int size) {
        this.size = size;
        this.matrix = new double[size][size];
        // Initialize with infinity (no connection)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
            }
        }
    }
    
    public void setEdge(int from, int to, double weight) {
        matrix[from][to] = weight;
        matrix[to][from] = weight; // Undirected graph
    }
    
    public double getWeight(int from, int to) {
        return matrix[from][to];
    }
    
    public int getSize() {
        return size;
    }
    
    public double[][] getMatrix() {
        return matrix;
    }
}

/**
 * Core algorithm logic for Prim's MST with step-by-step execution
 */
class PrimAlgorithmLogic {
    private List<Node> nodes;
    private AdjacencyMatrix adjMatrix;
    private boolean[] visited;
    private List<GraphEdge> mstEdges;
    private double totalWeight;
    private int currentStep;
    private boolean algorithmComplete;
    
    public PrimAlgorithmLogic(List<Node> nodes, AdjacencyMatrix adjMatrix) {
        this.nodes = nodes;
        this.adjMatrix = adjMatrix;
        this.visited = new boolean[nodes.size()];
        this.mstEdges = new ArrayList<>();
        this.totalWeight = 0;
        this.currentStep = 0;
        this.algorithmComplete = false;
    }
    
    /**
     * Initialize algorithm from starting node
     */
    public void initialize() {
        if (nodes.isEmpty()) return;
        visited[0] = true;
        currentStep = 1;
    }
    
    /**
     * Execute one step of Prim's algorithm
     */
    public boolean executeStep() {
        if (algorithmComplete || currentStep >= nodes.size()) {
            algorithmComplete = true;
            return false;
        }
        
        // Find minimum weight edge connecting visited to unvisited node
        double minWeight = Double.POSITIVE_INFINITY;
        int minFrom = -1;
        int minTo = -1;
        
        for (int i = 0; i < nodes.size(); i++) {
            if (!visited[i]) continue;
            
            for (int j = 0; j < nodes.size(); j++) {
                if (visited[j]) continue;
                
                double weight = adjMatrix.getWeight(i, j);
                if (weight < minWeight) {
                    minWeight = weight;
                    minFrom = i;
                    minTo = j;
                }
            }
        }
        
        if (minFrom != -1 && minTo != -1 && minWeight != Double.POSITIVE_INFINITY) {
            visited[minTo] = true;
            GraphEdge edge = new GraphEdge(minFrom, minTo, minWeight);
            edge.inMST = true;
            mstEdges.add(edge);
            totalWeight += minWeight;
            currentStep++;
            return true;
        }
        
        algorithmComplete = true;
        return false;
    }
    
    /**
     * Execute all remaining steps
     */
    public void executeAll() {
        if (!visited[0]) {
            initialize();
        }
        while (executeStep()) {
            // Continue until complete
        }
    }
    
    public boolean[] getVisited() {
        return visited;
    }
    
    public List<GraphEdge> getMstEdges() {
        return mstEdges;
    }
    
    public double getTotalWeight() {
        return totalWeight;
    }
    
    public boolean isAlgorithmComplete() {
        return algorithmComplete;
    }
    
    public void reset() {
        visited = new boolean[nodes.size()];
        mstEdges.clear();
        totalWeight = 0;
        currentStep = 0;
        algorithmComplete = false;
    }
}

/**
 * Main panel for graph visualization
 */
class GraphPanel extends JPanel {
    private List<Node> nodes;
    private List<GraphEdge> allEdges;
    private PrimAlgorithmLogic algorithm;
    private AdjacencyMatrix adjMatrix;
    
    private static final int NODE_RADIUS = 20;
    private static final Color NODE_COLOR = new Color(70, 130, 180);
    private static final Color NODE_VISITED_COLOR = new Color(60, 179, 113);
    private static final Color EDGE_COLOR = new Color(200, 200, 200);
    private static final Color MST_EDGE_COLOR = new Color(255, 69, 0);
    private static final Color BACKGROUND_COLOR = new Color(240, 248, 255);
    
    public GraphPanel() {
        nodes = new ArrayList<>();
        allEdges = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
        setBackground(BACKGROUND_COLOR);
    }
    
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
        repaint();
    }
    
    public void setEdges(List<GraphEdge> edges) {
        this.allEdges = edges;
        repaint();
    }
    
    public void setAlgorithm(PrimAlgorithmLogic algorithm) {
        this.algorithm = algorithm;
        repaint();
    }
    
    public List<Node> getNodes() {
        return nodes;
    }
    
    public int getNodeRadius() {
        return NODE_RADIUS;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw all edges first
        g2d.setStroke(new BasicStroke(2));
        for (GraphEdge edge : allEdges) {
            Node from = nodes.get(edge.from);
            Node to = nodes.get(edge.to);
            
            if (edge.inMST && algorithm != null) {
                g2d.setColor(MST_EDGE_COLOR);
                g2d.setStroke(new BasicStroke(4));
            } else {
                g2d.setColor(EDGE_COLOR);
                g2d.setStroke(new BasicStroke(2));
            }
            
            g2d.drawLine(from.x, from.y, to.x, to.y);
            
            // Draw edge weight
            int midX = (from.x + to.x) / 2;
            int midY = (from.y + to.y) / 2;
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            String weightStr = String.format("%.1f", edge.weight);
            g2d.drawString(weightStr, midX, midY);
        }
        
        // Draw nodes
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            
            // Determine node color based on visited status
            if (algorithm != null && algorithm.getVisited()[i]) {
                g2d.setColor(NODE_VISITED_COLOR);
            } else {
                g2d.setColor(NODE_COLOR);
            }
            
            g2d.fillOval(node.x - NODE_RADIUS, node.y - NODE_RADIUS, 
                        NODE_RADIUS * 2, NODE_RADIUS * 2);
            
            // Draw node border
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(node.x - NODE_RADIUS, node.y - NODE_RADIUS, 
                        NODE_RADIUS * 2, NODE_RADIUS * 2);
            
            // Draw node ID
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String label = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label, node.x - labelWidth / 2, node.y + labelHeight / 3);
        }
    }
}

/**
 * Panel for displaying adjacency matrix
 */
class MatrixPanel extends JPanel {
    private AdjacencyMatrix adjMatrix;
    private JTextArea matrixDisplay;
    
    public MatrixPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Adjacency Matrix"));
        setPreferredSize(new Dimension(300, 600));
        
        matrixDisplay = new JTextArea();
        matrixDisplay.setEditable(false);
        matrixDisplay.setFont(new Font("Monospaced", Font.PLAIN, 10));
        
        JScrollPane scrollPane = new JScrollPane(matrixDisplay);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void updateMatrix(AdjacencyMatrix matrix) {
        this.adjMatrix = matrix;
        StringBuilder sb = new StringBuilder();
        
        if (matrix == null) {
            matrixDisplay.setText("No graph created yet");
            return;
        }
        
        double[][] mat = matrix.getMatrix();
        int size = matrix.getSize();
        
        // Header
        sb.append("     ");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%6d", i));
        }
        sb.append("\n");
        
        // Matrix values
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%4d ", i));
            for (int j = 0; j < size; j++) {
                if (mat[i][j] == Double.POSITIVE_INFINITY) {
                    sb.append("   ∞  ");
                } else if (mat[i][j] == 0) {
                    sb.append("   0  ");
                } else {
                    sb.append(String.format("%6.1f", mat[i][j]));
                }
            }
            sb.append("\n");
        }
        
        matrixDisplay.setText(sb.toString());
    }
}

/**
 * Main application window
 */
public class PrimsAlgorithmVisualizer extends JFrame {
    private GraphPanel graphPanel;
    private MatrixPanel matrixPanel;
    private JPanel controlPanel;
    private JLabel statusLabel;
    private JLabel weightLabel;
    
    private List<Node> nodes;
    private List<GraphEdge> edges;
    private AdjacencyMatrix adjMatrix;
    private PrimAlgorithmLogic algorithm;
    
    private enum Mode { ADD_NODE, VIEW }
    private Mode currentMode = Mode.ADD_NODE;
    
    public PrimsAlgorithmVisualizer() {
        setTitle("Prim's Algorithm Visualizer - Interactive MST Construction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        
        // Initialize panels
        graphPanel = new GraphPanel();
        matrixPanel = new MatrixPanel();
        
        // Add mouse listener for node placement
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentMode == Mode.ADD_NODE) {
                    addNode(e.getX(), e.getY());
                }
            }
        });
        
        // Control panel
        controlPanel = createControlPanel();
        
        // Status panel
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusLabel = new JLabel("Status: Click to add nodes");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        weightLabel = new JLabel("Total MST Weight: 0.0");
        weightLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(statusLabel);
        statusPanel.add(weightLabel);
        
        // Layout
        add(graphPanel, BorderLayout.CENTER);
        add(matrixPanel, BorderLayout.EAST);
        add(controlPanel, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        JButton addNodesBtn = new JButton("Add Nodes Mode");
        addNodesBtn.addActionListener(e -> {
            currentMode = Mode.ADD_NODE;
            statusLabel.setText("Status: Click to add nodes");
        });
        
        JButton createGraphBtn = new JButton("Create Complete Graph");
        createGraphBtn.addActionListener(e -> createCompleteGraph());
        
        JButton initBtn = new JButton("Initialize Algorithm");
        initBtn.addActionListener(e -> initializeAlgorithm());
        
        JButton stepBtn = new JButton("Step");
        stepBtn.addActionListener(e -> executeStep());
        
        JButton runBtn = new JButton("Run All");
        runBtn.addActionListener(e -> runAll());
        
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> reset());
        
        JButton clearBtn = new JButton("Clear All");
        clearBtn.addActionListener(e -> clearAll());
        
        JButton generateRandomBtn = new JButton("Generate Random Graph");
        generateRandomBtn.addActionListener(e -> generateRandomGraph());
        
        panel.add(addNodesBtn);
        panel.add(createGraphBtn);
        panel.add(initBtn);
        panel.add(stepBtn);
        panel.add(runBtn);
        panel.add(resetBtn);
        panel.add(clearBtn);
        panel.add(generateRandomBtn);
        
        return panel;
    }
    
    private void addNode(int x, int y) {
        Node newNode = new Node(nodes.size(), x, y);
        nodes.add(newNode);
        graphPanel.setNodes(nodes);
        statusLabel.setText("Status: Node " + (nodes.size() - 1) + " added. Total nodes: " + nodes.size());
    }
    
    private void createCompleteGraph() {
        if (nodes.size() < 2) {
            JOptionPane.showMessageDialog(this, 
                "Please add at least 2 nodes before creating a graph.", 
                "Insufficient Nodes", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        edges.clear();
        adjMatrix = new AdjacencyMatrix(nodes.size());
        
        // Create complete graph with Euclidean distance weights
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                double weight = nodes.get(i).distanceTo(nodes.get(j));
                GraphEdge edge = new GraphEdge(i, j, weight);
                edges.add(edge);
                adjMatrix.setEdge(i, j, weight);
            }
        }
        
        graphPanel.setEdges(edges);
        matrixPanel.updateMatrix(adjMatrix);
        statusLabel.setText("Status: Complete graph created with " + edges.size() + " edges");
        currentMode = Mode.VIEW;
    }
    
    private void initializeAlgorithm() {
        if (adjMatrix == null || nodes.size() < 2) {
            JOptionPane.showMessageDialog(this, 
                "Please create a graph first.", 
                "No Graph", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        algorithm = new PrimAlgorithmLogic(nodes, adjMatrix);
        algorithm.initialize();
        graphPanel.setAlgorithm(algorithm);
        statusLabel.setText("Status: Algorithm initialized. Starting from node 0");
        updateWeightDisplay();
    }
    
    private void executeStep() {
        if (algorithm == null) {
            JOptionPane.showMessageDialog(this, 
                "Please initialize the algorithm first.", 
                "Algorithm Not Initialized", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean stepped = algorithm.executeStep();
        
        if (stepped) {
            // Update edges to show MST progress
            for (GraphEdge edge : edges) {
                edge.inMST = false;
            }
            for (GraphEdge mstEdge : algorithm.getMstEdges()) {
                for (GraphEdge edge : edges) {
                    if ((edge.from == mstEdge.from && edge.to == mstEdge.to) ||
                        (edge.from == mstEdge.to && edge.to == mstEdge.from)) {
                        edge.inMST = true;
                        break;
                    }
                }
            }
            graphPanel.repaint();
            statusLabel.setText("Status: Step executed. MST edges: " + algorithm.getMstEdges().size());
            updateWeightDisplay();
        } else {
            statusLabel.setText("Status: Algorithm complete! MST constructed.");
        }
    }
    
    private void runAll() {
        if (algorithm == null) {
            initializeAlgorithm();
        }
        
        algorithm.executeAll();
        
        // Update all edges
        for (GraphEdge edge : edges) {
            edge.inMST = false;
        }
        for (GraphEdge mstEdge : algorithm.getMstEdges()) {
            for (GraphEdge edge : edges) {
                if ((edge.from == mstEdge.from && edge.to == mstEdge.to) ||
                    (edge.from == mstEdge.to && edge.to == mstEdge.from)) {
                    edge.inMST = true;
                    break;
                }
            }
        }
        
        graphPanel.repaint();
        statusLabel.setText("Status: Algorithm complete! MST constructed.");
        updateWeightDisplay();
    }
    
    private void reset() {
        if (algorithm != null) {
            algorithm.reset();
            
            // Clear MST markings
            for (GraphEdge edge : edges) {
                edge.inMST = false;
            }
            
            graphPanel.setAlgorithm(algorithm);
            graphPanel.repaint();
            statusLabel.setText("Status: Algorithm reset. Click 'Initialize' to start again.");
            updateWeightDisplay();
        }
    }
    
    private void clearAll() {
        nodes.clear();
        edges.clear();
        adjMatrix = null;
        algorithm = null;
        
        graphPanel.setNodes(nodes);
        graphPanel.setEdges(edges);
        graphPanel.setAlgorithm(null);
        matrixPanel.updateMatrix(null);
        
        currentMode = Mode.ADD_NODE;
        statusLabel.setText("Status: All cleared. Click to add nodes");
        weightLabel.setText("Total MST Weight: 0.0");
    }
    
    private void generateRandomGraph() {
        int nodeCount = 6 + (int)(Math.random() * 5); // 6-10 nodes
        nodes.clear();
        
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int margin = 50;
        
        // Generate random node positions
        Random rand = new Random();
        for (int i = 0; i < nodeCount; i++) {
            int x = margin + rand.nextInt(panelWidth - 2 * margin);
            int y = margin + rand.nextInt(panelHeight - 2 * margin);
            nodes.add(new Node(i, x, y));
        }
        
        graphPanel.setNodes(nodes);
        statusLabel.setText("Status: Generated " + nodeCount + " random nodes. Click 'Create Complete Graph'");
        currentMode = Mode.VIEW;
    }
    
    private void updateWeightDisplay() {
        if (algorithm != null) {
            weightLabel.setText(String.format("Total MST Weight: %.2f", algorithm.getTotalWeight()));
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            PrimsAlgorithmVisualizer visualizer = new PrimsAlgorithmVisualizer();
            visualizer.setVisible(true);
        });
    }
}
