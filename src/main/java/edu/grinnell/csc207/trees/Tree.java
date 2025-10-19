package edu.grinnell.csc207.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic binary tree implementation.
 */
public class Tree<T extends Comparable<? super T>> {

    ///// From the reading
    
    // N.B., the Node<T> class is made public for this lab, so that you can
    // construct trees without an insert-style method!

    private Node<T> root;

    /**
     * Constructs a new, empty binary tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Constructs a new binary tree with the given root node.
     * @param root the root node of the tree
     */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * @param node the root of the tree 
     * @return the number elements found in this tree rooted at node
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /** @return the number of elements in the tree */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Contains

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        Node<T> node = root;
        if (node == null){
            return false;
        }
        else {
            return containsHelper(value, node);
        }
    }

    public boolean containsHelper(T value, Node<T> node){
        if (node == null){
            return false;
        }
        if (node.value.equals(value)){
            return true;
        }
        if (containsHelper(value, node.left)){
            return true;
        }
        else {
            return containsHelper(value, node.right);
        }
    }



    ///// Part 2: toString
   
    /**
     * @return a string represent of this tree in the form, "[x1, ..., xk]."
     * The order of the elements is left unspecified.
     */
    @Override
    public String toString() {
        return "[" + toStringHelper(root).trim() + "]";
}

/**
 * Recursively collect values into a string.
 */
private String toStringHelper(Node<T> node) {
    if (node == null) {
        return "";
    }

    String left = toStringHelper(node.left);
    String right = toStringHelper(node.right);

    String result = "";
    if (!left.isEmpty()) {
        result += left + ", ";
    }
    result += node.value;
    if (!right.isEmpty()) {
        result += ", " + right;
    }
    return result;
}
    
    ///// Part 3: Traversals

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> result = new ArrayList<>();
        toListInorderHelper(root, result);
        return result;
    }

    private void toListInorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return; 
        }

        toListInorderHelper(node.left, list);
        list.add(node.value);
        toListInorderHelper(node.right, list);
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> result = new ArrayList<>();
        toListPreorderHelper(root, result);
        return result;
    }

    private void toListPreorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return; 
        }

        list.add(node.value); 
        toListPreorderHelper(node.left, list); 
        toListPreorderHelper(node.right, list); 
    }
    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> result = new ArrayList<>();
        toListPostorderHelper(root, result);
        return result;
    }

    private void toListPostorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return; 
        }

        toListPostorderHelper(node.left, list);  
        toListPostorderHelper(node.right, list);
        list.add(node.value);   
    }

    ///// Extra: Pretty Printing
    
    /**
     * @return a string represent of this tree in bulleted list form.
     */
    public String toPrettyString() {
        return toPrettyStringHelper(root, 0);
    }

    private String toPrettyStringHelper(Node<T> node, int depth) {
        if (node == null) {
            return "";
        }

        String indent = "";
        for (int i = 0; i < depth; i++) {
            indent += "  ";  
        }

        String s = indent + "- " + node.value + "\n";
        s += toPrettyStringHelper(node.left, depth + 1);
        s += toPrettyStringHelper(node.right, depth + 1);

        return s;
    }
    /**
     * The main driver for this program
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Nothing to do. 'Run' via the JUnit tests instead!");
    }
}
