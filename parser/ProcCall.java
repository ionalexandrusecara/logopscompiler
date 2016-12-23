/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.parser;

import java.util.ArrayList;
import java.util.List;
import logoCompiler.lexer.*;

/**
 *
 * @author user
 */
public class ProcCall extends Proc {

    ArrayList<logoCompiler.parser.Stmts> stmts;

    public ProcCall(String procName, String parameterName, ArrayList<logoCompiler.parser.Stmts> stmts) {
        super(procName, stmts);
        this.stmts = stmts;
    }
    //Checks whether a procedure has been defined before being used
    public static boolean testProcStmt(ProcStmt stmt,
        List<Proc> procs, Proc procMain) {
        boolean procNameFound = false;
        //in the list of procs (headers) defined in the program, the method checks to see wether the procedure called has the same name
        for (Proc procTest : procs) {
            if (((ProcStmt) stmt).getProcName().equals(procTest.getProcName())) {
                return true;
            }
        }
        if (!procNameFound) {
            Parser.printer.println("Procedure not defined at Line: " + Lexer.getLineNo());
            return false;
        }
        return true;
    }

    //Checks also for validity of procedure
    public static boolean check(List<Stmts> stmts, List<Proc> procs,
            Proc procMain) {
        for (Stmts stmt : stmts) {
            if (stmt instanceof ProcStmt) {
                if (!testProcStmt((ProcStmt) stmt, procs, procMain)) {
                    return false;
                }
            } else if (stmt instanceof ConditionalStmt) {
                if (!check(((ConditionalStmt) stmt).getFalseStmts(), procs, procMain)) {
                    return false;
                }
                if (!check(((ConditionalStmt) stmt).getTrueStmts(), procs, procMain)) {
                    return false;
                }
            } else if (stmt instanceof DirectionalStmt) {
                return false;
            }
        }
        return true;
    }
}
