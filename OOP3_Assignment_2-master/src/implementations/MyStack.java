package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.lang.reflect.Array;
import java.util.EmptyStackException;

public class MyStack<E> implements StackADT<E> {

    private MyArrayList<E> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    @Override
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        if (list.isEmpty())
            throw new EmptyStackException();

        return list.remove(list.size() - 1);
    }

    @Override
    public E peek() {
        if (list.isEmpty())
            throw new EmptyStackException();

        return list.get(list.size() - 1);
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null || this.size() != that.size())
            return false;

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = that.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            E a = it1.next();
            E b = it2.next();
            if (!a.equals(b))
                return false;
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[list.size()];
        int index = 0;

        // Stack top must be first element of array
        for (int i = list.size() - 1; i >= 0; i--) {
            arr[index++] = list.get(i);
        }

        return arr;
    }


	@Override
	@SuppressWarnings("unchecked")
    public E[] toArray(E[] copy) {
        if (copy == null)
            throw new NullPointerException("The array is null");

        int size = list.size();
        
        if (copy.length < size) {
            copy = (E[]) Array.newInstance(copy.getClass().getComponentType(), size);
        }

        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            copy[index++] = list.get(i);
        }

        return copy;
    }

    @Override
    public int search(E obj) {
        if (obj == null)
            return -1;

        // Top of stack = index size-1 = position 1
        int position = 1;

        for (int i = list.size() - 1; i >= 0; i--) {
            if (obj.equals(list.get(i)))
                return position;
            position++;
        }

        return -1;
    }

    @Override
    public boolean contains(E obj) {
        if (obj == null)
            return false;

        return list.contains(obj);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

	public boolean stackOverflow() {
		return false;
	}
}