/**
 * Program Title: Rock-Paper-Scissors Game
 * Author: [VinayKumarBM]
 * Date: 2025-10-10
 *
 * Description: Implements the classic Rock-Paper-Scissors game against a computer opponent.
 *
 * Language: Java
 *
 * Time Complexity: O(1).
 * Space Complexity: O(1).
 */

import java.util.*;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] options = {"rock", "paper", "scissors"};
        Random random = new Random();

        while (true) {
            System.out.print("Enter rock, paper, or scissors (or 'quit'): ");
            String user = sc.nextLine().toLowerCase();

            if (user.equals("quit")) break;
            if (!Arrays.asList(options).contains(user)) {
                System.out.println("Invalid choice! Try again.");
                continue;
            }

            String computer = options[random.nextInt(3)];
            System.out.println("Computer chose: " + computer);

            if (user.equals(computer))
                System.out.println("It's a tie!");
            else if ((user.equals("rock") && computer.equals("scissors")) ||
                     (user.equals("scissors") && computer.equals("paper")) ||
                     (user.equals("paper") && computer.equals("rock")))
                System.out.println("You win! 🎉");
            else
                System.out.println("You lose! 💻 wins!");
        }
    }
}
