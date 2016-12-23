package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

public class NumExpr extends PrimaryExpr{
    
    private int num;
    
    public NumExpr(int num){
        this.num=num;
    }
    
    //Numerical expression is stored and the next token is retrieved
    public static Expr parse() throws IOException{
        NumExpr numExpr=new NumExpr(((NumToken) Parser.t).getNum());
        Parser.t=Lexer.lex();
        return numExpr;
    }
    
    public void codegen() {
        Parser.printer.println(num);
    }
    
}
