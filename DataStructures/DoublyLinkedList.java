/**
 * Doubly Linked List Implementation in Java
 * 
 * A Doubly Linked List is a linear data structure where each node contains:
 *   data (value)
 *   prev (reference to the previous node)
 *   next (reference to the next node)
 *
 * Advantages:
 *   Can be traversed in both directions (forward & backward)
 *   Easy insertion/deletion at both ends or any position
 *
 * Disadvantages:
 *   Uses extra memory for the previous pointer
 *   Slightly more complex to implement than singly linked list
 *
 */
class DoublyLinkedList {

    // Node class
    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data; 
        }
    }

    Node head; 

    // Insert at the end
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
            newNode.prev = temp;
        }
    }

    // Delete by value
    public void delete(int key) {
        if (head == null) return;

        Node temp = head;
        if (temp.data == key) {
            head = temp.next;
            if (head != null) head.prev = null;
            return;
        }

        while (temp != null && temp.data != key) temp = temp.next;
        if (temp == null) return;

        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
    }

    // Search for a value
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }

    // Display forward
    public void displayForward() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Display backward
    public void displayBackward() {
        if (head == null) return;
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    // Main method to test
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Insert elements
        dll.insert(10);
        dll.insert(20);
        dll.insert(30);

        System.out.println("Forward:");
        dll.displayForward();  
        System.out.println("Backward:");
        dll.displayBackward();  

        // Delete element
        dll.delete(20);
        System.out.println("After Deletion of 20:");
        dll.displayForward();  
        dll.displayBackward();  

        // Search elements
        System.out.println("Search 30: " + dll.search(30)); 
        System.out.println("Search 50: " + dll.search(50)); 
    }
}
