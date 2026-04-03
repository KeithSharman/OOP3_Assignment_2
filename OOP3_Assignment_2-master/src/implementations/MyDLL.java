package implementations;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            linkFirst(toAdd);
        } else if (index == size) {
            linkLast(toAdd);
        } else {
            linkBefore(toAdd, nodeAt(index));
        }
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        linkLast(toAdd);
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }

        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return nodeAt(index).getElement();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return unlink(nodeAt(index));
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toRemove.equals(current.getElement())) {
                return unlink(current);
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException();
        }
        checkIndex(index);

        MyDLLNode<E> node = nodeAt(index);
        E old = node.getElement();
        node.setElement(toChange);
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException();
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (toFind.equals(current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }

        E[] result = toHold.length >= size
                ? toHold
                : (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);

        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.getElement();
            current = current.getNext();
        }

        if (result.length > size) {
            result[size] = null;
        }

        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.getElement();
            current = current.getNext();
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator(copyChain(head));
    }

    private void linkFirst(E element) {
        MyDLLNode<E> newNode = new MyDLLNode<>(element, null, head);
        if (head != null) {
            head.setPrevious(newNode);
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    private void linkLast(E element) {
        MyDLLNode<E> newNode = new MyDLLNode<>(element, tail, null);
        if (tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    private void linkBefore(E element, MyDLLNode<E> successor) {
        MyDLLNode<E> predecessor = successor.getPrevious();
        MyDLLNode<E> newNode = new MyDLLNode<>(element, predecessor, successor);

        predecessor.setNext(newNode);
        successor.setPrevious(newNode);
        size++;
    }

    private E unlink(MyDLLNode<E> node) {
        MyDLLNode<E> prev = node.getPrevious();
        MyDLLNode<E> next = node.getNext();

        if (prev == null) {
            head = next;
        } else {
            prev.setNext(next);
        }

        if (next == null) {
            tail = prev;
        } else {
            next.setPrevious(prev);
        }

        size--;
        return node.getElement();
    }

    private MyDLLNode<E> nodeAt(int index) {
        if (index < (size / 2)) {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            MyDLLNode<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
            return current;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private MyDLLNode<E> copyChain(MyDLLNode<E> start) {
        if (start == null) {
            return null;
        }

        MyDLLNode<E> copyHead = new MyDLLNode<>(start.getElement(), null, null);
        MyDLLNode<E> copyCurrent = copyHead;
        MyDLLNode<E> current = start.getNext();

        while (current != null) {
            MyDLLNode<E> newNode = new MyDLLNode<>(current.getElement(), copyCurrent, null);
            copyCurrent.setNext(newNode);
            copyCurrent = newNode;
            current = current.getNext();
        }

        return copyHead;
    }

    private class DLLIterator implements Iterator<E> {
        private MyDLLNode<E> current;

        private DLLIterator(MyDLLNode<E> start) {
            current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E value = current.getElement();
            current = current.getNext();
            return value;
        }
    }
}