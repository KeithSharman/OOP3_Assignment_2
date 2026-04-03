package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import utilities.Iterator;

/*
 * built off of myDLL
 * front = index 0
 * back = end
 */
@SuppressWarnings("unchecked")
public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> list;

    // basic constructor
    public MyQueue() {
        list = new MyDLL<>();
    }

    // add to back of queue
    @Override
    public void enqueue(E toAdd) throws NullPointerException {

        if(toAdd == null){
            throw new NullPointerException("null not allowed");
        }

        list.add(toAdd);   // DLL handles adding to end
    }

    // remove from front (index 0)
    @Override
    public E dequeue() throws EmptyQueueException {

        if( isEmpty() ){
            throw new EmptyQueueException("Queue is empty");
        }

        return list.remove(0);
    }

    // look at front element
    @Override
    public E peek() throws EmptyQueueException {

        if(isEmpty()){
            throw new EmptyQueueException("Queue is empty");
        }

        return list.get(0);
    }

    // remove everything
    @Override
    public void dequeueAll() {

        while( !isEmpty() ){
            list.remove(0);
        }
    }
    

    // check if empty
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // check if element exists
    @Override
    public boolean contains(E toFind) throws NullPointerException {

        if(toFind == null){
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

    // return 1-based position
    @Override
    public int search(E toFind) {

        int pos = 1;

        Iterator<E> it = list.iterator();

        while(it.hasNext()){

            if(it.next().equals(toFind)){
                return pos;
            }

            pos++;
        }

        return -1;
    }

    // return iterator from DLL
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    // compare two queues
    @Override
    public boolean equals(QueueADT<E> that) {

        if(that == null || this.size() != that.size()){
            return false;
        }

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = that.iterator();

        while(it1.hasNext()){

            E a = it1.next();
            E b = it2.next();

            if(!a.equals(b)){
                return false;
            }
        }

        return true;
    }

    // convert to Object[]
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

    // convert to typed array
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {

        if(holder == null){
            throw new NullPointerException();
        }

        if(holder.length < size()){
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

    // no fixed size
    @Override
    public boolean isFull() {
        return false;
    }

    // return size
    @Override
    public int size() {
        return list.size();
    }
}