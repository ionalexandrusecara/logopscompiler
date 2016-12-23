package logoCompiler;

import java.io.IOException;
import logoCompiler.lexer.*;
import logoCompiler.parser.*;

public class LogoPSCompiler {

    public static void main(String[] args) throws IOException {
        Lexer.initReader();
        Parser.enablePrinter();
        Parser.t = Lexer.lex();
        Prog prog = Prog.parse();
        
        if (Parser.error == false) {
            printPS(prog);
        } else {
            Parser.printer.println(Parser.getNoOfErrors() + " Error(s) Found");
        }
        Parser.printer.close();
    }

    public static void printPS(Prog prog) throws IOException {
        psPrologue();
        prog.codegen();
        psEpilogue();
    }

    public static void psPrologue() {
        Parser.printer.println("%!PS-Adobe-3.0");	// Adobe header
        /* rest of prologue ... */
        Parser.printer.println("/Xpos    { 300 } def");
        Parser.printer.println("/Ypos    { 500 } def");
        Parser.printer.println("/Heading { 0   } def");
        Parser.printer.println("/Arg     { 0   } def");
        //Implementation of Right, Left and Forward procedures in PostScript
        Parser.printer.println("/Right   {");
        Parser.printer.println("Heading exch add Trueheading");
        Parser.printer.println("/Heading exch def");
        Parser.printer.println("} def");
        Parser.printer.println("/Left {");
        Parser.printer.println("Heading exch sub Trueheading");
        Parser.printer.println("/Heading exch def");
        Parser.printer.println("} def");
        Parser.printer.println("/Trueheading {");
        Parser.printer.println("360 mod dup");
        Parser.printer.println("0 lt { 360 add } if");
        Parser.printer.println("} def");
        Parser.printer.println("/Forward {");
        Parser.printer.println("dup  Heading sin mul");
        Parser.printer.println("exch Heading cos mul");
        Parser.printer.println("2 copy Newposition");
        Parser.printer.println("rlineto");
        Parser.printer.println("} def");
        Parser.printer.println("/Newposition {");
        Parser.printer.println("Heading 180 gt Heading 360 lt");
        Parser.printer.println("and { neg } if exch");
        Parser.printer.println("Heading  90 gt Heading 270 lt");
        Parser.printer.println("and { neg } if exch");
        Parser.printer.println("Ypos add /Ypos exch def");
        Parser.printer.println("Xpos add /Xpos exch def");
        Parser.printer.println("} def");
    }

    public static void psEpilogue() {
        /* epilogue ... */
        Parser.printer.println("Xpos Ypos moveto");
        Parser.printer.println(0);
        Parser.printer.println("MAIN");
        Parser.printer.println("stroke");
        Parser.printer.println("showpage");
    }
}
