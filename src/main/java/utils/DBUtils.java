package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class DBUtils {

	/**
	 * load the driver once only
	 */
	static{
		System.out.println("Loading mysql driver...");
	
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}
	
	
	private static Properties props = new Properties();
	private static InputStream input = null;
	
	private static void getProperties(){
		
		try {
		
			input = new FileInputStream("src/main/resources/config.properties");
			props.load(input);
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public static Connection getConnection(){
		getProperties();
 
		String url = props.getProperty("url");
		String username = props.getProperty("userName");
		String password = props.getProperty("password");
	
		
		System.out.println("Connecting to database...");
		Connection connection = null;
		
		try { 
			connection = DriverManager.getConnection(url, username, password); {
		}
		System.out.println("Database connected!");
		
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	
		return connection;
	}
	
}
