package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

/*
 * primary-expr:
 *   num
 *   ident
 */
public abstract class PrimaryExpr extends Expr {

    public static Expr parse(String str) throws IOException {        
        if (Parser.t instanceof NumToken) {
            return NumExpr.parse();
        } else if (Parser.t instanceof IdentToken) {
            if (((IdentToken) Parser.t).getName().equals(str)) {
                return IdentExpr.parse();
            }
        } else {
            Parser.noOfErrors++;
        }
        return null;
    }
    
    private static boolean isStatement() {
        return (Parser.t.getClass().getSuperclass().equals(DirectionalToken.class)
                || Parser.t instanceof IfToken
                || Parser.t instanceof EndIfToken);
    }
}
