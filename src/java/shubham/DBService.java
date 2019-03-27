package shubham;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBService {
    static private Connection con;
    
    static
    {
        try
        {
         DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           con = DriverManager.getConnection("jdbc:MySQL://localhost:3309/moriarity", "root", "root");
            if(con!= null)
            {
                System.out.println("OK");
            }
            else
            {
                System.out.println("Not OK");
            }
        }
        
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
    }
    
    public static Connection getConnection()
    {
        return con;
    }
}
