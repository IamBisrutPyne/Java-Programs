/**
 * Program Title: Min-Max Algorithm (Iterative Approach)
 * 
 * Author: Jayanth (jayanthwritescode)
 * Date: 2025-10-17
 *
 * Description: This program implements the iterative Min-Max algorithm to find both
 * the minimum and maximum elements in an array with minimal comparisons. It processes
 * elements in pairs, comparing each pair internally first before updating the global
 * min and max values. This approach reduces the total number of comparisons needed
 * compared to separate min and max searches.
 * 
 * Time Complexity: O(3n/2) - approximately 1.5n comparisons for n elements
 * 
 * Space Complexity: O(1) - constant space, no recursion overhead
 */

public class MinMaxIterative {
    
    static class MinMax {
        int min;
        int max;
        
        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    
    static MinMax findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        MinMax result = new MinMax(arr[0], arr[0]);
        
        // Process elements in pairs starting from index 1
        int i = 1;
        while (i < arr.length - 1) {
            int first = arr[i];
            int second = arr[i + 1];
            
            // Compare the pair against each other first
            if (first > second) {
                if (first > result.max) {
                    result.max = first;
                }
                if (second < result.min) {
                    result.min = second;
                }
            } else {
                if (second > result.max) {
                    result.max = second;
                }
                if (first < result.min) {
                    result.min = first;
                }
            }
            
            i += 2;
        }
        
        // Handle case when array has odd length
        if (i < arr.length) {
            int last = arr[i];
            if (last > result.max) {
                result.max = last;
            }
            if (last < result.min) {
                result.min = last;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test case 1: Mixed positive and negative numbers
        int[] arr1 = {5, -2, 8, 1, -10, 3};
        MinMax result1 = findMinMax(arr1);
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Min: " + result1.min + ", Max: " + result1.max);
        
        // Test case 2: Single element
        int[] arr2 = {42};
        MinMax result2 = findMinMax(arr2);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Min: " + result2.min + ", Max: " + result2.max);
        
        // Test case 3: Two elements
        int[] arr3 = {100, -50};
        MinMax result3 = findMinMax(arr3);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr3));
        System.out.println("Min: " + result3.min + ", Max: " + result3.max);
        
        // Test case 4: Odd number of elements
        int[] arr4 = {7, 2, 9, 1, 5};
        MinMax result4 = findMinMax(arr4);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr4));
        System.out.println("Min: " + result4.min + ", Max: " + result4.max);
        
        // Test case 5: All same elements
        int[] arr5 = {3, 3, 3, 3};
        MinMax result5 = findMinMax(arr5);
        System.out.println("\nArray: " + java.util.Arrays.toString(arr5));
        System.out.println("Min: " + result5.min + ", Max: " + result5.max);
    }
}
