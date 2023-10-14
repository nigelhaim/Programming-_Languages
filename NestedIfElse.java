import java.util.*;
import java.io.*;
public class NestedIfElse {
    /*
     * This is a program that checks the syntax of the nested if else 
     * statement. 
     */
    public static void main(String[] args) {
        System.out.print("==============\nBeginTest\n==============");
        System.out.println();
        //List<String> tokens = new ArrayList<>();
        HashMap<Integer, String> token = new HashMap<Integer, String>();
        int ifCount = 0;
        int elseCount = 0;
        try{
            File file=new File("./InputMedium.txt");    //creates a new file instance  
            FileReader fr=new FileReader(file);   //reads the file  
            BufferedReader br=new BufferedReader(fr);  
            System.out.println("Importing code");
            int curlyCount = 0;
            int pharCount = 0; 
            int LineCount = 0;
            String inp;
            while ((inp=br.readLine())!=null) {
                //System.out.println("Inserting " + inp);
                if(inp.contains("{")){
                    //System.out.println("curlyCount++");
                    curlyCount++;
                }
                if(inp.contains("}")){
                    //System.out.println("curlyCount--");
                    curlyCount--;
                }

                if(inp.contains("(")){
                    //System.out.println("PharCount++");
                    pharCount++;
                }
                if(inp.contains(")")){
                    //System.out.println("PharCount--");
                    pharCount--;
                }
                if(inp.toUpperCase().contains("IF")){
                    ifCount++;
                }else if(inp.toUpperCase().contains("ELSE")){
                    elseCount++;
                }
                //System.out.println("Curly braces count: " + curlyCount + "\nPharanthesis Count: " + pharCount); 
                //tokens.add(inp);
                if(!inp.equals("")){
                    token.put(LineCount,inp);
                    LineCount++;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
        System.out.println("===============");
        System.out.println("\nPrinting tokens");
        //System.out.println("Token list: " + tokens);
        //System.out.println("Token list: " + tokens);
        System.out.println();
        System.out.println("===============");
        System.out.println("Count");
        System.out.println("Number of if statements: " + ifCount);
        System.out.println("Number of else statements: " + elseCount);
        System.out.println("===============");
        System.out.println();
        //int lineNumber = 0;
        // for(String t : tokens){
        //     System.out.println(lineNumber + " | " + t);
        //     lineNumber++;
        // }
        
        scanIF(token, false);
    }
    static void scanIF(HashMap<Integer, String> token, boolean skipIF){
        HashMap<Integer, String> ifBlock = new HashMap<Integer, String>();
        int curlyCount = 0;
        int pharCount = 0; 
        boolean addBlock = false;
        for(Map.Entry<Integer, String> met: token.entrySet()){
            String s = met.getValue();
            int lineNum = met.getKey();
            // System.out.println(met.getKey() + " || " + met.getValue());  
            if(skipIF == false){
                if(s.toUpperCase().contains("IF")){
                    addBlock = true;
                }  
            }
            else{
                skipIF = false;
                curlyCount--;
            }
            if(s.contains("{")){
                //System.out.println("curlyCount++");
                curlyCount++;
            }
            if(s.contains("}")){
                //System.out.println("curlyCount--");
                curlyCount--;
            }

            if(s.contains("(")){
                //System.out.println("PharCount++");
                pharCount++;
            }
            if(s.contains(")")){
                //System.out.println("PharCount--");
                pharCount--;
            }
            
            if(addBlock){
                ifBlock.put(lineNum, s);
            }

            if(curlyCount == 0 && pharCount == 0){
                addBlock = false;
            }  
        }
        System.out.println();
        for(Map.Entry<Integer, String> get :ifBlock.entrySet()){
            System.out.println(get.getKey() + " // " + get.getValue());
        }
        System.out.println("-------------------------------------------------------");
        skipIF = true;
        if(!ifBlock.isEmpty()){
            scanIF(ifBlock, skipIF);
        }
        return;
    }
}


