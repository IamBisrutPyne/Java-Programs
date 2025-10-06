import java.util.Scanner;

public class FibonacciSequenceGenerator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object for user input
        System.out.print("Enter the number of terms in the Fibonacci sequence: ");
        int n = sc.nextInt(); // Read the number of terms in the Fibonacci sequence
        int a = 0, b = 1;

        System.out.println("Your Fibonacci sequence is:");
        for (int i = 0; i <= n; i++) {
            System.out.print(a + " "); // Print the current term
            int next = a + b; // Calculate the next term
            a = b; // Update a to the next term
            b = next; // Update b to the term after next
        }
        sc.close();
    }
}