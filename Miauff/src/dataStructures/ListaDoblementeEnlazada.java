package dataStructures;

class ListaDoblementeEnlazada <E> implements DoubleLinkedList<E>{
    Nodo <E> head;
    Nodo <E> tail;
    public ListaDoblementeEnlazada(){
    
    }
    @Override
    public void pushFront(E Object){
        Nodo <E> nodo1 = new Nodo<>(Object,null,null);
        if(this.head==null){
            head=tail=nodo1;
        }else{
            nodo1.next=head;
            head.prev=nodo1;
            this.head = nodo1;
        }
    }
    
    public E topFront(){
        if(this.head==null){
            throw new RuntimeException("No hay elementos en la lista");
        }
        return head.getKey();
    }
    
    public E popFront(){
        if(head==tail){
            head = tail = null;
        }
        E temp = head.getKey();
        this.head = head.getNext();
        head.prev = null;
        return temp;
    }
    
    public void pushBack(E Object){
        Nodo <E> nodo1 = new Nodo<>(Object,null,null);
        if(this.tail==null){
            head=tail=nodo1;
        }else{
            nodo1.prev=tail;
            tail.next=nodo1;
            
        }
    }
    
    public E topBack(){
        if(this.head==null){
            throw new RuntimeException("No hay elementos en la lista");
        }
        return tail.getKey();
    }
    
    public E popBack(){
        E temp = tail.getKey();
        if(head==tail){
            head = tail = null;
            return temp;
        }else{
            
            this.tail = tail.getPrev();
            tail.next = null;
            return temp;
        }
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    public void printList(){
     
        if(this.head==null){
            System.out.print("");
        }
        Nodo <E> temp = this.head;
        
        while(temp!=null){
            if(temp.next==null){
                System.out.print(temp.key);
            }else{
                System.out.println(temp.key+" ");
            }
            
            temp=temp.next;
        }
      
    }
    
    public void printList2(){
        System.out.print("[");
        if(this.head==null){
            System.out.print("");
        }
        Nodo <E> temp = this.tail;
        
        while(temp!=null){
            if(temp.prev==null){
                System.out.print(temp.key);
            }else{
                System.out.print(temp.key+" ");
            }
            
            temp=temp.prev;
        }
        System.out.print("]");
    }
}
