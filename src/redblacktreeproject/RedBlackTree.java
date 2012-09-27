package redblacktreeproject;

/**
 * Implementation of a red-black tree
 *
 * @author vravuri
 */
public class RedBlackTree {

    private RedBlackNode root;
    private RedBlackNode leaf;
    private int recentCompares;
    private int numberOfNodes;

    /**
     * Default constructor. Initializes the red-black tree
     */
    public RedBlackTree() {
        // Creates the NULL node which is the common leaf node
        root = leaf = new RedBlackNode("-1", RedBlackNode.BLACK, null, null, null);
        recentCompares = 0;
        numberOfNodes = 0;
    }

    /**
     * Returns a value close to v in the tree. If v is found in the tree, it
     * returns v<br> Running Time(best case): Big Theta(1)<br> Running
     * Time(worst case): Big Theta(log n)<br>
     *
     * @param v The value to search close by for
     * @return Data in the tree that is the closest to v
     */
    public String closeBy(String v) {
        return getCloseNode(root, v);
    }

    /**
     * Returns true if the String v is in the RedBlackTree and false otherwise.
     * It counts each comparison it makes<br> Running Time(best case): Big
     * Theta(1)<br> Running Time(worst case): Big Theta(log n)<br>
     *
     * @param v The value to search for
     * @return True if the data is found, false otherwise
     */
    public boolean contains(String v) {
        recentCompares = 0;
        return checkForData(root, v);
    }

    /**
     * Returns the comparisons made for the most recent compare() method
     * call<br> Running Time(both best and worst case): Big Theta(1)
     *
     * @return Number of comparisons made in last call on the contains method
     */
    public int getRecentCompares() {
        return this.recentCompares;
    }

    /**
     * Returns the number of nodes in the tree<br> Running Time(both best and
     * worst case): Big Theta(1)
     *
     * @return Number of values inserted into the tree
     */
    public int getSize() {
        return this.numberOfNodes;
    }

    /**
     * Returns the height of the tree<br> Running Time(best case): Big
     * Theta(1)<br> Running Time(worst case): Big Theta(log n)<br>
     *
     * @return Height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Performs in-order traversal of the tree and displays the content<br>
     * Running Time(best and worst case): Big Theta(n)<br>
     *
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Inserts the data in the tree<br> Running Time(best case): Big
     * Theta(1)<br> Running Time(worst case): Big Theta(log n)<br>
     *
     * @param value Data to be inserted
     */
    public void insert(String value) {
        RedBlackNode z = new RedBlackNode(value, RedBlackNode.RED, null, null, null);
        RedBlackNode y = this.leaf;
        RedBlackNode x = this.root;

        while (x != this.leaf) {
            y = x;
            if (x.getData().compareTo(value) > 0) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
        }
        z.setP(y);
        if (y == this.leaf) {
            this.root = z;
        } else {
            if (z.getData().compareTo(y.getData()) > 0) {
                y.setRc(z);
            } else {
                y.setLc(z);
            }
        }
        z.setLc(this.leaf);
        z.setRc(this.leaf);
        RBInsertFixup(z);
    }

    /**
     * Performs a level order traversal and displays the tree contents during
     * traversal<br> Running Time(best and worst case): Big Theta(n)<br>
     */
    public void levelOrderTraversal() {
        Queue nodeList = new Queue();
        RedBlackNode node;
        if (this.root == this.leaf) {
            return;
        }

        nodeList.enQueue(this.root);
        while (!nodeList.isEmpty()) {
            node = (RedBlackNode) nodeList.deQueue();
            System.out.println(node);
            if (node.getLc() != this.leaf) {
                nodeList.enQueue(node.getLc());
            }
            if (node.getRc() != this.leaf) {
                nodeList.enQueue(node.getRc());
            }
        }
    }

    /**
     * Performs a single left rotation. Called by RBInsertFixup() while
     * balancing the tree<br> Running Time(both best and worst case): Big
     * Theta(1)
     *
     * @param node Node around which left rotation has to be performed
     */
    private void leftRotate(RedBlackNode x) {
        if (x.getRc() == this.leaf) {
            return;
        }

        if (this.root.getP() != this.leaf) {
            return;
        }

        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());

        if (x.getP() == this.leaf) {
            this.root = y;
        } else {
            if (x.getP().getLc() == x) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
    }

    /**
     * Performs a single right rotation. Called by RBInsertFixup() while
     * balancing the tree<br> Running Time(both best and worst case): Big
     * Theta(1)
     *
     * @param node Node around which right rotation has to be performed
     */
    private void rightRotate(RedBlackNode x) {
        if (x.getLc() == this.leaf) {
            return;
        }

        if (this.root.getP() != this.leaf) {
            return;
        }

        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if (x.getP() == this.leaf) {
            this.root = y;
        } else {
            if (x.getP().getLc() == x) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setRc(x);
        x.setP(y);
    }

    /**
     * Performs fixup so that Red-Black tree properties are preserved<br>
     * Running Time(best case): Big Theta(1)<br> Running Time(worst case): Big
     * Theta(log n)<br>
     *
     * @param node Current node on which fixup is performed
     */
    private void RBInsertFixup(RedBlackNode z) {
        RedBlackNode y;
        while (z.getP().getColor() == RedBlackNode.RED) {
            if (z.getP().getP().getLc() == z.getP()) {
                y = z.getP().getP().getRc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z.getP().getRc() == z) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getLc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z.getP().getLc() == z) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        this.root.setColor(RedBlackNode.BLACK);
    }

    /**
     * Called by inOrderTraversal() to perform in-order traversal. Uses
     * recursion to perform the traversal<br> Running Time(best and worst case):
     * Big Theta(n)<br>
     *
     * @param node Current node for which in-order traversal is performed
     */
    private void inOrderTraversal(RedBlackNode node) {
        if (node.getLc() != this.leaf) {
            inOrderTraversal(node.getLc());
        }
        System.out.println(node);
        if (node.getRc() != this.leaf) {
            inOrderTraversal(node.getRc());
        }
    }

    /**
     * Called by height() to get the height of the tree. Uses recursion to
     * calculate the height<br> Running Time(best and worst case): Big Theta(log
     * n)<br>
     *
     * @param node Current node for which height is being checked
     * @return Height of the current node
     */
    private int height(RedBlackNode node) {
        if (node == leaf || (node.getLc() == leaf) && (node.getRc() == leaf)) {
            // If it is the leaf, then the height is 0
            return 0;
        }
        // At any moment, height of a node is 1 plus max of height of its left and right child
        return 1 + Math.max(height(node.getLc()), height(node.getRc()));
    }

    /**
     * Called by contains() to check for the existence of data in the tree. Uses
     * recursion to find the data in the tree.<br> Running Time(best and worst
     * case): Big Theta(log n)<br>
     *
     * @param node Node being compared
     * @param data Data that is being searched
     * @return True/false based on whether data is found or not
     */
    private boolean checkForData(RedBlackNode node, String data) {
        if (node == leaf) {
            // If the node is leaf, then return false since the value hasnt been found
            recentCompares++;
            return false;
        } else if (node.getData().compareTo(data) == 0) {
            // The value has been found, so return true
            recentCompares++;
            return true;
        } else if (node.getData().compareTo(data) < 0) {
            // The data in the node is smaller, so a recursive call to right node.
            recentCompares++;
            return checkForData(node.getRc(), data);
        } else {
            // The data in the node is larger, so a recursive call to left node.
            recentCompares++;
            return checkForData(node.getLc(), data);
        }
    }

    /**
     * Used by closeBy() to get the closest node. Uses recursion to track the
     * closest node<br> Running Time(best and worst case): Big Theta(log n)<br>
     *
     * @param node Node that is being compared to
     * @param data Data that is being compared
     * @return The closest node if found
     */
    private String getCloseNode(RedBlackNode node, String data) {
        String returnData;

        if (node == leaf) {
            // If the node is leaf, then return null since the value hasnt been found
            return null;
        } else if (node.getData().compareTo(data) == 0) {
            // The value has been found, so return the data
            return node.getData();
        } else if (node.getData().compareTo(data) < 0) {
            // The data in the node is smaller, so a recursive call to right node.
            // If the return value is null, it implies the child node was null, 
            // so it will return the current node as the closest value.
            if ((returnData = getCloseNode(node.getRc(), data)) == null) {
                return node.getData();
            } else {
                return returnData;
            }
        } else {
            // The data in the node is larger, so a recursive call to left node.
            // If the return value is null, it implies the child node was null, 
            // so it will return the current node as the closest value.
            if ((returnData = getCloseNode(node.getLc(), data)) == null) {
                return node.getData();
            } else {
                return returnData;
            }
        }
    }

    /**
     * Test driver for Red-Black tree
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        for (int j = 1; j <= 5; j++) {
            rbt.insert("" + j);
        }

        // after 1..5 are inserted
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTraversal();

        // is 3 in the tree

        if (rbt.contains("" + 3)) {
            System.out.println("Found 3");
        } else {
            System.out.println("No 3 found");
        }

        // display the height
        System.out.println("The height is " + rbt.height());

    }
}
