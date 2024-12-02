package cs143.dataaccess;

import cs143.domain.Retiree;
import java.io.Serializable;
import java.util.ArrayList;

/**
 */
public class BST implements Serializable {

    protected TreeNode root;
    protected int size = 0;

    /**
     * A default constructor
     */
    public BST() {
    }

    /**
     * A constructor for the BST class
     *
     * @param objects - A array of Retiree objects
     */
    public BST(Retiree[] objects) {
        for (Retiree object : objects) {
            insert(object);
        }
    }

    /**
     * Get the Retiree by their Social Security Number
     *
     * @param ssn the Social Security Number
     * @return the Retiree object
     */
    public Retiree get(long ssn) {
        TreeNode current = root;
        while (current != null) {
            if (ssn < current.element.getSsn()) {
                current = current.left;
            } else if (ssn > current.element.getSsn()) {
                current = current.right;
            } else {
                return current.element;
            }
        }
        return null;
    }

    /**
     * Insert the Retiree into the tree by their information
     *
     * @param r the Retiree object
     * @return true if success fully inserted and false if not
     */
    public final boolean insert(Retiree r) {
        if (root == null) {
            root = createNewNode(r);
        } else {
            //locate the parent node
            TreeNode parent = null;
            TreeNode current = root;
            while (current != null) {
                if (r.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (r.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            //create the new node and attach it to the parent node
            if (r.compareTo(parent.element) < 0) {
                parent.left = createNewNode(r);
            } else {
                parent.right = createNewNode(r);
            }
        }
        size++;
        return true;
    }

    /**
     * Delete a Retiree by their information
     *
     * @param e the Retiree object
     * @return true if success fully deleted and false if not
     */
    public boolean delete(Retiree e) {
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null) {
            return false;
        }
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            //The current node has a left child
            TreeNode parentOfRightMost = current;
            TreeNode rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                //parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    /**
     * This method creates a new TreeNode with the parameter Retiree object 
     * as its element.
     *
     * @param r an object of Retiree type
     * @return a new TreeNode with the parameter as its element
     */
    protected TreeNode createNewNode(Retiree r) {
        return new TreeNode(r);
    }
    
    /**
     * A getter for size
     *
     * @return an integer value that represents the size of this BST
     */
    public int getSize() {
        return size;
    }
    
    /**
     * A getter for the Root
     *
     * @return a TreeNode value that is the root of this BST
     */
    public TreeNode getRoot() {
        return root;
    }
    
    /**
     * This method clears everything in the BST
     */
    public void clear() {
        root = null;
        size = 0;
    }
    /**
     * This method determines whether or not the Binary Search Tree is empty or
     * not.
     *
     * @return a boolean variable that's true if it is empty or false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * This method returns the path of the Binary Search Tree via ArrayList
     *
     * @param r a Retiree that is part of the path
     * @return An ArrayList that represents the path of a Binary Search Tree
     */
    public ArrayList<TreeNode> path(Retiree r) {
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            list.add(current);
            if (r.compareTo(current.element) < 0) {
                current = current.left;
            } else if (r.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;

    }
    
    /**
     * This class defines a TreeNode which is a Node in a BST. Each Node has an
     * element, a left TreeNode, and a right TreeNode which are null unless 
     * otherwise stated.
     */
    public static class TreeNode implements Serializable {

        public Retiree element;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Retiree r) {
            element = r;
        }
    }

}
