package dataStructures;

public interface Stack<E> {
    public void Push(E Object);
    public E Pop();
    public E Top();
    public boolean isEmpty();
    public void makeEmpty();
    public void printStack();
    public int contar();
    public boolean esta(E Object);
}
