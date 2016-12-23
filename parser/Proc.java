package logoCompiler.parser;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import logoCompiler.lexer.*;


/*
 * proc:
 *   "PROC" ident '(' ident ')' stmts 
 */
public class Proc {

    String procName;
    List<Stmts> stmts = new ArrayList<Stmts>();
    static Boolean error = false;

    public Proc(String procName, List<Stmts> stmts) {
        this.procName = procName;
        this.stmts = stmts;
    }
    //Checks the syntax of a procedure and starts processing the procedure's statements
    public static Proc parse() throws IOException {
        List<Stmts> statements = new ArrayList<Stmts>();
        String procName = "";
        String parameterName = "";
        Parser.t = Lexer.lex();
        if (Parser.t instanceof IdentToken) {
            procName = ((IdentToken) Parser.t).getName();
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("Procedure Name is Missing at Line: " + Lexer.getLineNo());
            Parser.addError();
        }
        if (Parser.t instanceof LParenToken) {
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("Left bracket '(' is Missing at Line: " + Lexer.getLineNo());
            Parser.addError();
        }
        if (Parser.t instanceof IdentToken) {
            parameterName = ((IdentToken) Parser.t).getName();
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("Procedure Parameter is Missing at Line: " + Lexer.getLineNo());
            Parser.addError();
        }
        if (Parser.t instanceof RParenToken) {
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("Procedure Name is Missing at Line: " + Lexer.getLineNo());
            Parser.addError();
        }
        while (isStatement()) {
            statements.add(Stmts.parse(parameterName));
        }
        return new Proc(procName, statements);
    }

    private static boolean isStatement() {
        return (Parser.t.getClass().getSuperclass().equals(DirectionalToken.class)
                || Parser.t instanceof IfToken
                || Parser.t instanceof IdentToken);
    }

    public void codegen() throws IOException {
        Parser.printer.println("/" + procName + " {");
        Parser.printer.println("/Arg exch def");
        ListIterator<Stmts> li = stmts.listIterator();
        Stmts stmt;
        while (li.hasNext()) {
            stmt=li.next();
            if (stmt != null) {
                stmt.codegen();
            }
        }
        Parser.printer.println("} def");
    }
    
    public List<Stmts> getStmts() {
        return stmts;
    }

    public String getProcName() {
        return procName;
    }
}
