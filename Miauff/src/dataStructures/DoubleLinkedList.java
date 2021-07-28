package dataStructures;

public interface DoubleLinkedList <E> {
    E popFront();
    void pushFront(E Object);
    E topFront();
    E popBack();
    void pushBack(E Object);
    E topBack();
    boolean isEmpty();
    void printList();
    
}