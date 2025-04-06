package banking.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;//for Resultset
public class Login extends JFrame implements ActionListener{
    JButton login,signup,clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        setLayout(null);
        
        Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label=new JLabel(i3);
        
        label.setBounds(70,10,100,100);
        
        add(label);
        JLabel text=new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);
        
        JLabel cardno=new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD,28));
        cardno.setBounds(120,150,150,30);
        add(cardno);
        
        cardTextField=new JTextField();//can be added anywhere,its here because for better organizing
        cardTextField.setBounds(300,150,230,30);//second & 4th field height same as cardno for it appear next to it
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);
         
        JLabel pin=new JLabel("PIN");
        pin.setFont(new Font("Raleway", Font.BOLD,28));
        pin.setBounds(120,220,250,30);
        add(pin);
        
        pinTextField=new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);
        
        login=new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        clear=new JButton("CLEAR");
        clear.setBounds(430,300,100,30);//login is 300+100=400 so start from 430
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup=new JButton("SIGNUP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(800,500);
        setVisible(true);
        setLocation(350,200);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }else if(ae.getSource()==login){
            Conn conn=new Conn();
            String cardnumber=cardTextField.getText();
            String pinnumber=pinTextField.getText();//slash because getText() cannot be used for JPassword class
            String query = "Select * from login where cardnumber = '" + cardnumber + "' and pin='" + pinnumber + "'";
            try{
 /*after query executed data is returned which is stored in result set*/ 
                  ResultSet rs=conn.s.executeQuery(query);
//usually executeUpdate(query)used,now executeQuery(query)used as this is a DDL command
                if(rs.next()){//if data is obtained
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{//if data not matched or pinned;
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne().setVisible(true);
            
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
