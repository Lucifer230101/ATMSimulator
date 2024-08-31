package bank.management.system;

//this import is for JFrame
import javax.swing.*;
//this import is for Image and setting the Color these functionalities we get from awt package
import java.awt.*;
//this import is for ActionListener
import java.awt.event.*;
//ResultSet is inside the sql
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    //we have defined these buttons and textfields already but locally inside the constructor so we can't access them
    //anywhere outside our constructor. Hence, we are defining them globally here.
    JButton login, signup, clear;
    JTextField cardTextField;
    //we dont want anyone to be able to see what pin we are entering so we use JPasswordField
    JPasswordField pinTextField;
    
    //to create a frame we need to use the concept of inheritance and extend the JFrame class
    //to add some actions to the values on button click we need to implement ActionListener interface
    //creating a constructor so that the inside block works as soon as the code runs
    Login(){
        
        setTitle("Automated Teller Machine");
        
//        by default swing applies its water layout to our project which has the property to position
//          all the components in the center so we will set the default layout to null
        setLayout(null);
        
        //using ImageIcon class to import our image that we want to place inside our frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        //to resize our image we use Iimage class
        Image i2 = i1.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
//        we need to convert the Image object to ImageIcon object to be able to place it inside JFrame
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
//        we will use the setBounds function of label object to set the location for the image component;
        label.setBounds(70, 10, 100, 100);
        //whenever we want to place any of our component isinde our JFrame we will use add() function
        add(label);
        
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);
        
        JLabel cardno = new JLabel("Card No.");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);
        
//        To add the textbox
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
        
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);
        
        //        To add the textbox
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
        
        //to create button we use JButton
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        //we will add actionlistener to tell the progran that yes the button ia clicked
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.white);
        
        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }
    
    //this is where we are overriding the abstract class of our ActionListener interface
    //the purpose of this function is to tell what to do after the button is clicked
    public void actionPerformed(ActionEvent ae){
        //now we need to differentiate which button is clicked for that we will use the ActionEvent class
        if(ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            //this dash on the getText indicates that this function is now not applicable for this field
            //which is true as pinnumber is passwordField not TextField but it will work anyway so kept as it is
            String pinnumber = pinTextField.getText();
            //we were using dml queries until now as we were sending and manipulating the data but now we will use
            //ddl commands to take data from the database
            String query = "select * from login where cardnumber = '"+ cardnumber + "' and pin = '"+ pinnumber +"'";
            try{
                //the above query once executed will return some data which we will store in the class ResultSet's object
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Nummber or Pin");
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
        }else if(ae.getSource() == signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    
    public static void main(String args[]){
        //new object of class created;
        new Login();
    }
    
}
