import java.util.*;
public class IDLList<E>{
    private class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        
        private Node(E elem){
            data = elem;
        }

        private Node(E elem, Node<E> prev, Node<E> next){
            data = elem;
            this.prev = prev;
            this.next = next;
            
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
    
    public IDLList(){
        size = 0;
        head = null;
        tail = null;
     indices = new ArrayList<Node<E>>();
    }

    private boolean checkEmptyList(){//not used
        if(head == null || tail == null)
                return true;
        else return false;
    }
    public boolean add(int index, E elem){//working
        Node<E> added = new Node<E>(elem);
        if(index < 0 || index > size){
            System.out.println("Invalid index "+ index +"!");
            return false;
        }
        else if(index == 0){
            return add(elem);
        }
        else if(index == size){
            return append(elem);
        }
        else{
            indices.add(index, added);

            indices.get(index+1).prev = added;
            added.next = indices.get(index+1);
        
            indices.get(index-1).next = added;
            added.prev = indices.get(index-1);
        
            size++;
        return true;
        }
    }
    public boolean add(E elem){//working
            
            Node<E> newhead = new Node<E>(elem);
            if(head != null){
                indices.add(0, newhead);
                newhead.prev = null;
                newhead.next = indices.get(1);

                indices.get(1).prev = newhead;
                size++;
            }
            else{ 
                head = newhead;
                tail = newhead;
                indices.add(0,newhead);
                size++;
            }
            return true;
    }
    
    public boolean append(E elem){//working
        Node<E> newtail = new Node<E>(elem);
        if(checkEmptyList()){
            return add(elem);
        }
        indices.add(newtail);
        size++;//change array contents
         
        newtail.prev = tail;
        tail.next = newtail;
        return true;
    }

    public E get(int index){//working
        if(index >= size || index < 0){
            System.out.println("Invalid index, returning null for index: " + index);
            return null;
        }
        else return indices.get(index).data;
    }

    public E getHead(){//working
        if(checkEmptyList()){
            System.out.println("Empty List, returning null");
            return null;
        }
        return head.data;
    }

    public E getLast(){//working
        if(checkEmptyList()){
            System.out.println("Empty List, returning null");
            return null;
        }
        return indices.get(size-1).data;
    }

    public int size(){//working
        return size;
    }
    public E remove(){//working
        if(checkEmptyList()){
            System.out.println("Add to list before removing!");
            return null;
        }
        head.next.prev = null;
        head.next = null;
        size--;
        return indices.remove(0).data;
    }
    public E removeLast(){//working
        if(checkEmptyList()){
            System.out.println("Add to list before removing!");
            return null;
        }
        indices.get(size-1).prev = null;
        try{
        indices.get(size-2).next = null;}
        catch(IndexOutOfBoundsException e){
            
        }
       return indices.remove(--size).data;
    }
    public boolean remove(E elem){//working
        int index = -1;
        
        for(int i = 0 ; i < size;i++)
              if(indices.get(i).data.equals(elem))
                index = i;
        
        if(index == 0){
            remove();
            return true;
        }
        else if (index == size-1){
            removeLast();
            return true;
        }
        else if(index > 0){//if valid index
            indices.get(index+1).prev = indices.get(index-1);
            indices.get(index -1).next = indices.get(index+1);
            indices.remove(index); 
            size--;
            return true;
        }
        else return false;

    }
    public E removeAt(int index){//working
        if(index > size - 1 || index < 0){
            System.out.println("Invalid index, returning null for index: " + index);
            return null;
        }
        else if(index == 0)
            return remove();
        else if (index == size-1)
            return removeLast();
        else{
            indices.get(index-1).next = indices.get(index+1);
            indices.get(index+1).prev = indices.get(index-1);
            size--;
            return indices.remove(index).data;
        }
    }   

    public String toString(){//working 
        String array = "";
        for(int i = 0; i < indices.size();i++)
        array+= "Node "+ i +": " + indices.get(i).data + " ";
        return array;
    }

}