public class IDLListTest{
    
    public static void main(String[] args) {
        IDLList<Integer> test = new IDLList<Integer>();
        //Add tests
        test.add(0);
        test.add(1, 1);
        test.getLast();
        //Remove Tests
        test.remove();
        test.remove(1);
        test.removeAt(1);
        test.removeLast();
        System.out.println("Size: " + test.size() + " " +test.toString());//Node 0: test0 Node 2: test1 Node2: test2
        System.out.println(test.get(0)+ test.get(1) + test.get(2) + test.get(3));
        
        

    }
}