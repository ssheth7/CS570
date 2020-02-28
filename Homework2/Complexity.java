//I pledge my honor that I have abided by the Stevens Honor Code-Shivam Sheth
public class Complexity {

    public static void method1(int n) {// n^2
        System.out.print("n^2 Operation counter for n = " + n + ": ");
        if(n == 0){System.out.print(0);System.out.println();return;}
        else if(n < 0){System.out.print("undefined");System.out.println(); return;}
        for (int i = 1, counter = 1; i <= n; i++)
            for (int j = 1; j <= n; j++, counter++)
                System.out.print(counter + " ");
        System.out.println();
    }

    public static void method2(int n) {// n^3
        System.out.print("n^3 Operation counter for n = " + n + ": ");
        if(n == 0){System.out.print(0);System.out.println();return;}
        else if(n < 0){System.out.print("undefined");System.out.println();return;}
        for (int i = 1, counter = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                for (int k = 0; k < n; k++, counter++)
                    System.out.print(counter + " ");
        System.out.println();
    }

    public static void method3(int n) {// logn
        System.out.print("logn Operation counter for n = " + n + ": ");
        if (n <= 0)
            System.out.print("undefined!");
        else
            for (int i = 1, counter = 1; i < n; i *= 2, counter++)
                System.out.print(counter + " ");
        System.out.println();
    }

    public static void method4(int n) {// nlogn
        System.out.print("nlogn Operation counter for n = " + n + ": ");
        if (n <= 0)
            System.out.print("undefined!");
        else
            for (int i = 1, counter = 1; i <= n; i++)
                for (int k = 1; k < n; k *= 2, counter++)
                    System.out.print(counter + " ");
        System.out.println();
    }

    public static void method5(int n) {// loglogn
        System.out.print("loglogn Operation counter for n = " + n + ": ");
        if(n <= 0)
        System.out.print("undefined!");
        else
        for (int i = 2, counter = 1; i < n; i *= i, counter++) {
            System.out.print(counter + " ");
        }
        System.out.println();
    }
    
    private static int counter = 1;
    public static int method6(int n) {// 2^n
        
        if(n < 0){System.out.println("undefined!");return 0;}
        else if (n == 0) {return 0;}
        
        counter++;
        
        return method6(n - 1) + method6(n - 1);
    }
}