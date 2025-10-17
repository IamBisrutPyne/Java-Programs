#!/bin/bash
# Prim's Algorithm Visualizer Launcher
# This script compiles and runs the Prim's Algorithm Visualizer on Unix-based systems

echo "========================================"
echo "Prim's Algorithm Visualizer"
echo "========================================"
echo ""

echo "Compiling PrimsAlgorithmVisualizer.java..."
javac PrimsAlgorithmVisualizer.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Launching Visualizer..."
    echo ""
    java PrimsAlgorithmVisualizer
else
    echo ""
    echo "Compilation failed! Please check for errors."
    exit 1
fi
