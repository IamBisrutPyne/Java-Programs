# Prim's Algorithm Visualizer - User Guide

## Quick Start Guide

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Windows/Linux/macOS operating system

### Installation & Running

#### Method 1: Using the Batch Script (Windows)
```bash
cd Algorithms
run_visualizer.bat
```

#### Method 2: Manual Compilation
```bash
cd Algorithms
javac PrimsAlgorithmVisualizer.java
java PrimsAlgorithmVisualizer
```

## Interface Overview

The application window consists of four main sections:

### 1. Main Canvas (Left Panel)
- **Purpose**: Interactive drawing area for graph visualization
- **Size**: 800x600 pixels
- **Background**: Light blue (#F0F8FF)
- **Features**:
  - Click to add nodes
  - Visual representation of graph edges
  - Real-time MST construction display
  - Color-coded nodes and edges

### 2. Adjacency Matrix Panel (Right Panel)
- **Purpose**: Displays the graph's adjacency matrix
- **Size**: 300x600 pixels
- **Features**:
  - Monospaced font for alignment
  - Shows edge weights between nodes
  - Updates automatically when graph changes
  - Infinity (∞) symbol for non-connected nodes

### 3. Control Panel (Top)
Eight control buttons for managing the visualizer:

| Button | Function | When to Use |
|--------|----------|-------------|
| **Add Nodes Mode** | Enables node placement | At the start or when adding more nodes |
| **Create Complete Graph** | Generates all possible edges | After adding at least 2 nodes |
| **Initialize Algorithm** | Prepares Prim's algorithm | Before running the algorithm |
| **Step** | Executes one algorithm iteration | To see step-by-step execution |
| **Run All** | Completes the entire algorithm | To see final MST immediately |
| **Reset** | Clears algorithm state | To re-run on same graph |
| **Clear All** | Removes everything | To start completely fresh |
| **Generate Random Graph** | Creates 6-10 random nodes | For quick testing/demonstration |

### 4. Status Panel (Bottom)
- **Status Label**: Shows current operation and progress
- **Weight Label**: Displays total MST weight in real-time

## Step-by-Step Tutorial

### Tutorial 1: Creating Your First MST

**Step 1: Add Nodes**
1. Launch the application
2. The status bar shows: "Status: Click to add nodes"
3. Click on the canvas to place nodes
4. Add at least 4-5 nodes for a good demonstration
5. Each node is numbered automatically (0, 1, 2, ...)

**Visual Feedback:**
- Blue circles represent nodes
- White numbers show node IDs
- Status updates with each node added

**Step 2: Create the Graph**
1. Click "Create Complete Graph" button
2. The application draws edges between all node pairs
3. Edge weights appear at the midpoint of each edge
4. Adjacency matrix populates on the right panel

**What Happens:**
- Euclidean distance calculated for each edge
- Complete graph created (every node connected to every other)
- Edge weights displayed with one decimal place
- Matrix shows all connections

**Step 3: Initialize the Algorithm**
1. Click "Initialize Algorithm" button
2. Node 0 turns green (starting node)
3. Status shows: "Algorithm initialized. Starting from node 0"
4. The algorithm is ready to execute

**Step 4: Execute Step-by-Step**
1. Click "Step" button repeatedly
2. Watch each iteration:
   - New node turns green when added to MST
   - Selected edge turns red
   - Total weight increases
   - Status shows MST edge count

**Observation Points:**
- The algorithm always selects the minimum weight edge
- Only edges connecting visited (green) to unvisited (blue) nodes are considered
- The MST grows one edge at a time

**Step 5: View Final Result**
1. Continue clicking "Step" until complete
2. Final status: "Algorithm complete! MST constructed."
3. All nodes are green
4. Red edges form the MST
5. Total weight displayed at bottom

### Tutorial 2: Using Random Graph Generation

**Quick Demo Mode:**
1. Click "Generate Random Graph"
2. 6-10 nodes appear at random positions
3. Click "Create Complete Graph"
4. Click "Run All" to see instant MST
5. Observe the final spanning tree

**Benefits:**
- Quick testing without manual placement
- Different graph topologies each time
- Perfect for demonstrations
- Good for comparing different runs

### Tutorial 3: Understanding the Algorithm

**Watch the Greedy Choice:**

1. **Initial State**: Only node 0 is visited (green)
2. **First Step**: Algorithm scans all edges from node 0
   - Selects the edge with minimum weight
   - Adds connected node to MST
   
3. **Subsequent Steps**: For each iteration
   - Scans edges from all visited nodes
   - Considers only edges to unvisited nodes
   - Selects minimum weight edge
   - Adds to MST

**Key Observations:**
- Greedy algorithm: Always picks minimum at each step
- Never creates cycles
- Guarantees minimum total weight
- Processes exactly (n-1) edges for n nodes

## Visual Color Coding

### Node Colors
| Color | Meaning | RGB Value |
|-------|---------|-----------|
| Blue | Unvisited node | (70, 130, 180) |
| Green | Visited/In MST | (60, 179, 113) |
| White | Node label | (255, 255, 255) |
| Black | Node border | (0, 0, 0) |

### Edge Colors
| Color | Meaning | Width | RGB Value |
|-------|---------|-------|-----------|
| Light Gray | Regular edge | 2px | (200, 200, 200) |
| Orange-Red | MST edge | 4px | (255, 69, 0) |
| Black | Edge weight text | - | (0, 0, 0) |

## Common Operations

### Resetting and Re-running
```
Scenario: You want to see the algorithm again

1. Click "Reset" button
2. All nodes turn blue
3. All edges turn gray
4. Weight resets to 0.0
5. Click "Initialize Algorithm"
6. Click "Step" or "Run All"
```

### Creating a New Graph
```
Scenario: Start with a completely new graph

1. Click "Clear All" button
2. Click to add new nodes
3. Click "Create Complete Graph"
4. Run the algorithm
```

### Comparing Different Runs
```
Scenario: Compare MST on different node arrangements

Run 1:
1. Add nodes in a line
2. Create graph and run algorithm
3. Note the total weight

Run 2:
1. Click "Clear All"
2. Add nodes in a circle
3. Create graph and run algorithm
4. Compare total weights
```

## Advanced Usage

### Custom Graph Topologies

**Linear Arrangement:**
- Place nodes in a straight line
- Observe how MST forms along the line
- Generally results in (n-1) consecutive connections

**Circular Arrangement:**
- Place nodes in a circular pattern
- MST typically forms a path, not using all edges
- Demonstrates non-cyclic property

**Clustered Arrangement:**
- Create groups of close nodes
- Observe how MST connects clusters efficiently
- Shows algorithm's optimization capability

**Random Arrangement:**
- Use "Generate Random Graph"
- Each run produces different MST
- Good for understanding algorithm behavior

### Understanding Edge Weights

**Euclidean Distance Formula:**
```
weight = √((x₂ - x₁)² + (y₂ - y₁)²)
```

**Example:**
- Node 0 at (100, 100)
- Node 1 at (100, 200)
- Distance = √((100-100)² + (200-100)²) = √(0 + 10000) = 100.0

**Practical Implications:**
- Closer nodes have smaller weights
- Diagonal distances are larger than horizontal/vertical
- Edge weight is independent of direction

### Adjacency Matrix Interpretation

**Reading the Matrix:**
```
     0    1    2    3
0    0  100  150  200
1  100    0  120  180
2  150  120    0  140
3  200  180  140    0
```

**Interpretation:**
- Diagonal is always 0 (distance to self)
- Symmetric matrix (undirected graph)
- Row i, Column j = distance from node i to node j
- ∞ represents no connection (in sparse graphs)

## Troubleshooting

### Problem: Can't Add Nodes
**Solution:**
- Ensure you're in "Add Nodes Mode"
- Click the "Add Nodes Mode" button
- Verify cursor is on the canvas

### Problem: "Create Graph" Button Disabled
**Solution:**
- Add at least 2 nodes first
- Check status message for guidance

### Problem: Algorithm Not Running
**Solution:**
- First create a graph
- Then click "Initialize Algorithm"
- Finally click "Step" or "Run All"

### Problem: Can't See Edges Clearly
**Solution:**
- MST edges are thicker and red
- Regular edges are thin and gray
- Zoom in if needed or add fewer nodes

### Problem: Matrix Shows Only Infinity
**Solution:**
- You haven't created the graph yet
- Click "Create Complete Graph" button
- Matrix will populate automatically

## Educational Exercises

### Exercise 1: Minimum vs Maximum
**Objective**: Understand why Prim's chooses minimum weight

1. Create a simple graph with 4 nodes
2. Run algorithm step-by-step
3. At each step, identify all candidate edges
4. Verify the selected edge has minimum weight
5. Calculate what would happen if maximum was chosen

### Exercise 2: Efficiency Analysis
**Objective**: Understand algorithm efficiency

1. Create graphs with 4, 6, 8, and 10 nodes
2. Count total edges in complete graph
3. Count edges in MST (always n-1)
4. Calculate percentage of edges used
5. Observe how many edges the algorithm saves

### Exercise 3: Weight Optimization
**Objective**: Verify MST minimality

1. Create a graph with 5 nodes
2. Run algorithm to get MST weight W
3. Manually calculate weight of other spanning trees
4. Verify all other spanning trees have weight ≥ W

### Exercise 4: Graph Properties
**Objective**: Understand MST properties

1. Create a graph
2. Count nodes (V) and MST edges (E)
3. Verify E = V - 1
4. Try to add one more edge - creates cycle
5. Remove any MST edge - graph becomes disconnected

## Performance Characteristics

### Time Complexity Analysis
- **Matrix-based implementation**: O(V²)
- **V** = Number of vertices/nodes
- **For each step**: Scan all edges from visited nodes
- **Total iterations**: V - 1

### Space Complexity
- **Adjacency Matrix**: O(V²)
- **Visited Array**: O(V)
- **MST Edges List**: O(V)
- **Total**: O(V²)

### Scalability Notes
- Works well for up to 20-30 nodes
- Beyond 30 nodes, visualization becomes cluttered
- Algorithm remains efficient even with many nodes
- UI responsiveness maintained throughout

## Tips for Best Experience

### For Learning:
1. Start with 4-5 nodes for clarity
2. Use step-by-step execution
3. Watch the status bar for insights
4. Compare different graph arrangements
5. Manually verify some calculations

### For Demonstration:
1. Use "Generate Random Graph" for variety
2. "Run All" for quick results
3. Create visually interesting patterns
4. Point out the greedy choice at each step
5. Highlight the color changes

### For Testing:
1. Test edge cases (2 nodes, 10 nodes)
2. Try different topologies
3. Verify total weight calculations
4. Check matrix accuracy
5. Test reset and clear functions

## Keyboard Shortcuts (Future Enhancement)
*Note: Current version uses button controls only*

Potential future shortcuts:
- `Ctrl+N`: Add node mode
- `Ctrl+G`: Create graph
- `Ctrl+I`: Initialize algorithm
- `Space`: Execute step
- `Ctrl+R`: Run all
- `Ctrl+Z`: Reset
- `Ctrl+X`: Clear all

## System Requirements

### Minimum Requirements:
- **OS**: Windows 7/8/10/11, Linux, macOS
- **Java**: JDK 8 or higher
- **RAM**: 256 MB
- **Display**: 1024x768 or higher

### Recommended Requirements:
- **OS**: Windows 10/11, Ubuntu 20.04+, macOS 10.15+
- **Java**: JDK 11 or higher
- **RAM**: 512 MB
- **Display**: 1920x1080 or higher

## Conclusion

This visualizer provides a comprehensive, interactive way to learn and understand Prim's Algorithm for Minimum Spanning Trees. Through its intuitive interface, real-time visualization, and step-by-step execution, users can gain deep insights into how greedy algorithms work and how MSTs are constructed.

Whether you're a student learning graph algorithms, a teacher demonstrating concepts, or a developer understanding implementation details, this tool offers valuable educational value and practical insights.

Happy visualizing! 🎨📊🌲

---

**For issues or suggestions**: Please open an issue on the GitHub repository
**For contributions**: Follow the repository's contribution guidelines
