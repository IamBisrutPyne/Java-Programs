/**
 * Test Suite for Prim's Algorithm Visualizer
 * Verifies core functionality of the algorithm components
 */

import java.util.*;

public class PrimsVisualizerTest {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Prim's Algorithm Visualizer - Test Suite");
        System.out.println("=".repeat(60));
        System.out.println();
        
        int passed = 0;
        int total = 0;
        
        // Test 1: Node Distance Calculation
        total++;
        System.out.println("Test 1: Node Distance Calculation");
        System.out.println("-".repeat(40));
        Node n1 = new Node(0, 0, 0);
        Node n2 = new Node(1, 3, 4);
        double distance = n1.distanceTo(n2);
        System.out.println("Node 1: (0, 0)");
        System.out.println("Node 2: (3, 4)");
        System.out.println("Expected Distance: 5.0");
        System.out.println("Actual Distance: " + distance);
        if (Math.abs(distance - 5.0) < 0.001) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 2: Node Contains Point
        total++;
        System.out.println("Test 2: Node Contains Point");
        System.out.println("-".repeat(40));
        Node n3 = new Node(0, 100, 100);
        boolean contains1 = n3.contains(105, 105, 20);
        boolean contains2 = n3.contains(150, 150, 20);
        System.out.println("Node at (100, 100) with radius 20");
        System.out.println("Point (105, 105) contained: " + contains1 + " (expected: true)");
        System.out.println("Point (150, 150) contained: " + contains2 + " (expected: false)");
        if (contains1 && !contains2) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 3: Adjacency Matrix Creation
        total++;
        System.out.println("Test 3: Adjacency Matrix Creation");
        System.out.println("-".repeat(40));
        AdjacencyMatrix matrix = new AdjacencyMatrix(3);
        matrix.setEdge(0, 1, 10.5);
        matrix.setEdge(1, 2, 15.3);
        double w01 = matrix.getWeight(0, 1);
        double w10 = matrix.getWeight(1, 0);
        double w02 = matrix.getWeight(0, 2);
        System.out.println("Set edge (0,1) = 10.5");
        System.out.println("Get edge (0,1) = " + w01);
        System.out.println("Get edge (1,0) = " + w10 + " (should be symmetric)");
        System.out.println("Get edge (0,2) = " + w02 + " (should be infinity)");
        if (Math.abs(w01 - 10.5) < 0.001 && 
            Math.abs(w10 - 10.5) < 0.001 && 
            w02 == Double.POSITIVE_INFINITY) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 4: Simple MST Calculation
        total++;
        System.out.println("Test 4: Simple MST Calculation");
        System.out.println("-".repeat(40));
        List<Node> testNodes = new ArrayList<>();
        testNodes.add(new Node(0, 0, 0));
        testNodes.add(new Node(1, 10, 0));
        testNodes.add(new Node(2, 10, 10));
        testNodes.add(new Node(3, 0, 10));
        
        AdjacencyMatrix testMatrix = new AdjacencyMatrix(4);
        // Create a square graph
        testMatrix.setEdge(0, 1, 10);
        testMatrix.setEdge(1, 2, 10);
        testMatrix.setEdge(2, 3, 10);
        testMatrix.setEdge(3, 0, 10);
        testMatrix.setEdge(0, 2, 14.14); // Diagonal
        testMatrix.setEdge(1, 3, 14.14); // Diagonal
        
        PrimAlgorithmLogic algo = new PrimAlgorithmLogic(testNodes, testMatrix);
        algo.initialize();
        algo.executeAll();
        
        double totalWeight = algo.getTotalWeight();
        int mstEdges = algo.getMstEdges().size();
        
        System.out.println("Square graph with 4 nodes");
        System.out.println("Side length: 10, Diagonal: 14.14");
        System.out.println("MST edges count: " + mstEdges + " (expected: 3)");
        System.out.println("MST total weight: " + totalWeight + " (expected: 30.0)");
        
        if (mstEdges == 3 && Math.abs(totalWeight - 30.0) < 0.001) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 5: Algorithm Reset
        total++;
        System.out.println("Test 5: Algorithm Reset");
        System.out.println("-".repeat(40));
        algo.reset();
        System.out.println("After reset:");
        System.out.println("MST edges: " + algo.getMstEdges().size() + " (expected: 0)");
        System.out.println("Total weight: " + algo.getTotalWeight() + " (expected: 0.0)");
        System.out.println("Algorithm complete: " + algo.isAlgorithmComplete() + " (expected: false)");
        
        if (algo.getMstEdges().size() == 0 && 
            algo.getTotalWeight() == 0.0 && 
            !algo.isAlgorithmComplete()) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 6: Step-by-Step Execution
        total++;
        System.out.println("Test 6: Step-by-Step Execution");
        System.out.println("-".repeat(40));
        List<Node> stepNodes = new ArrayList<>();
        stepNodes.add(new Node(0, 0, 0));
        stepNodes.add(new Node(1, 5, 0));
        stepNodes.add(new Node(2, 10, 0));
        
        AdjacencyMatrix stepMatrix = new AdjacencyMatrix(3);
        stepMatrix.setEdge(0, 1, 5);
        stepMatrix.setEdge(1, 2, 5);
        stepMatrix.setEdge(0, 2, 10);
        
        PrimAlgorithmLogic stepAlgo = new PrimAlgorithmLogic(stepNodes, stepMatrix);
        stepAlgo.initialize();
        
        boolean step1 = stepAlgo.executeStep();
        int edges1 = stepAlgo.getMstEdges().size();
        
        boolean step2 = stepAlgo.executeStep();
        int edges2 = stepAlgo.getMstEdges().size();
        
        boolean step3 = stepAlgo.executeStep();
        
        System.out.println("Linear graph: 0-5-1-5-2");
        System.out.println("Step 1 executed: " + step1 + ", Edges: " + edges1);
        System.out.println("Step 2 executed: " + step2 + ", Edges: " + edges2);
        System.out.println("Step 3 executed: " + step3 + " (should be false, complete)");
        System.out.println("Final weight: " + stepAlgo.getTotalWeight() + " (expected: 10.0)");
        
        if (step1 && step2 && !step3 && 
            edges1 == 1 && edges2 == 2 && 
            Math.abs(stepAlgo.getTotalWeight() - 10.0) < 0.001) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Test 7: GraphEdge Functionality
        total++;
        System.out.println("Test 7: GraphEdge Functionality");
        System.out.println("-".repeat(40));
        GraphEdge edge1 = new GraphEdge(0, 1, 25.5);
        System.out.println("Created edge: " + edge1.from + " -> " + edge1.to);
        System.out.println("Weight: " + edge1.weight);
        System.out.println("In MST: " + edge1.inMST + " (expected: false)");
        edge1.inMST = true;
        System.out.println("After marking: " + edge1.inMST + " (expected: true)");
        
        if (edge1.from == 0 && edge1.to == 1 && 
            Math.abs(edge1.weight - 25.5) < 0.001 && 
            edge1.inMST) {
            System.out.println("✓ PASSED");
            passed++;
        } else {
            System.out.println("✗ FAILED");
        }
        System.out.println();
        
        // Summary
        System.out.println("=".repeat(60));
        System.out.println("Test Summary");
        System.out.println("=".repeat(60));
        System.out.println("Tests Passed: " + passed + "/" + total);
        System.out.println("Tests Failed: " + (total - passed) + "/" + total);
        System.out.println("Success Rate: " + String.format("%.1f%%", (passed * 100.0 / total)));
        System.out.println();
        
        if (passed == total) {
            System.out.println("🎉 All tests passed! The visualizer is working correctly.");
        } else {
            System.out.println("⚠ Some tests failed. Please review the implementation.");
        }
        System.out.println("=".repeat(60));
    }
}
