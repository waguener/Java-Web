package br.com.controle.util;

import java.sql.DriverManager;
import java.sql.SQLException;




public class ConnectionFactory {
	
	public static java.sql.Connection  getConnection() throws ClassNotFoundException, SQLException{
		
		
		java.sql.Connection connection ; 
		
		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName);
		
		String serverName = "localhost";
		String myDb = "olgber";
		String url = "jdbc:mysql://"+serverName+"/"+myDb;
		String user = "root";		
		String password = "";
		
		connection = DriverManager.getConnection(url,user,password);
		
			System.out.println("TESTE : "+connection);
		
		return connection;
	
	
		
	}
}
