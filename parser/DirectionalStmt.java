/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

/**
 *
 * @author user
 */
public class DirectionalStmt extends Stmts {

    private String directionOrder;

    public DirectionalStmt(Expr expr, String directionOrder) {
        super(expr);
        this.directionOrder = directionOrder;
    }

    public static Stmts parse(String str) throws IOException {
        String directionOrder = ((DirectionalToken) Parser.t).getPostscriptCode();
        Parser.t = Lexer.lex();
        Expr expr = Expr.parse(str);
        return new DirectionalStmt(expr, directionOrder);
    }

    public void codegen() throws IOException {
        expr.codegen();
        Parser.printer.println(directionOrder);
    }

}
