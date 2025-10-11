// KMP Algorithm in Java
// ------------------------------------------------------
// Author: Jatin Vishwakarma
// Description: Implementation of the Knuth–Morris–Pratt (KMP)
// Pattern Searching Algorithm in Java.
// This algorithm efficiently finds all occurrences of a pattern
// in a given text with O(n + m) time complexity.

import java.util.Scanner;

public class KMPAlgorithm {

    // Function to preprocess the pattern and build the LPS (Longest Prefix Suffix) array
    private static void computeLPSArray(String pattern, int[] lps) {
        int length = 0;  // length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0;  // LPS of the first character is always 0

        // Loop to calculate lps[i] for i = 1 to pattern.length - 1
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    // This is tricky — consider the example "AAACAAAA"
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // KMP Search Function
    public static void KMPSearch(String pattern, String text) {
        int M = pattern.length();
        int N = text.length();

        // Create LPS array
        int[] lps = new int[M];
        computeLPSArray(pattern, lps);

        int i = 0; // index for text[]
        int j = 0; // index for pattern[]

        boolean found = false;

        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                System.out.println("✅ Pattern found at index: " + (i - j));
                j = lps[j - 1];
                found = true;
            }

            // Mismatch after j matches
            else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters, they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }

        if (!found)
            System.out.println("❌ Pattern not found in the given text.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("   KMP STRING MATCHING ALGORITHM");
        System.out.println("=====================================");
        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the pattern to search: ");
        String pattern = sc.nextLine();

        System.out.println("\n🔍 Searching for pattern...");
        KMPSearch(pattern, text);

        System.out.println("\n--- Program Completed Successfully ---");
        sc.close();
    }
}
