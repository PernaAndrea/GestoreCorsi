package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	// conterra un unico metodo statico per le connessioni con il database 
	
	public static Connection getConnection() throws SQLException {
		
		String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=And13rea4";
		return DriverManager.getConnection(jdbcURL);
	}

}
