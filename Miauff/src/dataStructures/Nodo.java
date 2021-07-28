package dataStructures;

public class Nodo<E> {
	public E key;
    protected Nodo<E> next;
    protected Nodo<E> prev;
    
    public Nodo(E key,Nodo<E> next,Nodo<E> prev){
        this.key = key;
        this.next = next;
        this.prev = prev;
        
    }

    public E getKey() {
        return key;
    }

    public Nodo<E> getNext() {
        return next;
    }

    public Nodo<E> getPrev() {
        return prev;
    }


    public void setKey(E key) {
        this.key = key;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }
    
    public void setPrev(Nodo<E> prev) {
        this.prev = prev;
    }
}
