/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculate;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
/**
 *
 * @author prabe
 */
public class Calculator implements ActionListener{
    Frame calFrame;
    Panel disPanel,keyPanel,uKeyPanel,lKeyPanel,cKeyPanel;
    Label alLabel, calLabel;
    Button num[], opt[], clr, bak, eql;
    Calculate cal;
    double a, b;
    int x, y, i;
    char chr[] = {'+','-','*','/'};
    public Calculator(){
        calFrame = new Frame("Calculator");
        cal = new Calculate();
        a = 0;
        b = 0;
        
        /*------Starting Display Panel------*/
        disPanel = new Panel();
        
            /*------All display------*/
            alLabel = new Label("     ",Label.RIGHT);
            alLabel.setSize(300,25);
            disPanel.add(alLabel);

            /*------Result Display------*/
            calLabel = new Label("0"+" ",Label.RIGHT);
            calLabel.setSize(300,25);
            Font font = new Font("Verdana", Font.BOLD, 36);
            calLabel.setFont(font);
            disPanel.add(calLabel);

            /*------Adding Display Panel*/
            disPanel.setLayout(new GridLayout(2,1));
            calFrame.add(disPanel, BorderLayout.NORTH);
        
        /*------End of Display Panel------*/
        
        /*-----Starting Key Panel-----*/
        keyPanel = new Panel();
        
            /*------Upper Keys------*/
            uKeyPanel = new Panel(new GridLayout(1,4,5,5));
            bak = new Button("Clr");  //Clear Button
            bak.setPreferredSize(new Dimension(75,75));
            opt = new Button[4];
            for(int i = 0; i < 4; i++)
                opt[i] = new Button(""+chr[i]);
            uKeyPanel.add(bak);
            uKeyPanel.add(opt[3]);
            uKeyPanel.add(opt[2]);
            uKeyPanel.add(opt[1]);
                
            /*------Middle Keys------*/
            cKeyPanel = new Panel(new GridLayout(4,3,5,5));
            num = new Button[10];
            for(int i = 0; i<10; i++)
                num[i] = new Button(""+i);
            cKeyPanel.add(num[7]);
            cKeyPanel.add(num[8]);
            cKeyPanel.add(num[9]);
            cKeyPanel.add(num[4]);
            cKeyPanel.add(num[5]);
            cKeyPanel.add(num[6]);
            cKeyPanel.add(num[1]);
            cKeyPanel.add(num[2]);
            cKeyPanel.add(num[3]);
            cKeyPanel.add(num[0]);
            num[1].setPreferredSize(new Dimension(75,75));
                
            /*------Lower Keys------*/
            lKeyPanel = new Panel(new GridLayout(2,1,5,5));
            eql = new Button("=");
            lKeyPanel.add(opt[0]);
            lKeyPanel.add(eql);
            opt[0].setPreferredSize(new Dimension(75,155));
                            
            /*-----Adding Key Panel-----*/    
            keyPanel.add(uKeyPanel, BorderLayout.NORTH);
            keyPanel.add(cKeyPanel, BorderLayout.CENTER);
            keyPanel.add(lKeyPanel, BorderLayout.EAST);
            calFrame.add(keyPanel, BorderLayout.SOUTH);
            calFrame.add(keyPanel);    
        
        /*------End of Key Panel------*/
            
        calFrame.setSize(350,565);
        calFrame.setVisible(true);
        
        /*----------End of Calculator Layout----------*/
        
        bak.addActionListener(this);
        
        for(i=0; i<10; i++){
            num[i].addActionListener(this);
        }
        
        for(i=0; i<4; i++){
            opt[i].addActionListener(this);
        }
        
        eql.addActionListener(this);
        
        calFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }
    
    public void actionPerformed(ActionEvent ae){
        System.out.println(ae.getSource());
        for(i=0;i<10;i++){
            if(ae.getSource()==num[i]){
                if(cal.op == '0'){
                    cal.a = cal.a*10 + i;
                    display(cal.a);
                }
                else{
                    cal.b = cal.b*10 + i;
                    display(cal.b);
                }
            }
        }
        
        for(i=0;i<4;i++){
            if(ae.getSource()==opt[i]){
                if(cal.op!='0'){
                    display(cal.result());
                    cal.a=cal.r;
                    cal.b = 0;
                    cal.op = '0';
                }
                cal.op = chr[i];
//                if(a != (int) a)
                    alLabel.setText(calLabel.getText()+"     "+cal.op+"     ");
//                else
//                    alLabel.setText((int)a+op+"     ");
                calLabel.setText(" ");
            }
        }
        
        String disp;
        if(ae.getSource()==eql){
            if(cal.op!='0'&&cal.b!=0)
                alLabel.setText(alLabel.getText()+calLabel.getText());
            display(cal.result());
            cal.a=cal.r;
            cal.b = 0;
            cal.op = '0';
        }
        
        if(ae.getSource()==bak){
            String str = calLabel.getText();
//            str = str.substring(0, (str.length()-2));
//            System.out.println(Double.parseDouble(str));
            if(str.length()>2){
                str = str.substring(0, (str.length()-2));
                if(cal.op!='0')
                    cal.b = Double.parseDouble(str);
                else
                    cal.a = Double.parseDouble(str);
                display(Double.parseDouble(str));
            }
            else{
                if(cal.op!='0')
                    cal.b = 0;
                else
                    cal.a = 0;
                display(0);
            }
        }
        
    }
    
    public void display(double q){
        long s = (long) q;
        if(s==q)
            calLabel.setText(s+" ");
        else
            calLabel.setText(q+" ");
    }
    
//    public double result(){
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(4);
//        double eq = 0;
//        switch(op){
//            case "+":
//                eq = a+b;
//                break;
//            case "-":
//                eq = a-b;
//                break;
//            case "*":
//                eq = a*b;
//                break;
//            case "/":
//                eq = a/b;
//                break;
//        }
//        return Double.parseDouble(df.format(eq));
//    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}
