import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    final Integer[] primes;
    Map<Character, Integer> letterTable;
    Map<Long,ArrayList<String>> anagramTable;
    public Anagrams(){
        primes = new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 
                               47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }
    private void buildLetterTable(){
        letterTable = new HashMap<Character, Integer>();
        letterTable.put('a', 2);
        letterTable.put('b', 3);
        letterTable.put('c', 5);
        letterTable.put('d', 7);
        letterTable.put('e', 11);
        letterTable.put('f', 13);
        letterTable.put('g', 17);
        letterTable.put('h', 19);
        letterTable.put('i', 23);
        letterTable.put('j', 29);
        letterTable.put('k', 31);
        letterTable.put('l', 37);
        letterTable.put('m', 41);
        letterTable.put('n', 43);
        letterTable.put('o', 47);
        letterTable.put('p', 53);
        letterTable.put('q', 59);
        letterTable.put('r', 61);
        letterTable.put('s', 67);
        letterTable.put('t', 71);
        letterTable.put('u', 73);
        letterTable.put('v', 79);
        letterTable.put('w', 83);
        letterTable.put('x', 89);
        letterTable.put('y', 97);
        letterTable.put('z', 101);
    }
    private void addWord(String s){
        if(s == null) return;
        long hash = myHashCode(s);
        
        if(anagramTable.get(hash) == null){
            ArrayList<String> str = new ArrayList<>();
            str.add(s);
            anagramTable.put(hash, str);
        }
        else anagramTable.get(hash).add(s);
    }
    private Long myHashCode(String s){
        long code = 1;
        for(int i = 0; i < s.length();i++){
            code *= letterTable.get(s.charAt(i));
        }
        return code;
    }
    private void processFile(String s) throws IOException{
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)
            this.addWord(strLine);
        br.close();
    }
    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
        ArrayList<Map.Entry<Long, ArrayList<String>>> largest = 
        new ArrayList<Map.Entry<Long, ArrayList<String>>>();
        int length = 0;
        for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet())
            if(entry.getValue().size() > length){
                largest.clear();
                length = entry.getValue().size();
                largest.add(entry);
            }
            else if(entry.getValue().size() == length)
                largest.add(entry);
        return largest;
    }
    public static void main(String[] args) {
        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();
        try{
            a.processFile("words_alpha.txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time: " + seconds);
        System.out.println("Key of max anagrams: " + maxEntries.get(0).getKey());
        System.out.println("List of max anagrams: "+ maxEntries);
        System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());
    }
}