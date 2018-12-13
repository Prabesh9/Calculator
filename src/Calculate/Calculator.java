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
    Button num[], opt[], clr, bak, eql, dec, neg;
    Calculate cal;
    String a, b;
    int x, y, i;
    char chr[] = {'+','-','*','/'};
    DecimalFormat df = new DecimalFormat();
    Boolean eq = false;
    public Calculator(){
        calFrame = new Frame("Calculator");
        cal = new Calculate();
        a = "";
        b = "";
        
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
            uKeyPanel = new Panel(new GridLayout(3,4,5,5));
            clr = new Button("AC");
            bak = new Button("<<<");
            bak.setPreferredSize(new Dimension(75,75));
            opt = new Button[4];
            for(int i = 0; i < 4; i++)
                opt[i] = new Button(""+chr[i]);
            uKeyPanel.add(clr);
            uKeyPanel.add(bak);
            uKeyPanel.add(opt[3]);
            uKeyPanel.add(opt[2]);
            num = new Button[10];
            for(int i = 0; i<10; i++)
                num[i] = new Button(""+i);
            uKeyPanel.add(num[7]);
            uKeyPanel.add(num[8]);
            uKeyPanel.add(num[9]);    
            uKeyPanel.add(opt[1]);
            uKeyPanel.add(num[4]);
            uKeyPanel.add(num[5]);
            uKeyPanel.add(num[6]);
            uKeyPanel.add(opt[0]);
            
            /*------Middle Keys------*/
            cKeyPanel = new Panel(new GridLayout(2,3,5,5));
            cKeyPanel.add(num[1]);
            cKeyPanel.add(num[2]);
            cKeyPanel.add(num[3]);
            neg = new Button("+/-");
            cKeyPanel.add(neg);
            cKeyPanel.add(num[0]);
            dec =new Button(".");
            cKeyPanel.add(dec);
            num[1].setPreferredSize(new Dimension(75,75));
                
            /*------Left Keys------*/
            lKeyPanel = new Panel(new GridLayout(1,1,5,5));
            eql = new Button("=");
            lKeyPanel.add(eql);
            eql.setPreferredSize(new Dimension(75,155));
                            
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
        dec.addActionListener(this);
        
        
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
                if(eq)
                    a = "";
                if(cal.op == '0'){
                    a += i;
                    calLabel.setText(a+" ");
                }
                else{
                    b += i;
                    calLabel.setText(cal.op+b+" ");
                }
                eq = false;
                break;
            }
        }
        
        for(i=0;i<4;i++){
            if(ae.getSource()==opt[i]){
                if(b!=""){
                    cal.a = Double.parseDouble(a);
                    cal.b = Double.parseDouble(b);
                    display(cal.result());
                    a=calLabel.getText();
                    b = "";
                    cal.op = '0';
                }
//                System.out.println(a);
                cal.a = Double.parseDouble(a);
//                cal.b=Double.parseDouble(b);
                cal.op = chr[i];
                alLabel.setText(a+"     ");
                calLabel.setText(cal.op+" ");
                eq = false;
                break;
            }
        }
        
        if(ae.getSource()==eql){
            eq = true;
            if(a!="")
                cal.a = Double.parseDouble(a);
            else
                cal.a=0;
            if(b!="")
                cal.b = Double.parseDouble(b);
            else
                cal.b=0;
            if(cal.op!='0'&&cal.b!=0)
                alLabel.setText(a+" "+cal.op+" "+b+"     ");
            else
                alLabel.setText(a+"     ");
            display(cal.result());
            a = calLabel.getText().substring(0, calLabel.getText().length()-1);
            a = a.replace(",", "");
            b = "";
            cal.op = '0';
        }
        
        if(ae.getSource()==bak){
            if(!eq){
            String str="0";
                if(b.length()>1){
                    b = b.substring(0, b.length()-1);
                    calLabel.setText(cal.op+b+" ");
                }
                else if(b.length()==1&&b!="0"){
                    b = "";
                    calLabel.setText(cal.op+" ");
                }
                else if(cal.op!='0'){
                    cal.op = '0';
                    alLabel.setText("");
                    calLabel.setText(a+" ");
                }
                else if(a.length()>1){
                    a = a.substring(0, a.length()-1);
                    calLabel.setText(a+" ");
                }
                else if(a.length()==1&&a!="0"){ 
                    a = "";
                    calLabel.setText("0 ");
                }
            }
        }
        if(ae.getSource()==dec){
            if(eq){
                a="";
                eq = false;
            }
            if(cal.op == '0'){
                if(!a.contains("."))
                    a += ".";
                calLabel.setText(a+" ");
                System.out.println(a);
            }
            else{
                if(!b.contains("."))
                    b += ".";
                calLabel.setText(cal.op+b+" ");
                System.out.println(b);
            }
        }
        
    }
    
    public void display(double q){
        long s = (long) q;
        
        df.setMaximumFractionDigits(4);
        if(s==q)
            calLabel.setText(df.format(s)+" ");
        else
            calLabel.setText(df.format(q)+" ");
    }
    public void display(String z){
        calLabel.setText(z+" ");
    }
    
    
    public static void main(String[] args) {
        new Calculator();
    }
}
