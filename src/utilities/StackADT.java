package utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT for a stack.
 *
 * @param <E> the type of element stored in the stack
 */
public interface StackADT<E> {

    /**
     * Adds an element to the top of the stack.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The given element is now at the top of the stack.
     * - The stack size increases by 1.
     *
     * @param element the element to push
     */
    void push(E element);

    /**
     * Removes and returns the topmost element from the stack.
     *
     * Preconditions:
     * - The stack is not empty.
     *
     * Postconditions:
     * - The top element is removed from the stack.
     * - The stack size decreases by 1.
     *
     * @return the removed top element
     * @throws NoSuchElementException if the stack is empty
     */
    E pop();

    /**
     * Returns the topmost element without removing it.
     *
     * Preconditions:
     * - The stack is not empty.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @return the top element
     * @throws NoSuchElementException if the stack is empty
     */
    E peek();

    /**
     * Returns true if this stack contains the same items in the same order as
     * the given stack.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - Neither stack is modified.
     *
     * @param that the stack to compare with
     * @return true if the stacks are equal, otherwise false
     */
    boolean equals(StackADT<E> that);

    /**
     * Returns an iterator over the items in this stack.
     * The iterator traverses items from top to bottom.
     * The iterator's remove() method is not supported.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @return an iterator over the stack contents
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all items in this stack.
     * The top of the stack corresponds to the first element of the array.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @return an array containing the stack contents
     */
    Object[] toArray();

    /**
     * Returns an array containing all items in this stack.
     * The top of the stack corresponds to the first element of the array.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @param copy the array into which the elements of the stack are stored,
     *             if it is large enough; otherwise, a new array is allocated
     * @return an array containing the stack contents
     */
    E[] toArray(E[] copy);

    /**
     * Returns the 1-based position of the given object from the top of the stack.
     * The top item is position 1.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @param obj the object to search for
     * @return the 1-based position of the object, or -1 if not found
     */
    int search(E obj);

    /**
     * Returns true if this stack contains the given object.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @param obj the object to look for
     * @return true if the object is found, otherwise false
     */
    boolean contains(E obj);

    /**
     * Returns the number of elements in the stack.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @return the stack size
     */
    int size();

    /**
     * Returns true if the stack is empty.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack is unchanged.
     *
     * @return true if the stack is empty, otherwise false
     */
    boolean isEmpty();

    /**
     * Removes all elements from the stack.
     *
     * Preconditions:
     * - None.
     *
     * Postconditions:
     * - The stack becomes empty.
     */
    void clear();
}