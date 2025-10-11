/**
 * Program Title: Queue (Linked List Implementation)
 * Author: [Ahad-23]
 * Date: 2025-10-08
 *
 * Description: Implements a generic Queue data structure using a custom
 * Singly Linked List. Operations (enqueue, dequeue, peek) are designed
 * to adhere to the FIFO (First-In, First-Out) principle.
 *
 * Time Complexity: O(1) for all main operations (enqueue, dequeue, peek).
 * Space Complexity: O(N) where N is the number of elements stored.
 */

public class Queue<T> {
    // Node class for custom singly linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front;  
    private Node<T> rear;   
    private int size;       

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) front = rear = newNode;
        
        else
        {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        T value = front.data;
        front = front.next;
        if (front == null) rear = null;
        
        size--;
        return value;
    }


    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // Get current size of the queue
    public int size() {
        return size;
    }
}
