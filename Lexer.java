
//Shelby Antill
//893039107

package lexer;

import java.io.*;
import java.util.*;

public class Lexer {

    
    public static void Tokenize(String fileName) throws IOException{
        
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        ArrayList<String> tokens = new ArrayList<>();
        String tempStr="";
        
        while(scan.hasNextLine()){
            String lineStr = scan.nextLine();
            
            for(int i = 0; i<lineStr.length();i++){
                
                char c = lineStr.charAt(i);
                
                if(Character.isLetterOrDigit(c)){
                    tempStr+=c;

                    switch(tempStr){
                        case "if":
                            tokens.add("IF");
                            tempStr="";
                            break;
                        case "for":
                            tokens.add("FOR");
                            tempStr="";
                            break;
                        case "while":
                            tokens.add("WHILE");
                            tempStr="";
                            break;
                        case "procedure":
                            tokens.add("PROC");
                            tempStr="";
                            break;
                        case "return":
                            tokens.add("RETURN");
                            tempStr="";
                            break;
                        case "int":
                            tokens.add("INT");
                            tempStr="";
                            break;
                        case "else":
                            tokens.add("ELSE");
                            tempStr="";
                            break;
                        case "do":
                            tokens.add("DO");
                            tempStr="";
                            break;
                        case "break":
                            tokens.add("BREAK");
                            tempStr="";
                            break;
                        case "end":
                            tokens.add("END");
                            tempStr="";
                            break;

                    }
                    
                    if(!tempStr.isEmpty()&& i == lineStr.length()-1){
                        String varStatus= validityChecker(tempStr, tokens);
                        if(varStatus.equals("NOTGOOD")){
                            tempStr="";
                            break;
                        }
                        else{
                            tempStr="";
                        }
                        
                    }
                }
                else if(c == ' '){
                    if(!tempStr.isEmpty()){
                        String varStatus= validityChecker(tempStr, tokens);
                        if(varStatus.equals("NOTGOOD")){
                            tempStr="";
                            break;
                        }
                        else{
                            tempStr="";
                        }
                        
                    }
                }
                
                else{
                    
                    
                    if(!tempStr.isEmpty()){
                        String varStatus= validityChecker(tempStr, tokens);
                        if(varStatus.equals("NOTGOOD")){
                            tempStr="";
                            break;
                        }
                        else{
                            tempStr="";
                        }
                        
                    }
                    
                   
                    switch(c){
                        case '<':
                            if(lineStr.charAt(i+1)=='='){
                                i++;
                                tokens.add("LE");
                            }
                            else{
                                tokens.add("LT");
                            }

                            break;

                        case '>':
                            if(lineStr.charAt(i+1)=='='){
                                i++;
                                tokens.add("GE");
                            }
                            else{
                                tokens.add("GT");
                            }
                            break;
                        case '=':
                            if(lineStr.charAt(i+1)=='='){
                                i++;
                                tokens.add("EE");
                            }else{
                                tokens.add("ASSIGN");
                            }

                            break;
                        case '+':
                            if(lineStr.charAt(i+1)=='+'){
                                i++;
                                tokens.add("INC");
                            }
                            else{
                                tokens.add("ADD_OP");
                            }
                            
                            break;
                        case '-':
                            tokens.add("SUB_OP");
                            break;
                        case '*':
                            tokens.add("MUL_OP");
                            break;
                        case '/':

                            tokens.add("DIV_OP");
                            break;
                        case '%':

                            tokens.add("MOD_OP");
                            break;

                        case '(':

                            tokens.add("LP");
                            break;
                        case ')':

                            tokens.add("RP");
                            break;
                        case ';':

                            tokens.add("SEMI");
                            break;
                        case '{':

                            tokens.add("LB");
                            break;
                        case '}':

                            tokens.add("RB");
                            break;
                        case '|':

                            tokens.add("OR");
                            break;
                        case '&':

                            tokens.add("AND");
                            break;
                        case '!':
                            tokens.add("NEG");
                            break;
                        case ',':
                            tokens.add("COMMA");
                            break;
                        
                        }
                  
                }
               
            }
        }
        
        for(int i = 0; i< tokens.size();i++){
            System.out.println(tokens.get(i));
        }
        
        
        }
    
    public static String validityChecker(String str, ArrayList<String> tokens){
        if(str.matches("\\d+")){ 
            tokens.add("INT_CONST");
            return "GOOD";
         }
        
        else if (str.matches("^[a-zA-Z][a-zA-Z0-9]*$")){
            tokens.add("IDENT") ;
            return "GOOD";
        }
        else {
            tokens.add("SYNTAX ERROR: INVALID IDENTIFIER NAME");
            return "NOTGOOD";
        }
        
    }
            

}
    
    
           
    

