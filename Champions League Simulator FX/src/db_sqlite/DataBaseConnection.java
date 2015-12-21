package db_sqlite;

import java.sql.*;

public class DataBaseConnection {
	public static Connection start_connection(String database_name) {
		Connection output_connection = null;
		String connection_name = "jdbc:sqlite:" + database_name + ".db";
		System.out.println(connection_name);
		try {
			Class.forName("org.sqlite.JDBC");
			output_connection = DriverManager.getConnection(connection_name);
			System.out.println("Opened database successfully");
			return output_connection;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.out.println("Error in the connection to the database.");
			return null;
		}
	}
}
