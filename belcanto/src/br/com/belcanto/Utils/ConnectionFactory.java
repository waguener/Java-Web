package br.com.belcanto.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection con;
	  
	  public static Connection getConnection(String databaseName) {
	    try {
	      Class.forName("org.postgresql.Driver");
	      String url = null;
	      if (con == null || con != null) {
	       
	          url = "jdbc:postgresql://localhost:5432/belcanto;DatabaseName=belcanto;user=postgres;password=postgres";
	          
	        
	        con = DriverManager.getConnection(url);
	        System.out.println("Conexão ok!!");
	      }  return con;
	    
	    }
	    catch (ClassNotFoundException E) {
	      E.printStackTrace();
	    }
	    catch (SQLException S) {
	      S.printStackTrace();
	      throw new RuntimeException();
	    } 
	    
	    return null;
	  }
}
