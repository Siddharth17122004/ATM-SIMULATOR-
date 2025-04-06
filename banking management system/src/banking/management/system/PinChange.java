package banking.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    JButton change, back;
    JPasswordField pin, repin;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // Setting background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Text for "Change Your PIN"
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        // Label and field for "New PIN"
        JLabel pintext = new JLabel("New PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);

        // Label and field for "Re-Enter PIN"
        JLabel repintext = new JLabel("Re-Enter PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 360, 180, 25);
        image.add(repin);

        // Change Button
        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        // Back Button
        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // Window settings
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            // If "CHANGE" button is clicked
            if (ae.getSource() == change) {
                String npin = pin.getText();
                String rpin = repin.getText();

                // Check if entered PINs match
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                // Validate PIN fields
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter pin");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new Pin");
                    return;
                }

                // Database update queries
                Conn conn = new Conn();
                String query1 = "UPDATE bank SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";
                String query2 = "UPDATE login SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";
                String query3 = "UPDATE signupthree SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";

                // Execute the queries
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                // Success message
                JOptionPane.showMessageDialog(null, "Pin changed successfully");

                // Transition to the Transactions screen
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } else if (ae.getSource() == back) {
                // When "Back" button is clicked, go to Transactions screen
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    // Main method to run the program
    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
