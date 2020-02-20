import java.util.*;
public class PhoneDirectory{
    private static ArrayList<DirectoryEntry> theDirectory;

    public PhoneDirectory(){
        theDirectory = new ArrayList<DirectoryEntry>();
    }

    public void addEntry(String name, String number){
        theDirectory.add(new DirectoryEntry(name, number));
    }

    public DirectoryEntry findEntry(String name){
        int idx = theDirectory.indexOf(new DirectoryEntry(name, ""));
        if(idx ==-1)
            return new DirectoryEntry("", "");
        else return theDirectory.get(idx);
    }
    public static void main(String[] args) {
        theDirectory = new ArrayList<DirectoryEntry>();
        DirectoryEntry entry = new DirectoryEntry("Bob", "0001112222");
        theDirectory.add(entry);
        DirectoryEntry entry1 = new DirectoryEntry("Joe", "1111230947");
        theDirectory.add(entry1);
        System.out.println(theDirectory);
        
    }
}