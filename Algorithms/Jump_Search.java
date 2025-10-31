/*
* Java program to implement Jump Search.
*
* Jump Search is a searching algorithm for sorted arrays.
* The basic idea is to check fewer elements by jumping
* ahead by fixed steps.
*
* Contributor: Priyanka Anand
* Submitted for Hacktoberfest 2025
*/

class Jump_Search {

    public static int jumpSearch(int[] arr, int x) {
        int n = arr.length;

        // Finding block size to be jumped
        int step = (int)Math.floor(Math.sqrt(n));

        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }

        // Doing a linear search for x in block
        // beginning with prev.
        while (arr[prev] < x) {
            prev++;

            // If we reached next block or end of
            // array, element is not present.
            if (prev == Math.min(step, n))
                return -1;
        }

        // If element is found
        if (arr[prev] == x)
            return prev;

        return -1;
    }

    // Main function to test the algorithm
    public static void main(String args[]) {
        int arr[] = { 0, 1, 1, 2, 3, 5, 8, 13, 21,
                    34, 55, 89, 144, 233, 377, 610 };
        int x = 55;

        int index = jumpSearch(arr, x);

        if (index != -1) {
            System.out.println("Element " + x + " is present at index " + index);
        } else {
            System.out.println("Element " + x + " is not present in the array");
        }

        x = 7;
        index = jumpSearch(arr, x);

        if (index != -1) {
            System.out.println("Element " + x + " is present at index " + index);
        } else {
            System.out.println("Element " + x + " is not present in the array");
        }
    }
}