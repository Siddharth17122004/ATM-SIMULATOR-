package banking.management.system;
import java.sql.*;
public class Conn{
    Connection c;
    Statement s;
    
    public Conn(){
        try{//exception handling as sql is external entity
            //Class.forName(com.mysql.cj.jdbc.Driver); Register Driver-1 implicitly done
            c=DriverManager.getConnection("jdbc:mysql:/// bankmanagementsystem","root","Siddharth17");///Create Connection
            s=c.createStatement();//Create Statement to execute queries;
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
