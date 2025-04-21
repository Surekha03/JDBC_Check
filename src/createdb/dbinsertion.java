package createdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class dbinsertion {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String Url = "jdbc:mysql://localhost:3306/Netflix";
	private static final String Username = "root";
	private static final String Password = "root";
	private static PreparedStatement pmst;
	private static Connection conn; 
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			String sql = " insert into new_table(LoginId,LoginEmail,LoginPassword) values(?,?,?) ";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter LoginId ");
			pmst.setString(1, src.nextLine());
			System.out.println("Enter LoginEmail");
			pmst.setString(2, src.nextLine());
			System.out.println("Enter LoginPassword");
			pmst.setString(3, src.nextLine());
			int i = pmst.executeUpdate();
			if( i > 0) {
				System.out.println("data inserted");
			}
			else {
				System.out.println("unable to insert data");
			}
			conn.close();
			pmst.close();
			src.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
