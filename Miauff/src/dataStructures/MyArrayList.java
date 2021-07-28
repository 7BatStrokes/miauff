package dataStructures;

import java.util.Arrays;

public class MyArrayList<O> implements SingleLinkedList<O> { 
	
	@SuppressWarnings("unchecked")
	public O[] array = (O[]) new Object[1];
	public int size;

	@Override
	public boolean isEmpty(){
		return size == 0;
	}

	@Override
	public void pushFront(O Object){
		if (size >= array.length){
			@SuppressWarnings("unchecked")
			O[] newArray = (O[]) new Object[array.length * 2];
			System.arraycopy(array, 0, newArray, 0, size);
			this.array = newArray;

		}
		 array[size++] = Object;
}
 @Override
    public O topFront(){
        
        if(isEmpty()){
            
            throw new RuntimeException("Array vacío");
        }
        return array[size-1];
        
    }
    
    @Override
    public O popFront(){
        
        if(isEmpty()){
            
            throw new RuntimeException("Array vacío");
        }
        size--;
        O temp= this.array[size];
        this.array[size] = null;
        return temp;
        
    }
    @Override
    public void printList(){
        int cont=1;
        for(int i = 0; i<size;i++ ){
        	String im = String.valueOf(cont);
        	String array = this.array[i].toString().replace("#", "").replace("-", " : ").replace(",", System.lineSeparator());
            System.out.println( im + ")"+ array);
            cont+=1;         
        }
    }
    
    public int contar(){
        return size;
    }
    
    public boolean esta(O Object){
        boolean is = false;
        for(int i = 0; i<size;i++ ){
            if(array[i]==Object){
                is=true;
            } 
        }
        return is;
    } 

	@Override
	public String toString() {
		return  Arrays.toString(array).replace("[", "").replace("]", "").trim();
	}

    @Override
    public void popIndex(int num){
        
        if(isEmpty()){
            
            throw new RuntimeException("Array vacío");
        }       
        this.array[num-1]=null;
        
        
    }
    
}


	