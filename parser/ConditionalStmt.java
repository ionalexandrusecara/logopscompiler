/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import logoCompiler.lexer.Token;
import logoCompiler.lexer.*;

/**
 *
 * @author user
 */
public class ConditionalStmt extends Stmts {

    List<Stmts> trueStmts;
    List<Stmts> falseStmts;

    public List<Stmts> getFalseStmts() {
        return falseStmts;
    }

    public List<Stmts> getTrueStmts() {
        return trueStmts;
    }

    public ConditionalStmt(Expr expression, List<Stmts> trueStmts, List<Stmts> falseStmts) {
        super(expression);
        this.trueStmts = trueStmts;
        this.falseStmts = falseStmts;
    }
    
    //Checks for the syntax of a conditional (if) statement and calculates the expression
    //Assigns statements that will be executed when the expression is false or true
    
    public static Stmts parse(String str) throws IOException {
        List<Stmts> trueStmts = new ArrayList<Stmts>(); //when expression is true
        List<Stmts> falseStmts = new ArrayList<Stmts>(); //when expression is false
        Expr expr;
        Parser.t = Lexer.lex();
        expr = Expr.parse(str);
        if (Parser.t instanceof ThenToken) {
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("Then Not Found at Line: " + Lexer.getLineNo()); //Prints error and its line number
            Parser.addError();
        }
        do {
            trueStmts.add(Stmts.parse(str));
        } while (isStatement());

        if (Parser.t instanceof ElseToken) {
            Parser.t = Lexer.lex();
            do {
                falseStmts.add(Stmts.parse(str));
            } while (isStatement());
        } else {
            Parser.foundError();
            Parser.printer.println("Else Not Found at Line: " + Lexer.getLineNo());
            Parser.addError();
        }

        if (Parser.t instanceof EndIfToken) {
            Parser.t = Lexer.lex();
        } else {
            Parser.foundError();
            Parser.printer.println("EndIf Not Found at Line: " + Lexer.getLineNo());
            Parser.addError();
        }
        return new ConditionalStmt(expr, trueStmts, falseStmts);
    }

    private static boolean isStatement() {
        return (Parser.t.getClass().getSuperclass().equals(DirectionalToken.class)
                || Parser.t instanceof IfToken
                || Parser.t instanceof IdentToken);
    }

    public void codegen() throws IOException {
        Stmts stmt;
        //Processes IF expression
        expr.codegen();
        //True Statements
        Parser.printer.println("{");
        ListIterator<Stmts> li = trueStmts.listIterator();
        while (li.hasNext()) {
            stmt = li.next();
            if (stmt != null) {
                stmt.codegen();
            }
        }
        //End of True Statements
        Parser.printer.println("}");

        //False Statements
        Parser.printer.println("{");
        li = falseStmts.listIterator();
        while (li.hasNext()) {
            stmt = li.next();
            if (stmt != null) {
                stmt.codegen();
            }
        }
        //End of False Statements
        Parser.printer.println("} ifelse");
    }
}
