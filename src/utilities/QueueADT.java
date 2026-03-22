package utilities;

import java.util.NoSuchElementException;

/**
 * ADT for a queue.
 *
 * @param <E> the type of element stored in the queue
 */
public interface QueueADT<E> {

    /**
     * Adds an element to the end of the queue.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The given element is now at the end of the queue.
     * - The queue size increases by 1.
     *
     * @param element the element to enqueue
     */
    void enqueue(E element);

    /**
     * Removes and returns the first element from the queue.
     *
     * Preconditions:
     * - The queue is not empty.
     *
     * Postconditions:
     * - The first element is removed from the queue.
     * - The queue size decreases by 1.
     *
     * @return the removed front element
     * @throws NoSuchElementException if the queue is empty
     */
    E dequeue();

    /**
     * Returns the first element without removing it.
     *
     * Preconditions:
     * - The queue is not empty.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return the front element
     * @throws NoSuchElementException if the queue is empty
     */
    E peek();

    /**
     * Returns true if this queue contains the same items in the same order as
     * the given queue.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Neither queue is modified.
     *
     * @param that the queue to compare with
     * @return true if the queues are equal, otherwise false
     */
    boolean equals(QueueADT<E> that);

    /**
     * Returns an iterator over the items in this queue.
     * The iterator traverses items from front to rear.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return an iterator over the queue contents
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all items in this queue.
     * The head of the queue corresponds to the first element of the array.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return an array containing the queue contents
     */
    Object[] toArray();

    /**
     * Returns an array containing all items in this queue.
     * The head of the queue corresponds to the first element of the array.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @param copy the array into which the elements of the queue are stored,
     *             if it is large enough; otherwise, a new array is allocated
     * @return an array containing the queue contents
     */
    E[] toArray(E[] copy);

    /**
     * Returns the number of elements in the queue.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return the queue size
     */
    int size();

    /**
     * Returns true if the queue is empty.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return true if the queue is empty, otherwise false
     */
    boolean isEmpty();

    /**
     * Returns true if the queue is full.
     * This method is optional and only applies to fixed-size queues.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue is unchanged.
     *
     * @return true if the queue is full, otherwise false
     */
    boolean isFull();

    /**
     * Removes all elements from the queue.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue becomes empty.
     */
    void clear();

    /**
     * Removes all elements from the queue.
     * This is an alternate name for clear().
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The queue becomes empty.
     */
    void dequeueAll();
}