
package logoCompiler.parser;

import java.util.ArrayList;
import logoCompiler.lexer.*;

public class Stmt
{
    static ArrayList<Token> statementTokens=new ArrayList();
    
    public Stmt(ArrayList<Token> statementTokens){
        this.statementTokens=statementTokens;
    }
    
    public static void addToken(Token t){
        statementTokens.add(t);
    }
}

