package orukomm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database singleton class providing the connection to the database and freeing connection resources.
 */
public class Database {	

	private static Database singleton = null;
	private Connection connection;
	private final String dbDriver = "com.mysql.jdbc.Driver";
	private final String connectionString = "jdbc:mysql://localhost:3306/oru_komm?autoReconnect=true&useSSL=false";

	private final String dbUser = "SYSDBA";
	private final String dbPassword = "masterkey";

	private Database() {
		try {
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Database getInstance() {
		if (singleton == null) {
			singleton = new Database();
		}

		return singleton;
	}

	public Connection getConnection() {
		return connection;
	}

	/*
	 * Returns the number of rows fetched to the ResultSet.
	 */
	public static int fetchedRows(ResultSet rs) {
		int rows = 0;
		
		try {
			rs.last();
			rows = rs.getRow();
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rows;
	}
	
	/*
	 * Closes provided database resources.
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();

			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
