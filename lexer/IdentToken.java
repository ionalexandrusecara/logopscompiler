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
public class IdentToken extends Token{
    public String name;
    public IdentToken(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
