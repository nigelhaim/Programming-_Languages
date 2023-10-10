import java.util.*;


public class Test_IfElse {
    /*
     * This is just a test for the test input on the Main program NestedIfElse.java
     */

    public void check_if_else(HashMap<Integer, String> token){
        for(Map.Entry<Integer, String> set: token.entrySet()){
            System.out.println(set.getKey() + " || " + set.getValue());
            
        }
    }

    public static void main(String[] args) {
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
    }
}
