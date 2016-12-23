/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.lexer;

/**
 *
 * @author user
 */
public class BiggerToken extends OperatorToken{
    public int precedence(){
        return 1;
    }
    
    public String getName()
    {
        return "Bigger";
    }
    
    public static String getlogoCode() {
        return ">";
    }

    public String getPostscriptCode() {
        return "gt";
    }
}
