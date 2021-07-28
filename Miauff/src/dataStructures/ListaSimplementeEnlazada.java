package dataStructures;

public class ListaSimplementeEnlazada <E> implements SingleLinkedList<E>{
    Nodo <E> head;
    
    public ListaSimplementeEnlazada(){
    
    }
    @Override
    public void pushFront(E Object){
        Nodo <E> nodo1 = new Nodo<>(Object,null,null);
        if(this.head==null){
            head=nodo1;
        }else{
            
            nodo1.next=head;
            this.head = nodo1;
        }
    }
    @Override
    public E topFront(){
        if(this.head==null){
            throw new RuntimeException("No hay elementos en la lista");
        }
        return head.getKey();
    }
    @Override
    public E popFront(){
        if(this.head==null){
            throw new RuntimeException("No hay elementos en la lista");
        }
        E temp = head.getKey();
        this.head=head.getNext();
        return temp;
    }
    @Override
    public boolean isEmpty(){
        return this.head==null;
    }
    
    
    public void printList(){
        if(this.head==null){
            System.out.println("No hay elementos en la lista");
        }
        Nodo<E> temp = this.head;
        
        while(temp!=null){
        System.out.println(temp.key);
        temp=temp.next;
        }
    }
	@Override
	public void popIndex(int num) {
		// TODO Auto-generated method stub
		
	}
	
    
}

