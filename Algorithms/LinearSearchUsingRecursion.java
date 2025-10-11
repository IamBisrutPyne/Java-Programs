import java.io.*;
import java.util.Scanner;

/**
 * This program demonstrates the implementation of a linear search algorithm using recursion.
 * It searches for a given key within an array of integers.
 */

public class LinearSearchRecursion{

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the size of the array:");
        int n = in.nextInt();

        // Input array
        int[] arr = new int[n];
        System.out.println("Enter the array elements (one by one): ");
        for (int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
        }

        System.out.println("Enter the element to find:");
        int key = in.nextInt();

        int index  = linearSearch(arr, 0, key);

        if (index == -1) {
            System.out.println("Key not found :(");
        } else {
            System.out.println("Key found at " + (index + 1) + " position");
        }
    }

// Find the Key/Element
    public static int linearSearch (int[] arr, int ind, int key) {
        if (arr.length == ind) {
            return -1;
        }

        if (arr[ind] == key) {
            return ind;
        }

        return linearSearch(arr, ind + 1, key);
    }
}