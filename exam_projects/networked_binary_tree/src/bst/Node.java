/* This Java file is part of Matt Krueger's Introduction to Software Design Oral Exam 2 */

package bst;

/*
 * Class:
 *     Node
 *
 * Purpose:
 *     Node class for the BinarySearchTree tree. Represents a single linked value, linking to a max of two children (left and right)
 *     Provides additional values tracking the node depth, level, and index inside the BST.
 */
public class Node {
    /* key: integer value of node */
    private int key;

    /* leftChild: linked child; must be smaller */
    private Node leftChild;

    /* rightChild: linked child; must be larger */
    private Node rightChild;

    /*
     * Constructor:
     *     Node
     * Purpose:
     *     Set attributes.
     *         - key: key
     *         - children: null
     *
     * @param key: node integer value
     * @return none
     */
    public Node (int key) {
        this.key = key;
        leftChild = null;
        rightChild = null;
    }

    /*
     * Constructor:
     *     hasRightChild
     * Purpose:
     *     return true if right child
     *
     * @param none
     * @return none
     */
    public boolean hasRightChild() {
        return (rightChild != null);
    }

    /*
     * Constructor:
     *     hasLeftChild
     * Purpose:
     *     return true if left child
     *
     * @param none
     * @return none
     */
    public boolean hasLeftChild() {
        return (leftChild != null);
    }

    // Setters (not documented)
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // Getters (not documented)
    public int getKey() {
        return key;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

}