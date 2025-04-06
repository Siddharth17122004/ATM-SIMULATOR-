package banking.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {
    
    MiniStatement(String pinnumber){
        setTitle("Mini Statement");
        setLayout(null);
        
        // Create label for displaying the mini statement
        JLabel mini = new JLabel();
        add(mini);
        
        // Label for bank name
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
        // Label for card number
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        // Label for balance
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);
        
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
        try {
            Conn conn = new Conn();
            
            
            // Fetch account details based on the pin number
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pinnumber+"' ");
            while (rs.next()) {
                // Set card number
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12)); 
            }
            // Display the current balance
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"' ");
            
            // Set the mini statement text
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" +
                             rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;" + 
                             rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
                             rs.getString("amount") + "<br><br></html>");
                 if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current balance is Rs: " + bal);

        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Set bounds for mini statement label
        mini.setBounds(20, 140, 400, 200);
    }

    public static void main(String args[]){
        // Test with an empty string, ideally replace with a real PIN number for testing
        new MiniStatement("");
    }
}
