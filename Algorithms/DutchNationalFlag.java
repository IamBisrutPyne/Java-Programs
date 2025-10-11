import java.util.Arrays;

public class DutchNationalFlag {

    /**
     * Sorts an array of 0s, 1s, and 2s in a single pass using the DNF algorithm.
     */
    public static void sort(int[] arr) {
        int low = 0;   // Pointer for the start of the 0s section
        int mid = 0;   // Pointer for the current element being processed
        int high = arr.length - 1; // Pointer for the end of the 2s section

        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    // If the element is 0, swap it with 'low' pointer
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    // If the element is 1, it's already in the middle position,
                    mid++;
                    break;
                case 2:
                    // If the element is 2, swap it with 'high' pointer
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {2, 0, 1, 2, 0, 1, 0, 2};
        System.out.println("Original array: " + Arrays.toString(array));
        
        sort(array);
        
        System.out.println("Sorted array:   " + Arrays.toString(array));
    }
}
