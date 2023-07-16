package ar.com.codoacodo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
	public static Connection getConnection() {
		String hosts = "127.0.0.1";
		String port = "33060";
		String password = "251201";
		String username = "root";
		
		String driverClassName = "com.mysql.cj.jdbc.Driver";	
		String dbName = "23049-db";
		Connection connection;
		try {
			Class.forName(driverClassName);
			
			String url = "jdbc:mysql://" + hosts + ":"+ port +"/"+ dbName +"?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
			
			connection = DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
			connection = null;
		}
		
		return connection;
	}
}
