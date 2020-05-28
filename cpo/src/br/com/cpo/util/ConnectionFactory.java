package br.com.cpo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection con;
	  
	  public static Connection getConnection(String databaseName) {
	    try {
	      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	      String url = null;
	      if (con == null || con != null) {
	       
	          url = "jdbc:sqlserver://192.168.0.5\\SQL2012:56307;DatabaseName=R16_Oficial;user=sa;password=#o6l5g4b3e2r1#";
	          
	        
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
