package dataStructures;

public interface SingleLinkedList <E> {
    E popFront();
    void pushFront(E Object);
    E topFront();
    boolean isEmpty();
    void printList();
	void popIndex(int num);

}