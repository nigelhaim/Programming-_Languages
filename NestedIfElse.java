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
            int LineCount = 1;
            String inp;
            //Counts the brackets if successfuly opened and closed 
            while ((inp=br.readLine())!=null) {
                if(inp.contains("{")){
                    curlyCount++;
                }
                if(inp.contains("}")){
                    curlyCount--;
                }

                if(inp.contains("(")){
                    pharCount++;
                }
                if(inp.contains(")")){
                    pharCount--;
                }
                if(inp.toUpperCase().contains("IF")){
                    ifCount++;
                }else if(inp.toUpperCase().contains("ELSE")){
                    elseCount++;
                }
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
        System.out.println();
        System.out.println("===============");
        System.out.println();
        for(Map.Entry<Integer, String> get :token.entrySet()){
            if(get.getKey() > 9){
                System.out.println(get.getKey() + " | " + get.getValue());
            }else{
                System.out.println(get.getKey() + "  | " + get.getValue());
            }
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Count");
        System.out.println("Number of if statements: " + ifCount);
        System.out.println("Number of else statements: " + elseCount);
        System.out.println("===============");
        System.out.println();
        int brackets = correctBrackets(token);
        //It is a flag system where it detects errors of the code and prints which error it has detected 
        if(brackets == 1){
            ArrayList<Integer> flag_lineNum = scanIF(token, false);
            String[] error = {"Case sensitvity for 'if'", "Case sensitivity for 'else'", 
            "Condition operator error", "Empty condition", "Incorrect use of logical operator", 
            "Missing condition", "Missing bracket", "Case sensitivity for 'else if' statement", 
            "Missing if statement"};
            if(flag_lineNum.get(0) == 0){
                int temp = 0;
                int line = 0;
                for (int i = 1; i < flag_lineNum.size(); i++) {
                    if(i % 2 == 1){
                        temp = flag_lineNum.get(i);
                    }
                    else{
                        line = flag_lineNum.get(i);                        
                        System.out.println(error[temp] + " in line " + line);
                    }
                
                }
            }
            else {
                System.out.println("Syntax is correct.");
            }
        }else{
            System.out.println("Bracket Error.");
        }
        
    }

    static ArrayList<Integer> syntaxCheck(HashMap<Integer, String> ifBlock){
        ArrayList<Integer> lineNum = new ArrayList<>();
        ArrayList<Integer> flag_lineNum = new ArrayList<>();
        int flag = 1;

        for(Map.Entry<Integer, String> met: ifBlock.entrySet()){
            String s = met.getValue();
            int line = met.getKey();
            String temp = s.toUpperCase();
            //Checks for the conditions 
            if(temp.contains("{") && !((temp.contains("IF(") || temp.contains("ELSE{") || temp.contains("ELSE IF(")) || (temp.contains("IF (") || temp.contains("ELSE {") || temp.contains("ELSE IF (")))){
                System.out.print("Invalid keyword on line " + line);
                System.exit(0);
            }
            String[] comparisonOperator = {"<=", "==", "!=", ">=", "<", ">"};
            if(temp.contains("IF") && temp.contains("ELSE")){
                String charI = s.contains("i") ? "i" : "I";
                String else_if = "";
                try {
                    else_if = s.substring(s.indexOf("e"), s.indexOf(charI));
                } catch (Exception e) {
                    else_if = s.substring(s.indexOf("E"), s.indexOf(charI));
                }
                if(s.contains("if") && s.contains("else") && else_if.contains(" ")){
                    try {
                        if(s.contains("}")){
                            flag = 1;
                        }
                        else if (ifBlock.get(line - 1).contains("}")){
                            flag = 1;
                        }
                        else{
                            flag = 0;
                            lineNum.add(8);
                            lineNum.add(line);
                            flag_lineNum.add(0, flag);
                            flag_lineNum.addAll(lineNum);
                            return flag_lineNum;
                        }
                    } catch (Exception e) {
                        flag = 0;
                        lineNum.add(8);
                        lineNum.add(line);
                        flag_lineNum.add(0, flag);
                        flag_lineNum.addAll(lineNum);
                        return flag_lineNum;
                    }
                }
                else{
                    flag = 0;
                    lineNum.add(7);
                    lineNum.add(line);
                    flag_lineNum.add(0, flag);
                    flag_lineNum.addAll(lineNum);
                    return flag_lineNum;
                }
            }
            if(temp.contains("IF")){
                if(s.contains("if")){
                    flag = 1;
                }
                else{
                    flag = 0;
                    lineNum.add(0);
                    lineNum.add(line);
                    flag_lineNum.add(0, flag);
                    flag_lineNum.addAll(lineNum);
                    return flag_lineNum;
                }
                String condition = "";
                try {
                    condition = s.substring(s.indexOf('('), s.lastIndexOf(')') + 1);
                } catch (IndexOutOfBoundsException e) {
                    flag = 0;
                    lineNum.add(5);
                    lineNum.add(line);
                    flag_lineNum.add(0, flag);
                    flag_lineNum.addAll(lineNum);
                    return flag_lineNum;
                }
                String condition_with_space = condition;
                condition = condition.replaceAll(" ", "");
                if(condition.indexOf(')') - condition.indexOf('(') == 1){
                    flag = 0;
                    lineNum.add(3);
                    lineNum.add(line);
                    flag_lineNum.add(0, flag);
                    flag_lineNum.addAll(lineNum);
                    return flag_lineNum;
                }
                ArrayList<String> conditions = new ArrayList<>();
                if(condition.contains("&") || condition.contains("|")){
                    char bef = ' ';
                    char aft = ' ';
                    try {
                        bef = condition_with_space.charAt(condition_with_space.indexOf("|") - 1);
                        aft = condition_with_space.charAt(condition_with_space.indexOf("|") + 2);
                    } catch (Exception e) {
                        bef = condition_with_space.charAt(condition_with_space.indexOf("&") - 1);
                        aft = condition_with_space.charAt(condition_with_space.indexOf("&") + 2);
                    }
                    boolean isValid = bef == ' ' && aft == ' ' ? true : false;
                    if(condition.contains("&&") && isValid || condition.contains("||") && isValid){
                        if(condition.contains("&&")){
                            String[] seperateConditions = condition.split("&&");
                            for(String sep: seperateConditions){
                                conditions.add(sep);
                            }
                        }
                        else{
                            String[] seperateConditions = condition.split("\\|\\|");
                            for(String sep: seperateConditions){
                                conditions.add(sep);
                            }
                        }
                    }
                    else{
                        flag = 0;
                        lineNum.add(4);
                        lineNum.add(line);
                        flag_lineNum.add(0, flag);
                        flag_lineNum.addAll(lineNum);
                        return flag_lineNum;
                    }
                }
                if(conditions.size() < 1){
                    for(int i = 0; i < comparisonOperator.length; i++){
                        if(condition.contains(comparisonOperator[i])){
                            if(s.contains(" " + comparisonOperator[i] + " ")){
                                flag = 1;
                                break;
                            }
                            else{
                                System.out.print("Invalid operator on line " + line);
                                System.exit(0);
                            }
                        }
                        else{
                            flag = 0;
                            if(i == comparisonOperator.length - 1){
                                lineNum.add(2);
                                lineNum.add(line);
                                flag_lineNum.add(0, flag);
                                flag_lineNum.addAll(lineNum);
                                return flag_lineNum;
                            }
                        }
                    }
                }
                else{
                    for(String cond: conditions){
                        for(int i = 0; i < comparisonOperator.length; i++){
                            if(cond.contains(comparisonOperator[i])){
                                flag = 1;
                                break;
                            }
                            else{
                                flag = 0;
                                if(i == comparisonOperator.length - 1){
                                    lineNum.add(2);
                                    lineNum.add(line);
                                    flag_lineNum.add(0, flag);
                                    flag_lineNum.addAll(lineNum);
                                    return flag_lineNum;
                                }
                            }
                        }
                    }
                }
            }
            if(temp.contains("ELSE")){
                if(s.contains("else")){
                    try {
                        flag = 1;
                        if(s.contains("}")){
                            flag = 1;
                        }
                        else if (ifBlock.get(line - 1).contains("}")){
                            flag = 1;
                        }
                        else{
                            flag = 0;
                            lineNum.add(8);
                            lineNum.add(line);
                            flag_lineNum.add(0, flag);
                            flag_lineNum.addAll(lineNum);
                            return flag_lineNum;
                        }
                    } catch (Exception e) {
                        flag = 0;
                        lineNum.add(10);
                        lineNum.add(line);
                        flag_lineNum.add(0, flag);
                        flag_lineNum.addAll(lineNum);
                        return flag_lineNum;
                    }
                }
                else{
                    flag = 0;
                    lineNum.add(1);
                    lineNum.add(line);
                    flag_lineNum.add(0, flag);
                    flag_lineNum.addAll(lineNum);
                    return flag_lineNum;
                }
            }
        }
        flag_lineNum.add(0, flag);
        flag_lineNum.addAll(lineNum);
        return flag_lineNum;
    }

    public static int correctBrackets(HashMap<Integer, String> token){
        //Recounts the brackets 
        int flag = 1;
        int curlyCount = 0;
        for(Map.Entry<Integer, String> set: token.entrySet()){
            String inp = set.getValue();

            if(inp.contains("}")){
                curlyCount--;
                int close = inp.indexOf("}");
                while (true) {                                                                                                                                                                                                                                            
                    inp = inp.substring(close + 1);
                    if(inp.contains("}")){
                        curlyCount--;
                        close = inp.indexOf("}");
                    }
                    else
                        break;
                }
            }
            if(inp.contains("{")){
                curlyCount++;
                int open = inp.indexOf("{");
                while(true){
                    inp = inp.substring(open + 1);
                    if(inp.contains("{")){
                        curlyCount++;
                        open = inp.indexOf("{");
                    }
                    else
                        break;
                }
            }
        }
        
        if(curlyCount != 0){
            flag = 0;
        }
        return flag;
    }

    static ArrayList<Integer> scanIF(HashMap<Integer, String> token, boolean skipIF){
        HashMap<Integer, String> ifBlock = new HashMap<Integer, String>();
        int curlyCount = 0;
        int pharCount = 0; 
        boolean addBlock = false;
        for(Map.Entry<Integer, String> met: token.entrySet()){
            String s = met.getValue();
            int lineNum = met.getKey();
            String temp = s.toUpperCase();
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
                curlyCount++;
            }
            if(s.contains("}")){
                curlyCount--;
            }

            if(s.contains("(")){
                pharCount++;
            }
            if(s.contains(")")){
                pharCount--;
            }
            
            if(addBlock){
                ifBlock.put(lineNum, s);
            }

            if(curlyCount == 0 && pharCount == 0){
                addBlock = false;
            }  
            if(temp.contains("{") && temp.contains("IF(")){
                char [] chars = temp.toCharArray();
                for(int i = 0; i < chars.length; i++){
                    if(i-1 >= 0 && chars[i] == 'I' && chars[i+1] == 'F' && chars[i+2] == '(' && (chars[i-1] != ' ' || String.valueOf(chars[i-1]).equals(""))){
                        System.out.print("Invalid Keyword on line " + met.getKey());
                        System.exit(0);
                    }
                }
            }
            if(temp.contains("{") && temp.contains("ELSE")){
                char [] chars = temp.toCharArray();
                for(int i = 0; i < chars.length; i++){
                        if((i-1 >= 0 && chars[i] == 'E' && chars[i+1] == 'L' && chars[i+2] == 'S' && chars[i+3] == 'E' && !((String.valueOf(chars[i+4]).equals(" ")) || chars[i+4] == '{'))){
                            System.out.print("Invalid Keyword on line " + met.getKey());
                            System.exit(0);
                        }
                    if(i-1 >= 0 && chars[i] == 'E' && chars[i+1] == 'L' && chars[i+2] == 'S' && chars[i+3] == 'E' && (chars[i-1] != ' ' || String.valueOf(chars[i-1]).equals("")) && chars[i-1] != '}'){
                        System.out.print("Invalid Keyword on line " + met.getKey());
                        System.exit(0);
                    }
                }
            }
            if(temp.contains("{") && !((temp.contains("IF(") || temp.contains("ELSE{") || temp.contains("ELSE IF(")) || (temp.contains("IF (") || temp.contains("ELSE {") || temp.contains("ELSE IF (")))){
                System.out.print("Invalid Keyword on line " + met.getKey());
                System.exit(0);
            }
        }
        skipIF = true;
        if(!ifBlock.isEmpty()){
            scanIF(ifBlock, skipIF);            
        }
        ArrayList<Integer> flag_lineNum = syntaxCheck(ifBlock);
        return flag_lineNum;
    }
}