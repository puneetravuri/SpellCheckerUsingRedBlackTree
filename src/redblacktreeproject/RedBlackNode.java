package redblacktreeproject;

/**
 * Node element of the red-black tree. Holds a string data within
 *
 * @author vravuri
 */
public class RedBlackNode {

    /**
     * To indicate that node color is black
     */
    public static int BLACK = 0;
    /**
     * To indicate that node color is red
     */
    public static int RED = 1;
    private String data;
    private int color;
    private RedBlackNode parent;
    private RedBlackNode leftChild;
    private RedBlackNode rightChild;

    /**
     * Constructor for red-black tree node
     *
     * @param data A simple value held in the tree
     * @param color Either RED or BLACK
     * @param p The parent pointer
     * @param lc The pointer to the left child
     * @param rc The pointer to the right child
     */
    public RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.data = data;
        this.color = color;
        this.parent = p;
        this.leftChild = lc;
        this.rightChild = rc;
    }

    /**
     * Returns the color of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @return Color of the node
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Returns the data in the node<br> Running Time (both best and worst case):
     * Big Theta(1)
     *
     * @return Data in the node
     */
    public String getData() {
        return this.data;
    }

    /**
     * Returns the left child of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @return Left child of the node
     */
    public RedBlackNode getLc() {
        return this.leftChild;
    }

    /**
     * Returns the right child of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @return Right child of the node
     */
    public RedBlackNode getRc() {
        return this.rightChild;
    }

    /**
     * Returns the parent of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @return Parent of the node
     */
    public RedBlackNode getP() {
        return this.parent;
    }

    /**
     * Sets the data of the node<br> Running Time (both best and worst case):
     * Big Theta(1)
     *
     * @param data Data to be set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Sets the color of the node<br> Running Time (both best and worst case):
     * Big Theta(1)
     *
     * @param color Color to be set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Sets the left child of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @param lc Left child to be set
     */
    public void setLc(RedBlackNode lc) {
        this.leftChild = lc;
    }

    /**
     * Sets the right child of the node<br> Running Time (both best and worst
     * case): Big Theta(1)
     *
     * @param rc Right child to be set
     */
    public void setRc(RedBlackNode rc) {
        this.rightChild = rc;
    }

    /**
     * Sets the parent of the node<br> Running Time (both best and worst case):
     * Big Theta(1)
     *
     * @param p Parent to be set
     */
    public void setP(RedBlackNode p) {
        this.parent = p;
    }

    /**
     * Returns a String representation of the node contents<br> Running Time
     * (both best and worst case): Big Theta(1)
     *
     * @return String representation of the node
     */
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append("[Data: ").append(this.data).append(", Color: ").
                append(this.color == BLACK ? "Black" : "Red").append(", Parent: ").
                append(this.parent.getData()).append(", Left Child: ").
                append(this.leftChild.getData()).append(", RightChild: ").
                append(this.rightChild.getData()).append("]");
        return content.toString();
    }
}
