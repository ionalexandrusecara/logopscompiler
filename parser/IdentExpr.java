package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

public class IdentExpr extends PrimaryExpr {
    
    //Identified expression is stored and next token is retreived
    public static Expr parse() throws IOException {
        IdentExpr identExpr = new IdentExpr();
        Parser.t = Lexer.lex();
        return identExpr;
    }

    public void codegen() throws IOException {
        Parser.printer.println("Arg");
    }
}
