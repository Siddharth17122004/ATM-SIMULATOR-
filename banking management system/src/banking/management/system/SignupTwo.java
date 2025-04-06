package banking.management.system;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
public class SignupTwo extends JFrame implements ActionListener{
    
    JTextField aadharTextField,panTextField;
    JButton next;
    JRadioButton eyes,eno,sno,syes;
    JComboBox relig,cat,inc,eq,occ;
    String formno;
    
    
    SignupTwo(String formno){
        this.formno=formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM-PAGE:2");
        
        JLabel additionalDetails=new JLabel("Page 2:Additional details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);
        
        JLabel religion=new JLabel("Religion:");
        religion.setFont(new Font("Raleway",Font.BOLD,20));
        religion.setBounds(100,140,100,30);
        add(religion);
        
        String[] valReligion={"Hindu","Muslim","Christian","Other"};
        relig=new JComboBox(valReligion);
        relig.setBounds(300,140,400,30);
        relig.setBackground(Color.WHITE);
        add(relig);
        
        JLabel category=new JLabel("Category:");
        category.setFont(new Font("Raleway",Font.BOLD,20));
        category.setBounds(100,190,200,30);
        add(category);
        
        String[] valcategory={"General","OBC","SC","ST","Other"};
        cat=new JComboBox(valcategory);
        cat.setBounds(300, 190, 400, 30);
        cat.setBackground(Color.WHITE);
        add(cat);

        
        
        
        JLabel income=new JLabel("Income:");
        income.setFont(new Font("Raleway",Font.BOLD,20));
        income.setBounds(100,240,200,30);
        add(income);
        
       
        String[] incomecategory={"Null","<1,50,000","<2,50,000","<5,00,000","Upto 10,00,000"};
        inc=new JComboBox(incomecategory);
        inc.setBounds(300, 240, 400, 30); // for Income
        inc.setBackground(Color.WHITE);
        add(inc);

          
        
        
        JLabel education=new JLabel("Educational:");
        education.setFont(new Font("Raleway",Font.BOLD,20));
        education.setBounds(100,290,200,30);
        add(education);
        
        JLabel qualification=new JLabel("Qualification:");
        qualification.setFont(new Font("Raleway",Font.BOLD,20));
        qualification.setBounds(100,315,200,30);
        add(qualification);
        
        String[] edu={"Non-Graduation","Graduate","Post-Graduation","Doctrate","Others"};
        eq=new JComboBox(edu);
        eq.setBounds(300, 315, 400, 30);
        eq.setBackground(Color.WHITE);
        add(eq);
        
        
        JLabel occupation=new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway",Font.BOLD,20));
        occupation.setBounds(100,390,200,30);
        add(occupation);
        
        String[] o={"Salaried","Self-employed","Business","Student","Retired","Others"};
        occ=new JComboBox(o);
        occ.setBounds(300, 390, 400, 30);
        occ.setBackground(Color.WHITE);
        add(occ);
        
        
        JLabel pan=new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway",Font.BOLD,20));
        pan.setBounds(100,440,200,30);
        add(pan);
        
        panTextField=new JTextField();
        panTextField.setFont(new Font("Raleway",Font.BOLD,14));
        panTextField.setBounds(300,440,400,30);
        add(panTextField);
        
        JLabel aadhar=new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Raleway",Font.BOLD,20));
        aadhar.setBounds(100,490,200,30);
        add(aadhar);
        
        aadharTextField=new JTextField();
        aadharTextField.setFont(new Font("Raleway",Font.BOLD,14));
        aadharTextField.setBounds(300,490,400,30);
        add(aadharTextField);
        
        JLabel scitizen=new JLabel("Senior citizen:");
        scitizen.setFont(new Font("Raleway",Font.BOLD,20));
        scitizen.setBounds(100,540,200,30);
        add(scitizen);
        
        syes=new JRadioButton("Yes");
        syes.setBounds(300, 540, 100, 30); // for Senior Citizen Yes
        syes.setBackground(Color.WHITE);
        add(syes);
        
        sno=new JRadioButton("No");
        sno.setBounds(450, 540, 100, 30); // for Senior Citizen No
        sno.setBackground(Color.WHITE);
        add(sno);
        
        ButtonGroup maritalgroup=new ButtonGroup();
        maritalgroup.add(syes);
        maritalgroup.add(sno);
        
        JLabel acc=new JLabel("Existing Account:");
        acc.setFont(new Font("Raleway",Font.BOLD,20));
        acc.setBounds(100,590,200,30);
        add(acc);
        
        eyes=new JRadioButton("Yes");
        eyes.setBounds(300, 590, 100, 30); // for Existing Account Yes
        eyes.setBackground(Color.WHITE);
        add(eyes);
        
        eno=new JRadioButton("No");
        eno.setBounds(450, 590, 100, 30); // for Existing Account No
        eno.setBackground(Color.WHITE);
        add(eno);
        
        ButtonGroup emaritalgroup=new ButtonGroup();
        emaritalgroup.add(eyes);
        emaritalgroup.add(eno);
        
        
        
        next=new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        String sreligion=(String)relig.getSelectedItem();
        String scategory =(String)cat.getSelectedItem();
        String sincome=(String)inc.getSelectedItem();
        String seducation=(String)eq.getSelectedItem();
        String soccupation=(String)occ.getSelectedItem();
        String seniorcitizen=null;
       
        if(syes.isSelected()){
            seniorcitizen="Yes";
        }else if(sno.isSelected()){
            seniorcitizen="No";
        }
        
        
        String existingacc=null;
        if(eyes.isSelected()){
            existingacc="Yes";
        }else if(eno.isSelected()){
            existingacc="No";
       }
        String span=panTextField.getText();
        String saadhar=aadharTextField.getText();
      
        
        try{
            
                Conn c=new Conn();
                String query="insert into signuptwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+span+"','"+saadhar+"','"+seniorcitizen+"','"+existingacc+"')";
                c.s.executeUpdate(query);
                
                //Signup3 object;
                setVisible(false);
                new SignupThree(formno).setVisible(true);
                


            
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    public static void main(String args[]){
        new SignupTwo("");
    }
}

