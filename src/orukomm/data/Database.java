package orukomm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ...
 */
public class Database {
	private static Database singleton = null;
	private Connection connection;
	private final String dbDriver = "com.mysql.jdbc.Driver";
	private final String connectionString = "jdbc:mysql://localhost:3306/oru_komm";

	private final String dbUser = "root";
	private final String dbPassword = "admin";

	
	public Database()
	{
		try
		{
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
		}
		catch (ClassNotFoundException | SQLException ex)
		{
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static Database getInstance()
	{
		return singleton == null ? new Database() : singleton;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
}
