package logoCompiler.parser;

import java.io.*;
import logoCompiler.lexer.*;

public final class Parser {

    public static Token t;
    public static boolean error = false;
    public static int noOfErrors;

    public static PrintWriter printer;

    public static void enablePrinter() throws IOException {
            printer = new PrintWriter("test.ps");

    }

    public static void foundError() {
        error = true;
    }

    public static boolean getError() {
        return error;
    }

    public static void addError() {
        noOfErrors++;
    }

    public static int getNoOfErrors() {
        return noOfErrors;
    }

}
