/**
 * Program Title: Min-Max Algorithm (Recursive Approach)
 * Author: Jayanth (jayanthwritescode)
 * Date: 2025-10-17
 *
 * Description: This program implements the recursive divide-and-conquer Min-Max algorithm
 * to find both the minimum and maximum elements in an array. It divides the array into
 * halves recursively, finds min-max in each half, and then merges the results by comparing
 * the minimums and maximums from both halves. This demonstrates the divide-and-conquer
 * paradigm commonly used in algorithms like merge sort and tournament trees.
 * 
 * Time Complexity: O(3n/2) - approximately 1.5n comparisons for n elements
 * 
 * Space Complexity: O(log n) - due to recursion call stack depth
 */

public class MinMaxRecursive {
    
    static class MinMax {
        int min;
        int max;
        
        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    
    static MinMax findMinMax(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        // Base case: single element
        if (low == high) {
            return new MinMax(arr[low], arr[low]);
        }
        
        // Base case: two elements
        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                return new MinMax(arr[low], arr[high]);
            } else {
                return new MinMax(arr[high], arr[low]);
            }
        }
        
        // Divide the array into two halves
        int mid = low + (high - low) / 2;
        
        // Recursively find min-max in left half
        MinMax left = findMinMax(arr, low, mid);
        
        // Recursively find min-max in right half
        MinMax right = findMinMax(arr, mid + 1, high);
        
        // Merge results by comparing mins and maxes from both halves
        return new MinMax(
            Math.min(left.min, right.min),
            Math.max(left.max, right.max)
        );
    }
    
    public static void main(String[] args) {
        // Test case 1: Mixed positive and negative numbers
        int[] arr1 = {5, -2, 8, 1, -10, 3};
        MinMax result1 = findMinMax(arr1, 0, arr1.length - 1);
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Min: " + result1.min + ", Max: " + result1.max);
        
        // Test case 2: Single element
        int[] arr2 = {42};
        MinMax result2 = findMinMax(arr2, 0, arr2.length - 1);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Min: " + result2.min + ", Max: " + result2.max);
        
        // Test case 3: Two elements
        int[] arr3 = {100, -50};
        MinMax result3 = findMinMax(arr3, 0, arr3.length - 1);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr3));
        System.out.println("Min: " + result3.min + ", Max: " + result3.max);
        
        // Test case 4: Power of 2 length array
        int[] arr4 = {15, 3, 9, 6, 12, 1, 8, 4};
        MinMax result4 = findMinMax(arr4, 0, arr4.length - 1);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr4));
        System.out.println("Min: " + result4.min + ", Max: " + result4.max);
        
        // Test case 5: All negative numbers
        int[] arr5 = {-5, -2, -8, -1};
        MinMax result5 = findMinMax(arr5, 0, arr5.length - 1);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr5));
        System.out.println("Min: " + result5.min + ", Max: " + result5.max);
    }
}
