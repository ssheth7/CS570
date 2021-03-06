
//I pledge my honor that I have abided by the Steven's Honor Code

public class BinaryNumber{

    private int[] data;
    private boolean overflow;

    private boolean validate()throws NullPointerException, ArrayIndexOutOfBoundsException{
        try{int x = data[0] ; return true;}
        catch(NullPointerException e){System.out.println(e.getLocalizedMessage()); return false;}
        catch(ArrayIndexOutOfBoundsException e){System.out.println(e.getLocalizedMessage()); return false;}   
    }
    private boolean validate(String str){
        boolean check = true;
        for(int i = 0; i < str.length();i++){
        if(Character.getNumericValue(str.charAt(i)) !=1 && Character.getNumericValue(str.charAt(i)) != 0)
            check = false;}
        return check;
    }

    public BinaryNumber(int length) {
        if(length>0)
        data = new int[length];
        else System.out.println("Bad length size!");
    }

    public BinaryNumber(String str) {
        if(!validate(str))
            {System.out.println("Not a binary number!"); return;}
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++)
            arr[i] = Character.getNumericValue(str.charAt(i));
        data = arr;
        
    }

    public int getLength() {
        if(validate())
        return data.length;
        else
        System.out.println("Object is empty!"); 
        return -1;     
    }

    public int getDigit(int index) {
        if(!validate())
        return -1;
        if (index > data.length || index < 0) {
            System.out.println("Index is out of bound");
            return 99999;
        }
        return data[index];

    }

    public void shiftR(int amount) {
        if(validate()){
        int[] newarr = new int[amount + data.length];
        for (int i = 0; i < data.length; i++)
            newarr[i + amount] = data[i];
        data = newarr;
        }
        else System.out.println("shift right not executed. Method called on bad object");
    }

    public void add(BinaryNumber aBinaryNumber) {
        if(!validate())
        return;

        if (data.length != aBinaryNumber.getLength()) {
            System.out.println("Binary numbers are not of equal length");
            return;
        }
        boolean localoverflow = false;
        for (int i = data.length - 1; i >= 0; i--) {

            if (localoverflow) {
                data[i]++;
                localoverflow = false;
            }

            data[i] += aBinaryNumber.getDigit(i);
            
            if (data[i] > 1) {
                data[i] %= 2;
                localoverflow = true;
                
            }

        }
        if (localoverflow) {
            overflow = true;
        }

    }

    public String toString() {
        if(!validate())
        return "Empty object";

            if(data.length == 0)
        return "Binary number is not defined";
        if (overflow)
        return "Overflow!";
        String string = "";
        for (int i = 0; i < data.length; i++)
            string += data[i] + " ";
       
        return string;
    }
        
    

    public int toDecimal() {
        
       if(!validate())
       return -1;
        int sum = 0;
        int i;
        for (i = 0; i < data.length; i++) {
            int value = data.length - 1 - i;
            if (data[value] != 0)
                sum +=  Math.pow(2, i);
        }
        if(overflow)
            sum+=Math.pow(2,i);
        return sum;
        
    }

    public void clearOverflow() {
        overflow = false;
        System.out.println("Overflow is set to " + overflow);
    }
}
