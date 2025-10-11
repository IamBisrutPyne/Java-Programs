/**
 * ------------------------------------------------------------
 * Program Name : GradeCalculator
 * Author       : Anukalp Pandey
 * Description  : A simple console application that takes a student's numerical score
 *                and prints the corresponding letter grade (A, B, C, D, F)
 *                using if-else if statements.
 * Complexity   : O(1) - Constant time complexity, since it performs a fixed
 *                number of comparisons regardless of input size.
 * ------------------------------------------------------------
 */

import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {

        // Using try-with-resources to ensure Scanner is closed automatically
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter student's score (0 - 100): ");
            int score = sc.nextInt();

            char grade;

            if (score >= 90 && score <= 100) {
                grade = 'A';
            } else if (score >= 80) {
                grade = 'B';
            } else if (score >= 70) {
                grade = 'C';
            } else if (score >= 60) {
                grade = 'D';
            } else if (score >= 0) {
                grade = 'F';
            } else {
                System.out.println("Invalid score entered! Please enter between 0 and 100.");
                return;
            }

            System.out.println("Grade: " + grade);
        } catch (Exception e) {
            System.out.println("Error: Please enter a valid numerical score.");
        }
    }
}
