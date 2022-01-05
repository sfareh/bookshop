package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static String dburl = "jdbc:mysql://localhost:8889/bookStore";
	private static String dbuser = "root";
	private static String dbpass = "root";
	public static Connection connection = null;

	public static void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, dbuser, dbpass);
			System.out.println("Driver OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("PROBLEME MYSQL DRIVER");
		}
	}
}