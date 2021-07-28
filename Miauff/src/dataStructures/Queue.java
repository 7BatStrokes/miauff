package dataStructures;

public interface Queue <E> {
   
    public void enqueue(E e);
    public E dequeue();
    public boolean isEmpty();
    public void printQueue();
    public E peek();
    
}