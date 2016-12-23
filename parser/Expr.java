package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

/*
 * expr:
 *   primary-expr 
 *   binary-expr 
 */
public abstract class Expr {

    public static Expr parse(String str) throws IOException {
        return fraserHanson(1, str); //name of Expression (Parameter or Variable)
    }

    //Binary Expressions precedence handler from Fraser and Hanson C Compiler book
    private static Expr fraserHanson(int k, String str) throws IOException {
        int i;
        Expr left;
        OperatorToken oper;
        Expr right;
        left = PrimaryExpr.parse(str);
        for (i = Parser.t.precedence(); i >= k; i--) {
            while (Parser.t.precedence() == i) {
                oper = (OperatorToken) Parser.t;
                Parser.t = Lexer.lex();
                right = fraserHanson(i + 1, str);
                left = new BinaryExpr(left, oper, right);

            }
        }
        return left;

    }

    public abstract void codegen() throws IOException;
}
