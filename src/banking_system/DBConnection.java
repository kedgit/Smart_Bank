package banking_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// local system path 
		private static final String URL="jdbc:postgresql://localhost:5432/bank";	
		
		// server name who handle
		private static final String USER="postgres";	
		
		// database password
		private static final String PASSWORD="mahi7777";	

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println(" Postgresql Driver not found!");
            throw new SQLException(e);
        }
    }
}
