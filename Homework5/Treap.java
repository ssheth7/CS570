import java.util.ArrayList;
import java.util.Random;
public class Treap<E extends Comparable<E>> {
    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;
        public Node(E data, int priority){
            if(data == null)
                throw new NullPointerException();
            this.data = data;
            this.priority = priority;
            left = null;
            right = null;
        }
        public Node<E> rotateRight(){
            Node<E> root = this.left;
            this.left = root.right;
            root.right = this;
            return root;
        }

        public Node<E> rotateLeft(){
            Node<E> root = this.right;
            this.right = root.left;
            root.left = this;
            return root;
        }
        public String toString(){
            return "(key=" + data + ", priority=" + priority + ")";
        }
    }
    private Random prioritygenerator;
    private Node<E> root;

    public Treap(){
        root = null;
        prioritygenerator = new Random();
    }
    public Treap(long seed){
        root = null;
        prioritygenerator = new Random(seed);
    }
    public  boolean add(E key){
        return add(key, prioritygenerator.nextInt(100));
    }

    public boolean add(E key, int priority){
        if (root == null) {
			root = new Node<E>(key, priority);
			return true;
        } 
        else if(find(key) == true)
             return false;
        else {
            ArrayList<Node<E>> path = new ArrayList<Node<E>>();
            Node<E> current = root;
			while (true) {//Iterative Traverse
				int comparison = current.data.compareTo(key);
				if (comparison == 0) 
					return false;
				else { //Traverses until it reaches the bottom of the tree
                    if (comparison < 0) {
                        path.add(current);
						if (current.right == null) {
                            current.right = new Node<E>(key, priority);
                            reheap(path,current.right);
							return true;
						} else 
							current = current.right;
					} else {
                        path.add(current);
                        if (current.left == null) {
                            current.left = new Node<E>(key, priority);
                            reheap(path, current.left);
							return true;
						} else 
							current = current.left;
					}
				}
            }
		}
    }
    private void reheap(ArrayList<Node<E>> path, Node<E> added){
        for(int i = path.size()-1;i >= 0;i--){
            Node<E> parent = path.get(i); //parent of added
            if(parent.priority < added.priority){
                if(parent.data.compareTo(added.data) > 0)
                    added = parent.rotateRight();
                else added = parent.rotateLeft();
                if(i-1 >=0) //if current parent has a parent
                    if(path.get(i- 1).left == parent) path.get(i-1).left = added;
                    else path.get(i-1).right = added;
                else this.root = added;
                } else break;
        }
    }
    private E findLargestChild(Node<E> parent){
        if(parent.right.right == null){
            E returnvalue =parent.right.data;
            parent.right = parent.right.left;
            return returnvalue;
        }
        else return findLargestChild(parent.right);
    }
    public boolean delete(E key){
        if(root == null) return false;
        else if(find(key) == false) 
            return false;    
        root=delete(root, key);
        return true;

    }
    private Node<E> delete(Node<E> localroot, E item){
        if (localroot == null){
            return localroot;
        }   
        int compresult = item.compareTo(localroot.data);
        if(compresult < 0){
            localroot.left = delete(localroot.left, item);
            return localroot;
        } else if(compresult > 0){
            localroot.right =delete(localroot.right, item);
            return localroot;
        }
        else {
            if(localroot.left==null)
                return localroot.right;
            else if(localroot.right == null) 
                return localroot.left;
            if(localroot.left.right ==null){
                localroot.data= localroot.left.data;
                localroot.left = localroot.left.left;
                return localroot;
            }
            else{
                localroot.data = findLargestChild(localroot.left);
                return localroot;
            }

        }
    }
    private boolean find(Node<E> root, E key){
        if(root == null)
            return false;
        int compResult = root.data.compareTo(key);
        if(compResult == 0)
            return true;
        else if(compResult > 0)
            return find(root.left, key);
        else return find(root.right, key);
    }
    public boolean find(E key){
        if(key != null)
            return find(root, key);
        
        else return false;
    }

    private void preOrderTraverse(Node<E> root, int level, StringBuilder sb) {
        for(int i = 0; i < level;i++)
            sb.append("  ");
        if(root == null)
            sb.append("null\n");
        else{
            sb.append(root.toString());
            sb.append("\n");
            preOrderTraverse(root.left, level+1, sb);
            preOrderTraverse(root.right, level+1, sb);
        }
	}
    public String toString(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add(4, 19);
        testTree.add(2, 31);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);
        System.out.println(testTree.toString());
        
        Treap<Character> testTree1 = new Treap<Character>();
        testTree1.add('p', 99);
        testTree1.add('g', 80);
        testTree1.add('a', 60);
        testTree1.add('j', 65);
        testTree1.add('u', 75);
        testTree1.add('r', 40);
        testTree1.add('z', 47);
        testTree1.add('w', 32);
        testTree1.add('v' ,21);
        testTree1.add('x');
        System.out.println(testTree1.delete('z'));
        System.out.println(testTree1.toString());
        
    }

}