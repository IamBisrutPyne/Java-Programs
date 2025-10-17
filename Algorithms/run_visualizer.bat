@echo off
REM Prim's Algorithm Visualizer Launcher
REM This script compiles and runs the Prim's Algorithm Visualizer

echo ========================================
echo Prim's Algorithm Visualizer
echo ========================================
echo.

echo Compiling PrimsAlgorithmVisualizer.java...
javac PrimsAlgorithmVisualizer.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo.
    echo Launching Visualizer...
    echo.
    java PrimsAlgorithmVisualizer
) else (
    echo.
    echo Compilation failed! Please check for errors.
    pause
)
