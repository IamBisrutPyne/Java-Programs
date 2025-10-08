import java.util.Stack;

public class trees {
    public static void main(String[] args) {
        // Create a stack of integers
        Stack<Integer> stack = new Stack<>();

        // Push elements (add to stack)
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println("Stack after pushes: " + stack);

        // Peek (see top element without removing)
        System.out.println("Top element (peek): " + stack.peek());

        // Pop (remove top element)
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        // Search (find position from top, 1-based)
        System.out.println("Position of 20 from top: " + stack.search(20));

        // Check if empty
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
