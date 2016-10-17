package databaseConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import examples.branch;

public class OurConnection{
	private Connection con;

	public OurConnection(){
		Statement stmt;
		ResultSet rs;

		try
	      {
		// Load the Oracle JDBC driver
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	      }
	      catch (SQLException ex)
	      {
		System.out.println("Message: " + ex.getMessage());
		System.exit(-1);
	      }
		 
		 if(connect("ora_e5w9a", "a10682145")){
			 try{
				 stmt = con.createStatement();
				 String authorID;
				 rs = stmt.executeQuery("SELECT AU_ID FROM authors");

				 while(rs.next())
				 {
				   authorID = rs.getString("AU_ID");
				   if (rs.wasNull())
				      {
						  System.out.printf("%-11.11s", " ");
			              }
				      else
				      {
						  System.out.printf("%-11.11s", authorID);
				      }
				      System.out.println("     ");
				 }
				 
				 stmt.close();
			 }
			 catch (SQLException ex)
			{
				System.out.println("Message: " + ex.getMessage());
			}	
			 
		 }
		
	}

	private boolean connect(String username, String password)
    {
      String connectURL = "jdbc:oracle:thin:@localhost:1522:ug"; 

      try 
      {
	con = DriverManager.getConnection(connectURL,username,password);

	System.out.println("\nConnected to Oracle!");
	return true;
      }
      catch (SQLException ex)
      {
	System.out.println("Message: " + ex.getMessage());
	return false;
      }
    }
	
	public static void main(String args[])
    {
      OurConnection connection = new OurConnection();
    }

}
