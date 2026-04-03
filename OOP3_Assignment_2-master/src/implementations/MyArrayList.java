package implementations;

import utilities.ListADT;
import utilities.Iterator;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;

public class MyArrayList<E> implements ListADT<E> {

    private E[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
	public MyArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
	private void ensureCapacity() {
        if (size == data.length) {
            E[] newArr = (E[]) new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newArr[i] = data[i];
            }
            data = newArr;
        }
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Null elements not allowed");

        ensureCapacity();
        data[size++] = toAdd;
        return true;
    }

    @Override
    public boolean add(int index, E toAdd)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toAdd == null)
            throw new NullPointerException("Null elements not allowed");

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("List is null");

        for (int i = 0; i < toAdd.size(); i++) {
            add(toAdd.get(i));
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index);

        return data[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index);

        E removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException("Null elements not allowed");

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(data[i])) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public E set(int index, E toChange)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toChange == null)
            throw new NullPointerException("Null elements not allowed");

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index);

        E old = data[index];
        data[index] = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Null elements not allowed");

        for (int i = 0; i < size; i++) {
            if (toFind.equals(data[i]))
                return true;
        }
        return false;
    }


	@Override
	@SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null)
            throw new NullPointerException("Array is null");

        if (toHold.length < size) {
            toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        
        for (int i = 0; i < size; i++) {
        	toHold[i] = data[i];
        }
        
        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data[i];
        }
        return arr;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int cursor = 0;
        private E[] snapshot;

        @SuppressWarnings("unchecked")
		public MyArrayListIterator() {
            snapshot = (E[]) new Object[size];
            for (int i = 0; i < size; i++) {
                snapshot[i] = data[i];
            }
        }

        @Override
        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException();
            return snapshot[cursor++];
        }
    }
}