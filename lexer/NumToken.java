/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logoCompiler.lexer;

import logoCompiler.parser.*;

/**
 *
 * @author user
 */
public class NumToken extends Token {

    int number;

    public NumToken(int number) {
        this.number = number;
    }

    public int getNum(){
        return number;
    }
}
