/**
 * Linked List Implementation in Java
 * 
 * A Linked List is a linear data structure where elements (nodes) 
 * are connected using references (pointers). Each node contains:
 *   - data (value)
 *   - next (reference to next node)
 *
 * Advantages:
 *  - Dynamic size (no need to define capacity)
 *  - Easy insertion/deletion at any position
 *
 * Disadvantages:
 *  - Random access not possible (must traverse sequentially)
 *  - Uses extra memory (pointer/reference for each node)
 *
 * Example:
 * Insert 10 → Insert 20 → Insert 30
 * Linked List: 10 -> 20 -> 30
 * Delete 20
 * Linked List: 10 -> 30
 */

class LinkedList {

    // Node class: represents a single element in Linked List
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data; // store value
            this.next = null; // initially no link
        }
    }

    Node head; // head (first node of the list)

    // Insert new node at the end
    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode; // if list is empty
        } else {
            Node temp = head;
            while (temp.next != null) { // traverse till last node
                temp = temp.next;
            }
            temp.next = newNode; // link new node at end
        }
    }

    // Delete a node by value
    public void delete(int key) {
        Node temp = head, prev = null;

        // If head itself holds the key
        if (temp != null && temp.data == key) {
            head = temp.next; // move head
            return;
        }

        // Search for the key
        while (temp != null && temp.data != key) {
            prev = temp;
            temp = temp.next;
        }

        // If key not found
        if (temp == null) return;

        // Unlink the node
        prev.next = temp.next;
    }

    // Search an element
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }

    // Display the linked list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Main method to test LinkedList
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Insert elements
        list.insert(10);
        list.insert(20);
        list.insert(30);
        System.out.println("After Insertion:");
        list.display();  // 10 -> 20 -> 30 -> null

        // Delete element
        list.delete(20);
        System.out.println("After Deletion of 20:");
        list.display();  // 10 -> 30 -> null

        // Search element
        System.out.println("Search 30: " + list.search(30)); // true
        System.out.println("Search 50: " + list.search(50)); // false
    }
}
