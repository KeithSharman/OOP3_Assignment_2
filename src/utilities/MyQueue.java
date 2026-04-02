package utilities;

import exceptions.EmptyQueueException;

/*
 * Queue implementation using MyDLL
 * front = head, back = tail
 */
@SuppressWarnings("unchecked")
public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> list;

    // constructor
    public MyQueue( ) {
        list = new MyDLL<>();
    }

    // adds to back of queue
    @Override
    public void enqueue(E toAdd) throws NullPointerException {

        if (toAdd == null){
            throw new NullPointerException("Cannot add null to queue");
        }

        list.addLast(toAdd);
    }

    // removes from front
    @Override
    public E dequeue() throws EmptyQueueException {

        if ( isEmpty() ) {
            throw new EmptyQueueException("Queue is empty");
        }

        return list.removeFirst();
    }

    // looks at front without removing
    @Override
    public E peek() throws EmptyQueueException {

        if(isEmpty()){
            throw new EmptyQueueException("Queue is empty");
        }

        return list.getFirst();
    }

    // clears everything
    @Override
    public void dequeueAll() {

        while( !isEmpty() ){
            list.removeFirst();
        }
    }

    // checks if empty
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // checks if item exists
    @Override
    public boolean contains(E toFind) throws NullPointerException {

        if (toFind == null){
            throw new NullPointerException();
        }

        Iterator<E> it = list.iterator();

        while(it.hasNext()){
            if(it.next().equals(toFind)){
                return true;
            }
        }

        return false;
    }

    // returns 1-based position
    @Override
    public int search(E toFind) {

        int position = 1;

        Iterator<E> it = list.iterator();

        while ( it.hasNext() ) {

            if(it.next().equals(toFind)){
                return position;
            }

            position++;
        }

        return -1;
    }

    // just return DLL iterator
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    // compares queues (same order + values)
    @Override
    public boolean equals(QueueADT<E> that) {

        if (that == null || this.size() != that.size()){
            return false;
        }

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = that.iterator();

        while(it1.hasNext()){

            E item1 = it1.next();
            E item2 = it2.next();

            if( !item1.equals(item2) ){
                return false;
            }
        }

        return true;
    }

    // returns as Object[]
    @Override
    public Object[] toArray() {

        Object[] arr = new Object[size()];

        int i = 0;

        Iterator<E> it = iterator();

        while(it.hasNext()){
            arr[i++] = it.next();
        }

        return arr;
    }

    // returns typed array
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {

        if(holder == null){
            throw new NullPointerException();
        }

        if (holder.length < size()){
            holder = (E[]) java.lang.reflect.Array.newInstance(
                    holder.getClass().getComponentType(), size());
        }

        int i = 0;

        Iterator<E> it = iterator();

        while(it.hasNext()){
            holder[i++] = it.next();
        }

        return holder;
    }

    // no max size
    @Override
    public boolean isFull() {
        return false;
    }

    // current size
    @Override
    public int size() {
        return list.size();
    }
}