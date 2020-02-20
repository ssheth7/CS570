import java.util.*;

public class DirectoryEntry {
    private String name, number;

    public DirectoryEntry(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }
    public void  setName(String name){
        this.name = name;
    }
    public String getNumber(){
        return number;
    }
    public void Number(String number){
        this.number = name;
    }
    @Override
    public boolean equals(Object O){
        DirectoryEntry e = (DirectoryEntry) O;
        if(name.equals(e.getName()))
            return true;
        return false;
    }
    @Override
    public String toString(){
        return name;
    }
    
}
