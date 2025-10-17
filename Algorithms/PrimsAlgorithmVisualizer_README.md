# Prim's Algorithm Visualizer

An interactive Java Swing GUI application that visualizes Prim's Algorithm for computing Minimum Spanning Trees (MST) on grid-based graphs with real-time edge weight calculation based on Euclidean distance.

## Features

### 🎨 Interactive Visualization
- **Node Placement**: Click anywhere on the canvas to add nodes interactively
- **Visual Graph Construction**: Creates a complete graph with edges connecting all nodes
- **Real-time Updates**: Watch the MST construction process step-by-step
- **Color-Coded Display**: 
  - Blue nodes: Unvisited nodes
  - Green nodes: Visited nodes (in MST)
  - Gray edges: Regular graph edges
  - Red edges: Edges included in the MST

### 🧮 Algorithm Features
- **Euclidean Distance Calculation**: Edge weights automatically calculated based on node positions
- **Adjacency Matrix Display**: Live view of the graph's adjacency matrix
- **Step-by-Step Execution**: Execute the algorithm one step at a time to understand the process
- **Run All**: Complete the MST construction instantly
- **Total Weight Calculation**: Real-time display of the MST's total weight

### 🏗️ Object-Oriented Architecture
- **Node Class**: Represents vertices with position coordinates
- **GraphEdge Class**: Represents weighted edges between nodes
- **AdjacencyMatrix Class**: Manages the graph structure and connections
- **PrimAlgorithmLogic Class**: Handles the MST algorithm logic with state management
- **GraphPanel Class**: Renders the visual representation
- **MatrixPanel Class**: Displays the adjacency matrix

## How to Use

### 1. Compile the Program
```bash
javac PrimsAlgorithmVisualizer.java
```

### 2. Run the Application
```bash
java PrimsAlgorithmVisualizer
```

### 3. Using the Interface

#### Adding Nodes
1. Click "Add Nodes Mode" button (if not already in this mode)
2. Click anywhere on the canvas to place nodes
3. Each node is automatically numbered (0, 1, 2, ...)

#### Creating the Graph
1. After adding at least 2 nodes, click "Create Complete Graph"
2. The application creates a complete graph where every node is connected to every other node
3. Edge weights are calculated using Euclidean distance
4. The adjacency matrix is displayed on the right panel

#### Running the Algorithm

**Option 1: Step-by-Step**
1. Click "Initialize Algorithm" to start from node 0
2. Click "Step" repeatedly to execute one iteration at a time
3. Observe how the algorithm selects the minimum weight edge at each step
4. Watch nodes turn green and edges turn red as they're added to the MST

**Option 2: Run All**
1. Click "Run All" to execute the entire algorithm at once
2. The complete MST is displayed immediately

#### Other Controls
- **Reset**: Clears the algorithm state but keeps the graph
- **Clear All**: Removes everything and starts fresh
- **Generate Random Graph**: Creates a random graph with 6-10 nodes automatically

## Algorithm Details

### Prim's Algorithm
The visualizer implements Prim's Algorithm, which constructs a Minimum Spanning Tree by:

1. Starting from an arbitrary node (node 0)
2. Repeatedly selecting the minimum weight edge that connects a visited node to an unvisited node
3. Adding the selected edge and node to the MST
4. Continuing until all nodes are included

**Time Complexity**: O(V²) for the matrix-based implementation
**Space Complexity**: O(V²) for the adjacency matrix

### Euclidean Distance Formula
Edge weights are calculated using the Euclidean distance formula:

```
weight = √((x₂ - x₁)² + (y₂ - y₁)²)
```

Where (x₁, y₁) and (x₂, y₂) are the coordinates of the two nodes.

## User Interface Components

### Main Canvas (Left)
- Interactive drawing area for node placement
- Visual representation of nodes and edges
- Real-time algorithm execution display

### Adjacency Matrix (Right)
- Shows the complete adjacency matrix
- Displays edge weights between all node pairs
- ∞ symbol indicates no direct connection (in non-complete graphs)

### Control Panel (Top)
Eight buttons for complete control:
1. **Add Nodes Mode**: Enable node placement
2. **Create Complete Graph**: Generate all edges
3. **Initialize Algorithm**: Prepare for execution
4. **Step**: Execute one algorithm iteration
5. **Run All**: Complete the algorithm
6. **Reset**: Clear algorithm state
7. **Clear All**: Remove everything
8. **Generate Random Graph**: Create random nodes

### Status Panel (Bottom)
- Status bar: Shows current operation and progress
- Weight display: Shows total MST weight

## Example Workflow

```
1. Launch application
2. Click on canvas to add 5-6 nodes
3. Click "Create Complete Graph"
4. Observe the adjacency matrix on the right
5. Click "Initialize Algorithm"
6. Click "Step" multiple times to watch the MST being built
7. Observe:
   - Green nodes (visited)
   - Red edges (in MST)
   - Total weight increasing
8. Click "Reset" to try again
9. Click "Run All" to see the complete MST instantly
```

## Technical Implementation

### Class Hierarchy
```
PrimsAlgorithmVisualizer (JFrame)
├── GraphPanel (JPanel)
├── MatrixPanel (JPanel)
└── Control Panel (JPanel)

Supporting Classes:
├── Node
├── GraphEdge
├── AdjacencyMatrix
└── PrimAlgorithmLogic
```

### Key Design Patterns
- **Separation of Concerns**: Algorithm logic separated from UI
- **Observer Pattern**: UI components update based on algorithm state
- **State Management**: Clean tracking of algorithm progress
- **Object-Oriented Design**: Each component has a single responsibility

## Educational Value

This visualizer is perfect for:
- Learning how Prim's Algorithm works
- Understanding Minimum Spanning Trees
- Visualizing greedy algorithms
- Teaching graph theory concepts
- Demonstrating algorithm efficiency

## Future Enhancements

Potential improvements:
- Add Kruskal's Algorithm for comparison
- Support for weighted graphs (manual weight input)
- Animation speed control
- Save/Load graph configurations
- Export MST as image or data
- Performance metrics display

## Author
@Vishrut99

## Date
October 17, 2025

## License
This project follows the repository's license terms.

## Contributing
Contributions are welcome! Please follow the repository's contribution guidelines.

---

**Note**: This visualizer is part of the Hacktoberfest 2025 contribution to the Java-Programs repository.
