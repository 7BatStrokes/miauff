package dataStructures;

public class QueueDoubleLinkedList<E> implements Queue<E> {

    private ListaDoblementeEnlazada<E> lista;

    public QueueDoubleLinkedList() {
        this.lista = new ListaDoblementeEnlazada<>();
    }

    
    public void enqueue(E e) {
        this.lista.pushFront(e);
    }

    
    public E dequeue() {
        return this.lista.popBack();
    }

    
    public boolean isEmpty() {
        return this.lista.isEmpty();
    }
    
    public void printQueue(){
        this.lista.printList();
    }
    
    public E peek(){
        return this.lista.topBack();
    }


}