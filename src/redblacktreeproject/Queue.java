package redblacktreeproject;

/**
 * Implementation of a circular queue.
 *
 * @author vravuri
 */
public class Queue {

    private Object[] data;
    private int front;
    private int rear;
    private boolean isEmpty;

    /**
     * Default constructor. Initializes the queue.
     */
    public Queue() {
        data = new Object[262143];
        front = rear = 0;
        isEmpty = true;
    }

    /**
     * Removes and returns reference in front of the queue<br>Running Time (both
     * best and worst case): Big Theta(1)
     *
     * @return Dequeued object
     */
    public Object deQueue() {
        // If queue is empty, return null
        if (isEmpty) {
            System.out.println("Queue is already empty");
            return null;
        }

        Object selectedData = data[front];

        // Move the start pointer accordingly
        if (front == data.length - 1) {
            front = 0;
        } else {
            front++;
        }

        // If the dequeued object was the last element, set the flag appropriately
        if (front == rear) {
            isEmpty = true;
        }

        return selectedData;
    }

    /**
     * Add an object reference to the rear of the queue <br>Running Time (both
     * best and worst case): Big Theta(1)
     *
     * @param x Object to be enqueued
     */
    public void enQueue(Object x) {
        // If queue is empty, error out without proceeding
        if (front == rear && isEmpty == false) {
            System.out.println("Queue is full");
            return;
        }

        // Set the data to the location where rear is referring to
        data[rear] = x;

        // If front was also referring to the same location, it implies queue was empty earlier. 
        // So isEmpty flag has to be set to false
        if (front == rear) {
            isEmpty = false;
        }

        // Increment rear after enqueing the data
        if (rear == data.length - 1) {
            rear = 0;
        } else {
            rear++;
        }
    }

    /**
     * Return the front of the queue without removing it<br>Running Time (both
     * best and worst case): Big Theta(1)
     *
     * @return Object at the front of the queue
     */
    public Object getFront() {
        if (isEmpty) {
            return null;
        }

        return data[front];
    }

    /**
     * Returns true if queue is empty, false otherwise<br> Running Time (both
     * best and worst case): Big Theta(n)
     *
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     * Returns a String representation of the current queue contents<br> Running
     * Time (both best and worst case): Big Theta(n)
     *
     * @return String representation of the queue
     */
    @Override
    public String toString() {
        int iterate = front;
        StringBuilder contents = new StringBuilder();
        contents.append("Queue contents from front - ");

        if (isEmpty) {
            contents.append("none");
            return contents.toString();
        }
        while (iterate == rear) {
            contents.append(data[iterate].toString()).append(", ");
            if (iterate == data.length - 1) {
                iterate = 0;
            } else {
                iterate++;
            }
        }

        return contents.toString();
    }

    /**
     * Test driver for Queue
     *
     * @param args Not used
     */
    public static void main(String[] args) {
    }
}
