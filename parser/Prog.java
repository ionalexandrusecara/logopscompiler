package logoCompiler.parser;

import java.io.IOException;
import logoCompiler.lexer.*;
import java.util.*;

public class Prog {

    List<Proc> procs;

    public Prog(List<Proc> procs) {
        this.procs = procs;
    }
    
    //Gets a list of all the procedures in the program as it parses through
    public static Prog parse() throws IOException {
        List<Proc> procs = new ArrayList<Proc>();
        while (Parser.t instanceof PROCToken) {
            procs.add(Proc.parse());
        }
        if (Parser.t instanceof EOIToken) {
            Parser.t = Lexer.lex();
        }
        
        //checks procedure calls
        for (int i=0; i<procs.size();i++) {
            if (ProcCall.check(procs.get(i).getStmts(), procs, procs.get(i)) && !(procs.get(i).procName.equals("MAIN"))) {
                Parser.foundError();
                Parser.addError();
                Parser.printer.println("Procedure Call Failed at Line: " + Lexer.getLineNo());
            }
        }
        return new Prog(procs);
    }

    public void codegen() throws IOException {
        ListIterator<Proc> li = procs.listIterator();
        while (li.hasNext()) {
            li.next().codegen();
        }
    }
}
