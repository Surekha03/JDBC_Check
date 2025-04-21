package createdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class dbcreation1 {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String Url = "jdbc:mysql://localhost:3306/";
	private static final String Username = "root";
	private static final String Password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;
	
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter database name: ");
			String sql = "create database " + src.nextLine();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("db is creted");
			}
			else {
				System.out.println("db is not created");
			}
			pmst.close();
			conn.close();
			src.close();
		} catch (Exception e) {
			e.printStackTrace();//represents type of error that occurs in system 
		}
	}

}
