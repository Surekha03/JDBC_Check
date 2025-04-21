package createdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DynamicApplication {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String Username = "root";
	private static final String Password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;

	public static void main(String[] args) {

		int choice;
		do {
			Scanner src = new Scanner(System.in);
			System.out.println("choose your choice");
			DisplayMenu();
			choice = Integer.parseInt(src.next());
			switch (choice) {
			case 1:
				CreateDatabase();
				break;
			case 2:
				DropDatabase();
				break;
			case 3:
				DataInsertion();
				break;
			case 4:
				DeleteByEmail();
				break;
			case 5:
				UpdateData();
				break;
			case 6:
				GetById();
				break;
			case 7:
				GetAll();
				break;
			case 8:
				System.exit(0);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} while (choice > 0);
	}

	private static void CreateDatabase() {
		try {
			Class.forName(Driver);
			String Url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter database name: ");
			Scanner src = new Scanner(System.in);
			String sql = "created Database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("datase created successfully");
			} else {
				System.out.println("database not created...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DropDatabase() {
		try {
			Class.forName(Driver);
			String Url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter Database name");
			Scanner src = new Scanner(System.in);
			String sql = "drop database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("Database Dropped Successfully");
			} else {
				System.out.println("OOPS Database Not Dropped...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void DataInsertion() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			System.out.println("Enter database name to insert data");
			String Url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter database table name");
			String sql = "insert into " + src.next()
					+ "(OrderId, OrderName, OrderPinCode, OrderAddress)values{?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			pmst.setLong(1, src.nextLong());
			System.out.println("Enter OrderId: ");
			pmst.setString(2, src.next());
			System.out.println("Enter OrderName: ");
			pmst.setInt(3, src.nextInt());
			System.out.println("Enter OrderPinCode: ");
			pmst.setString(4, src.next());
			System.out.println("Enter OrderAddress: ");
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("datase created successfully");
			} else {
				System.out.println("database not created...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void DeleteByEmail() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			System.out.println("Enter Database name to insert data");
			String Url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter Database table name");
			String sql = "delete from " + src.next() + " where order_id=? ";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter order id");
			pmst.setLong(1, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Database deleted Successfully");
			} else {
				System.out.println("Database Not deleted...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void UpdateData() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			System.out.println("Enter database name to insert data");
			String Url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter database table name");
			String sql = "update " + src.next() + "set OrderName=?,OrderPinCode=?,OrderAddress=? where OrderId=?";
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, src.next());
			System.out.println("Enter OrderName: ");
			pmst.setInt(2, src.nextInt());
			System.out.println("Enter OrderPinCode: ");
			pmst.setString(3, src.next());
			System.out.println("Enter OrderAddress: ");
			pmst.setLong(4, src.nextLong());
			System.out.println("Enter OrderId: ");
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("datase created successfully");
			} else {
				System.out.println("database not created...!");
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void GetById() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			System.out.println("Enter database name ");
			String Url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter table name");
			String sql = "select * from "+src.next()+" where OrderId=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter OrderId");
			pmst.setInt(1, src.nextInt());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("OrderId :"+rs.getInt("OrderId"));
				System.out.println("OrderName :"+rs.getString("OrderName"));
				System.out.println("OrderPinCode :"+rs.getInt("OrderPinCode"));
				System.out.println("OrderAddress :"+rs.getString("OrderAddress"));
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void GetAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			System.out.println("Enter database name ");
			String Url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter table name");
			String sql = "select * from " +src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("OrderId :"+rs.getInt("OrderId"));
				System.out.println("OrderName :"+rs.getString("OrderName"));
				System.out.println("OrderPinCode :"+rs.getInt("OrderPinCode"));
				System.out.println("OrderAddress :"+rs.getString("OrderAddress"));
			}
			pmst.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void DisplayMenu() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t3.data insertion");
		System.out.println("\t4.delete by email");
		System.out.println("\t5.update data");
		System.out.println("\t6. get by Id");
		System.out.println("\t7.get All");
		System.out.println("\t8.exit");
	}

}
