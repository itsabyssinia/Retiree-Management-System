package cs143.dataaccess;

import cs143.domain.Retiree;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class of AVL tree's for individual Retiree's
 * 
 */
public final class SsnAvl extends BST implements Serializable {

    public SsnAvl() {
    }

    /**
     * A constructor for the SSNAvl class
     *
     * @param objects - An array of Retiree objects
     */
    public SsnAvl(Retiree[] objects) {
        for (Retiree r : objects) {
            add(r);
        }
    }

    /**
     * Add the Retiree object to the AVL tree
     *
     * @param r the Retiree object to be added
     * @return true if successfully added and false if not
     */
    public boolean add(Retiree r) {
        boolean successful = super.insert(r);
        if (!successful) {
            return false;
        } else {
            balancePath(r);
        }
        return true;
    }

    /**
     * Remove the Retiree object from the AVL tree
     *
     * @param r the Retiree object to be removed
     * @return true if successfully removed and false if not
     */
    public boolean remove(Retiree r) {
        if (root == null) {
            return false;
        }
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
                break;
            }
        }
        //does not exist in AVL tree
        if (current == null) {
            return false;
        }
        //the current has no left children (See Figure 23.6)
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (r.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                balancePath(parent.element);
            }
        } else {
            //the current node has a left child
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
                parentOfRightMost.left = rightMost.left;
            }
            balancePath(parentOfRightMost.element);
        }
        size--;
        return true;
    }

    /**
     * A method to create a new Retiree node
     *
     * @param r - The new retiree to add
     * @return A new node containing the new Retiree
     */
    @Override
    protected AVLTreeNode createNewNode(Retiree r) {
        return new AVLTreeNode(r);
    }
    
    /**
     * This method updates the height when there is an imbalance in the AVL Tree
     *
     * @param node Node for the AVL Tree
     */
    private void updateHeight(AVLTreeNode node) {
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else if (node.left == null) {
            node.height = 1 + ((AVLTreeNode) (node.right)).height;
        } else if (node.right == null) {
            node.height = 1 + ((AVLTreeNode) (node.left)).height;
        } else {
            node.height = 1
                    + Math.max(((AVLTreeNode) (node.right)).height,
                            ((AVLTreeNode) (node.left)).height);
        }
    }

    /**
     * A method to balance the AVL tree
     *
     * @param r - A Retiree object
     */
    private void balancePath(Retiree r) {
        ArrayList<TreeNode> path = path(r);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode a = (AVLTreeNode) (path.get(i));
            updateHeight(a);
            AVLTreeNode parentOfA = (a == root) ? null
                    : (AVLTreeNode) (path.get(i - 1));
            switch (balanceFactor(a)) {
                case -2:
                    if (balanceFactor((AVLTreeNode) a.left) <= 0) {
                        balanceLL(a, parentOfA);
                    } else {
                        balanceLR(a, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode) a.right) >= 0) {
                        balanceRR(a, parentOfA);
                    } else {
                        balanceRL(a, parentOfA);
                    }
            }
        }
    }

    /**
     * A method to determine the balance factor of the tree
     *
     * @param node
     * @return the balance factor number
     */
    private int balanceFactor(AVLTreeNode node) {
        if (node.right == null) {
            return -node.height;
        } else if (node.left == null) {
            return +node.height;
        } else {
            return ((AVLTreeNode) node.right).height
                    - ((AVLTreeNode) node.left).height;
        }
    }
    
    /**
     * This method left-left balances the SsnAvl.
     * 
     * @param a the TreeNode
     * @param parentOfA the parent TreeNode
     */
    private void balanceLL(TreeNode a, TreeNode parentOfA) {
        TreeNode b = a.left;
        if (a == root) {
            root = b;
        } else if (parentOfA.left == a) {
            parentOfA.left = b;
        } else {
            parentOfA.right = b;
        }

        a.left = b.right;
        b.right = a;
        updateHeight((AVLTreeNode) a);
        updateHeight((AVLTreeNode) b);
    }
    
    /**
     * This method left-right balances the SsnAvl.
     * 
     * @param a the TreeNode
     * @param parentOfA the parent TreeNode
     */
    private void balanceLR(TreeNode a, TreeNode parentOfA) {
        TreeNode b = a.left;
        TreeNode c = b.right;
        if (a == root) {
            root = c;
        } else if (parentOfA.left == a) {
            parentOfA.left = c;
        } else {
            parentOfA.right = c;
        }
        a.left = c.right;
        b.right = c.left;
        c.left = b;
        c.right = a;
        updateHeight((AVLTreeNode) a);
        updateHeight((AVLTreeNode) b);
        updateHeight((AVLTreeNode) c);
    }

    /**
     * This method right-right balances the SsnAvl.
     * 
     * @param a the TreeNode
     * @param parentOfA the parent TreeNode
     */
    private void balanceRR(TreeNode a, TreeNode parentOfA) {
        TreeNode b = a.right;
        if (a == root) {
            root = b;
        } else if (parentOfA.left == a) {
            parentOfA.left = b;
        } else {
            parentOfA.right = a;
        }
        a.right = b.left;
        b.left = a;
        updateHeight((AVLTreeNode) a);
        updateHeight((AVLTreeNode) b);
    }

    /**
     * This method right-left balances the SsnAvl.
     * 
     * @param a the TreeNode
     * @param parentOfA the parent TreeNode
     */
    private void balanceRL(TreeNode a, TreeNode parentOfA) {
        TreeNode b = a.right;
        TreeNode c = b.left;
        if (a == root) {
            root = c;
        } else if (parentOfA.left == a) {
            parentOfA.left = c;
        } else {
            parentOfA.right = c;
        }
        a.right = c.left;
        b.left = c.right;
        c.left = a;
        c.right = b;
        updateHeight((AVLTreeNode) a);
        updateHeight((AVLTreeNode) b);
        updateHeight((AVLTreeNode) c);
    }
    
    /**
     * This inner static class represents an AVLTreeNode.
     */
    protected static class AVLTreeNode extends BST.TreeNode
            implements Serializable {

        protected int height = 0;

        public AVLTreeNode(Retiree r) {
            super(r);
        }
    }

}
