package implementations;

class MyDLLNode<E> {
    private E element;
    private MyDLLNode<E> next;
    private MyDLLNode<E> previous;

    MyDLLNode(E element, MyDLLNode<E> previous, MyDLLNode<E> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    E getElement() {
        return element;
    }

    void setElement(E element) {
        this.element = element;
    }

    MyDLLNode<E> getNext() {
        return next;
    }

    void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    MyDLLNode<E> getPrevious() {
        return previous;
    }

    void setPrevious(MyDLLNode<E> previous) {
        this.previous = previous;
    }
}