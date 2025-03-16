package bst;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchTree {
    private Node root;
    private StringBuilder stringBuilder = new StringBuilder(); // for traversals

    public BinarySearchTree() {
        this.root = null;
    }

    public String add(int key) {
        Node newNode = new Node(key);
        return findAndAdd(root, newNode);
    }

    public String findAndAdd(Node current, Node inNode) {
        if (current == null) { // no root node
            root = inNode;
            return "SUCCESS";
        }
        else if (inNode.getKey() >= current.getKey()) { // move right
            if (current.getRightChild() != null) {
                return findAndAdd(current.getRightChild(), inNode);
            } else { // add as right child!
                // link & add to arraylist
                current.setRightChild(inNode);
                return "SUCCESS";
            }
        } else {
            if (current.getLeftChild() != null) { // move left
                return findAndAdd(current.getLeftChild(), inNode);
            } else { // add as left child!
                current.setLeftChild(inNode);
                return "SUCCESS";
            }
        }
    }

    public String remove(int key) {
        return (findAndRemove(root, key) != null) ? "RSUCCESS" : "RERROR";
    }

    private Node findAndRemove(Node current, int key) {
        if (current == null) {
            return null; // not in bst
        }

        // can move
        if (key < current.getKey()) { // move left
            System.out.println("MOVE LEFT");
            current.setLeftChild(findAndRemove(current.getLeftChild(), key));
            System.out.println("NEW: " + current.getKey());
        } else if (key > current.getKey()) { // move right
            System.out.println("MOVE RIGHT");
            current.setRightChild(findAndRemove(current.getRightChild(), key));
            System.out.println("NEW: " + current.getKey());
        } else { // current is key (foudn)
            if (current.hasLeftChild() && current.hasRightChild()) { // case: two children (get min of rightsubtree)
                System.out.println("GET MIN");
                // get the minimum node of the right tree
                Node minimumRightChild = getMinOfTree(current.getRightChild());
                System.out.println("min: " + minimumRightChild.getKey());

                // set the minimum of the right child to current (removing previously held key)
                System.out.println("SET");
                current.setKey(minimumRightChild.getKey());
                System.out.println("NEW: " + current.getKey());

                // remove duplicate from replacement (extra min at bottom)
                System.out.println("REMOVE DUP");
                current.setRightChild(findAndRemove(minimumRightChild.getRightChild(), minimumRightChild.getKey()));
                System.out.println("NEW: " + current.getKey());
            } else if (current.hasLeftChild()) { // case: only left child -> return current.left
                return current.getLeftChild();
            } else if (current.hasRightChild()) { // case: only right child -> return current.right
                return current.getRightChild();
            } else { // case: no kids -> delete the leaf
                System.out.println("LEAF");
                return null;
            }
        }
        System.out.println("CURR: " + current.getKey());
        return current;
    }

    private Node getMinOfTree(Node current) {
        // iterate down left side to get minimum
        while (current.hasLeftChild()) {
            current = current.getLeftChild();
        }
        return current; // no smaller value
    }

    public String search(int key) {
        return (inBst(root, key))? (key + " in bst") : (key + " not in bst");
    }

    private boolean inBst(Node current, int key) {
        if (current == null) { // not found!
            return false;
        } else if (current.getKey() == key) { // found!
            return true;
        } else if (key >= current.getKey()) { // move right
            return inBst(current.getRightChild(), key);
        } else { // move left
            return inBst(current.getLeftChild(), key);
        }
    }

    public String preOrderTraversal() {
        stringBuilder = new StringBuilder();
        if (root != null) {
            preOrderRecursive(root);
        } else {
            stringBuilder.append("No nodes in bst; cannot PreOrderTraverse");
        }

        return stringBuilder.toString();
    }

    private void preOrderRecursive(Node current) {
        stringBuilder.append(current.getKey() + " ");

        // go down left printing
        if (current.getLeftChild() != null) {
            preOrderRecursive(current.getLeftChild());
        }

        // once reached and returned to go down right
        if (current.getRightChild() != null) {
            preOrderRecursive(current.getRightChild());
        }

        // if no children, implicitly return (no need to return anything as I am just appending to SB
    }

    public String inOrderTraversal() {
        stringBuilder = new StringBuilder();
        if (root != null) {
            inOrderRecursive(root);
        } else {
            stringBuilder.append("No nodes in bst; cannot inOrderTraverse");
        }

        return stringBuilder.toString();
    }

    private void inOrderRecursive(Node current) {
        // go down left
        if (current.getLeftChild() != null) {
            inOrderRecursive(current.getLeftChild());
        }

        // no more left, append current
        stringBuilder.append(current.getKey() + " ");

        // now go down right
        if (current.getRightChild() != null) {
            inOrderRecursive(current.getRightChild());
        }

        // if no children, implicitly return (no need to return anything as I am just appending to SB
    }

    public String postOrderTraversal() {
        stringBuilder = new StringBuilder();
        if (root != null) {
            postOrderRecursive(root);
        } else {
            stringBuilder.append("No nodes in bst; cannot postOrderTraverse");
        }

        return stringBuilder.toString();
    }

    private void postOrderRecursive(Node current) {
        // go down left
        if (current.getLeftChild() != null) {
            postOrderRecursive(current.getLeftChild());
        }

        // no more left, now go down right
        if (current.getRightChild() != null) {
            postOrderRecursive(current.getRightChild());
        }

        // no more left, append current
        stringBuilder.append(current.getKey() + " ");

        // if no children, implicitly return (no need to return anything as I am just appending to SB
    }

    public String buildArrowString(Node node) {
        return "";
    }

    public String visualize() {
        return "";
    }

    public static void main(String[] args) {
        // testing locally
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println(bst.add(6));
        System.out.println(bst.add(5));
        System.out.println(bst.add(3));
        System.out.println(bst.add(7));
        System.out.println(bst.remove(8));
        System.out.println(bst.preOrderTraversal());
        System.out.println(bst.remove(7));
        System.out.println(bst.add(8));
        System.out.println(bst.preOrderTraversal());
        System.out.println(bst.remove(8));
        System.out.println(bst.preOrderTraversal());
        System.out.println(bst.add(3));
        System.out.println(bst.preOrderTraversal());
        System.out.println(bst.add(3));
    }
}