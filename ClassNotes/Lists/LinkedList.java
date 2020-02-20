public class LinkedList<E>{
    private Node<E> head;
    private int size = 0;
    private static class Node<E>{
        private E data;
        private Node<E> next;

        private Node(E data){
            this.data = data;
            next = null;
        }

    }
    @Override
    public String toString(){
        String temp = "";
        Node<E> nodeRef = head;
        while(nodeRef!= null){
            temp += nodeRef.data;
            if(nodeRef.next != null)
            temp+= ", ";
            nodeRef = nodeRef.next;
        }
        return temp;
    }
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<String>();
        Node<String> tom = new Node<String>("Tom");
        Node<String> james = new Node<String>("James");
        Node<String> john = new Node<String>("John");
        tom.next = james;
        tom.next.next = john;
        ll.head = tom;
        System.out.println(ll);

    }
}