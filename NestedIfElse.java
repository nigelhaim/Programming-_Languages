import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class NestedIfElse {
    /*
     * This is a program that checks the syntax of the nested if else 
     * statement. 
     * TODO: Input a multi line code and print
     */
    public static void main(String[] args) {
        System.out.print("==============\nBeginTest\n==============");
        System.out.println();
        List<String> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your code:");
        while (scanner.hasNext()) {
            tokens.add(scanner.next());
        }
        System.out.println("===============");
        System.out.println(tokens);
        scanner.close();
        System.out.println(tokens);
    }

    public void checkif(String s){
    }
}

/*
 Test input 
    int x = 1; 
    if(x+1 == 2){
     System.out.print("True");
     if(x+2 == 3){
         System.out.print("True");
     }else{
         System.out.print("False");
     }
    }else{
     System.out.print("False");
    }

 */

 
