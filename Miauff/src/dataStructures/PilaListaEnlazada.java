package dataStructures;

public class PilaListaEnlazada<E> extends ListaSimplementeEnlazada<E> implements Stack<E>{
    
    public PilaListaEnlazada(){
    
    }
    
    @Override
    public void Push(E Object){
        this.pushFront(Object);
    }
    @Override
    public E Pop(){
        return this.popFront();
    }
    @Override
    public E Top(){
        return this.topFront();
    }
    @Override   
    public void makeEmpty(){
        if(this.head==null){
            throw new RuntimeException("Pila vacía");
        }else{
            while(this.head!=null){
                this.Pop();
            }
            System.out.println("Pila vacía");
        }
    }
    @Override
    public void printStack(){
        this.printList();
        
    }
    @Override  
    public int contar(){
        int contador = 0;
        if(this.head==null){
            
        }else{
        Nodo <E> temp = this.head;
        
        while(temp!=null){
        contador++;
        temp=temp.next;
        }          
        }
        return contador;
    }
    

   
    @Override  
    public boolean esta(E Object){
       boolean is = false;
        if(this.head==null){
           
        }else{
        Nodo <E> temp = this.head;
        
        while(temp!=null){   
            if(temp.key==Object){
                is = true;
            }
            temp=temp.next;
        }          
        }
       
       return is;
    }

    
    
    
}