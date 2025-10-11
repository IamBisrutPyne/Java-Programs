// Metadata Header (MANDATORY)
// -----------------------------
// Program Title: AVL Tree Implementation
// Author: [Madipadige-ManishKumar]
// Date: 2025-10-10
//
// Description: Implements the AVL Self-Balancing Binary Search Tree.
//
// Language: Java
//
// Time Complexity: O(log n).
// Space Complexity: O(n).
// -----------------------------

class AVLTree {

    // Node class - Declared as private for better encapsulation
    private class Node {
        int key, height;
        Node left, right;

        Node(int data) {
            key = data;
            height = 1;
        }
    }

    private Node root; // Should be private

    // ... (rest of the excellent code follows)

class AVLTree {

    // Node class
    class Node {
        int key, height;
        Node left, right;

        Node(int data) {
            key = data;
            height = 1;
        }
    }

    private Node root;

    // Utility: get height of a node
    int height(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    // Utility: get balance factor of a node
    int getBalance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    // Right rotation (LL rotation)
    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotation (RR rotation)
    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a key
    Node insert(Node node, int key) {
        // 1️⃣ Perform normal BST insertion
        if (node == null) return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // Duplicate keys not allowed

        // 2️⃣ Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3️⃣ Get balance factor
        int balance = getBalance(node);

        // 4️⃣ Balance the tree using rotations
        // LL
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Find node with minimum key
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a key
    Node delete(Node root, int key) {
        if (root == null)
            return root;

        // Perform standard BST delete
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // Node with one or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;
                root = (temp == null) ? null : temp;
            } else {
                // Node with two children
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        // If the tree had only one node
        if (root == null)
            return root;

        // Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Check balance
        int balance = getBalance(root);

        // Perform rotations
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    // Public methods for user
    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    // Inorder Traversal
    public void inorder() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    // Driver Example
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys)
            tree.insert(key);

        System.out.println("Inorder traversal after insertions:");
        tree.inorder();

        tree.delete(40);
        System.out.println("After deleting 40:");
        tree.inorder();
    }
}
