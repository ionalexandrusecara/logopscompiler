package logoCompiler.lexer;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import logoCompiler.parser.Parser;
import logoCompiler.parser.Stmts;

public final class Lexer {

    static BufferedReader reader;
    public static int ch = ' ';
    static int lineNo = 1;

    public Lexer() {
    }
    
    public static int getLineNo(){
        return lineNo;
    }

    public static void initReader() {
        if (reader == null) {
            try {
                reader = new BufferedReader(new FileReader("triangle.t"));
            } catch (FileNotFoundException e) {
                Parser.error = true;
                Parser.printer.println(e.getMessage());
            }
        }
    }
    //Read next character
    public static Token lex() throws IOException {
        String tokenWord = "";
        do {
            ch = getChar();
            if(ch=='\n'){
                lineNo++;
            }
        } while (ch == ' ' || ch == '\n' || ch == '\t' || ch == 0);

        //Identify next token and retrieve it
        switch (ch) {
            case -1: {
                return new EOIToken();
            }
            case '+': {
                return new PlusToken();
            }
            case '-': {
                return new MinusToken();
            }
            case '*': {
                return new MultiplyToken();
            }
            case '/': {
                return new DivideToken();
            }
            case '>': {
                return new BiggerToken();
            }
            case '<': {
                return new SmallerToken();
            }
            case '(': {
                return new LParenToken();
            }
            case ')': {
                return new RParenToken();
            }
            default: { //case when the token consists of more than one character
                char tokenCharacter = ((char) ch); //saves each character
                do {
                    tokenWord = tokenWord + Character.toString(tokenCharacter); //used for storing more characters into a string
                    ch = getChar();
                    tokenCharacter = ((char) ch);
                } while (!(tokenCharacter == ' ' || tokenCharacter == '\n' || tokenCharacter == '\t' || tokenCharacter == -1 || ch == 13));
                if (tokenWord.equals("==")) {
                    return new EqualEqualToken();
                } else if (tokenWord.equals(">=")) {
                    return new BiggerEqualToken();
                } else if (tokenWord.equals("<=")) {
                    return new SmallerEqualToken();
                } else if (tokenWord.equals("!=")) {
                    return new NotEqualToken();
                } else if (tokenWord.equals("IF")) {
                    return new IfToken();
                } else if (tokenWord.trim().equals("THEN")) {
                    return new ThenToken();
                } else if (tokenWord.equalsIgnoreCase("ELSE")) {
                    return new ElseToken();
                } else if (tokenWord.equals("PROC")) {
                    return new PROCToken();
                } else if (tokenWord.equals("ENDIF")) {
                    return new EndIfToken();
                } else if (tokenWord.equals("LEFT")) {
                    return new LToken();
                } else if (tokenWord.equals("RIGHT")) {
                    return new RToken();
                } else if (tokenWord.equals("FORWARD")) {
                    return new ForwardToken();
                } else if (tokenWord.matches("[1-9][0-9]*") || tokenWord.equals("0")) {
                    int number = Integer.parseInt(tokenWord);
                    return new NumToken(number);
                } else if (tokenWord.matches("\\p{Alpha}[\\d\\p{Alpha}]*") || tokenWord.equals("0")) {
                    return new IdentToken(tokenWord);
                } else {
                    return lex();
                }
            }
        }
    }

//Reads character by character from the file
    public static int getChar() {
        int ch;
        try {
            ch = reader.read();
            return ch;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
            return -1;
        }
    }
}
