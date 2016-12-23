package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;

public class ProcStmt extends Stmts {

    private String procName;

    public ProcStmt(String procName, Expr expression) {
        super(expression);
        this.procName = procName;
    }

    public String getProcName() {
        return procName;
    }

    public void setIdentifier(String procName) {
        this.procName = procName;
    }

    //Checks validity of procedure call inside the program and retrieves next token
    public static Stmts parse(String str) throws IOException {
        String procName="";
        Expr expr = null;
        
        if (Parser.t instanceof IdentToken) {
            procName = ((IdentToken) Parser.t).getName();
        }
        Parser.t = Lexer.lex();

        if (Parser.t instanceof LParenToken) {
            Parser.t = Lexer.lex();
            expr = Expr.parse(str);
        } else {
           Parser.printer.println("'(' is missing at Line: " + Lexer.getLineNo());
            Parser.error = true;
            Parser.noOfErrors++;
        }

        if (Parser.t instanceof RParenToken) {
            Parser.t = Lexer.lex();
        } else {
            Parser.printer.println("')' is missing at Line: " + Lexer.getLineNo());
            Parser.error = true;
            Parser.noOfErrors++;
        }
        return new ProcStmt(procName, expr);
    }

    public void codegen() throws IOException {
        Parser.printer.println("Arg\n");
        expr.codegen();
        Parser.printer.println(procName + "\n");
        Parser.printer.println("/Arg exch def\n");
    }
}
