package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

public final class RightStmt{

    Expr expr;

    public RightStmt(Expr expr) {
        this.expr = expr;
    }

    public static RightStmt parse(String str) throws IOException {

        Parser.t = Lexer.lex();
        Expr expr = Expr.parse(str);
        return new RightStmt(expr);
    }

    public void codegen() throws IOException {
        expr.codegen();
        System.out.println("Right");
    }
}
