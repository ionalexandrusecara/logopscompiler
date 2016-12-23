package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

/*
 *   "LEFT" expr
 */
public final class LeftStmt {

    Expr expr;

    public LeftStmt(Expr expr) {
        this.expr = expr;
    }
    
    //Expression is parsed and next token is retrieved
    public static LeftStmt parse(String str) throws IOException {
        Parser.t = Lexer.lex();
        Expr expr = Expr.parse(str);
        return new LeftStmt(expr);
    }

    public void codegen() throws IOException {
        expr.codegen();
        System.out.println("Left");
    }
}
