package logoCompiler.parser;
import java.io.IOException;
import  logoCompiler.lexer.*;

/*
 * binary-expr:
 *   expr op expr
 *
 *   where op is one of '+',  '-',  '*', '/',
 *                      '==', '!=', '>', '<', '<=', '>='
 */
public final class BinaryExpr extends Expr {
  public Expr  left;
  public OperatorToken oper;
  public Expr  right;

  public BinaryExpr(Expr left, OperatorToken oper, Expr right) {
    this.left  = left;
    this.oper  = oper;
    this.right = right;
  }
  
public void codegen() throws IOException{
    left.codegen();
    right.codegen();
    Parser.printer.println(oper.getPostscriptCode());
  }
}