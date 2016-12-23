/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import logoCompiler.lexer.*;

/**
 *
 * @author user
 */
public abstract class Stmts {

    static ArrayList<ArrayList<Token>> Statements = new ArrayList<ArrayList<Token>>();
    static Stmt stmt;
    public Expr expr;

    public Stmts(Expr expr) {
        this.expr = expr;
    }
    
    public static void addStmt(ArrayList<Token> statement) {
        Statements.add(statement);
    }
    
    public static Stmt getStmt(){
        return stmt;
    }
    
    //Checks the type of the statemnent and returns the respective type
    public static Stmts parse(String str) throws IOException {
        if (Parser.t.getClass().getSuperclass().equals(DirectionalToken.class)) {
            return DirectionalStmt.parse(str);
        } else if (Parser.t instanceof IfToken) {
            return ConditionalStmt.parse(str);
        } else if (Parser.t instanceof IdentToken) {
            return ProcStmt.parse(str);
        } else {
            Parser.t = Lexer.lex();
            System.out.println("Wrong Syntax Found at Line: " + Lexer.getLineNo());
            Parser.foundError();
            Parser.addError();
            return null;
        }

    }

    public abstract void codegen() throws IOException;

}
