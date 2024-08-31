package bank.management.system;

import java.sql.*;

public class Conn {
    
    //There are 5 steps in jdbc connectivity
    //Register Driver
    //Create Connection
    //Create Statement
    //Execute Query
    //Close Connection
    
    //Creat Connection
    Connection c;
    Statement s;
    public Conn(){
        //we will have to perform exceptional handling as mysql is an external entity so it may
        //return us error at runtime
        try{
            //We will register the Driver first
            //this Driver is inside the jar file we imported mysql-connector-java-8.0.28
            //we have commented the line below because after adding the above jar file there seems to be
            //no need to explicitly register the Driver, jdbc can pick it up by itself from the jar file
            //Class.forName(com.mysql.cj.jdbc.Driver);
            //we will create a connection now
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "Lucifer2301@@");
            //we will create a statement
            s = c.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
