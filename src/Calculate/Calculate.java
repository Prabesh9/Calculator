/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculate;

import java.text.DecimalFormat;

/**
 *
 * @author prabe
 */
public class Calculate {
    public double a, b, r;
    public char op;
    
    public Calculate(){
        op = '0';
    }
    
    public double result(){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        switch(op){
            case '+':
                r = a+b;
                break;
            case '-':
                r = a-b;
                break;
            case '*':
                r = a*b;
                break;
            case '/':
                r = a/b;
                break;
            default :
                r = a;
        }
        return Double.parseDouble(df.format(r));
    }
}
