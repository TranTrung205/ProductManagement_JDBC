import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Category;
import utils.jdbcUtil;

public class MainClass {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("0.Exit");
			System.out.println("1.Get all categories");
			System.out.println("2.Get details a category");
			System.out.println("3.Search category");
			System.out.println("Please enter cmd: ");
			int cmd = scan.nextInt();
			if (cmd == 0)
				break;
			else if (cmd == 1) {
//				List<Category> cat2 ; // tao ra cat2 nhung dang null-> khi truy suat thi bi nullpointer
				List<Category> cats = new ArrayList<Category>(); // tao ra cats nhung da co vung nho, empty list ->
																	// khong bi nullpointer
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				Connection con = jdbcUtil.getConnection();
				Statement st = con.createStatement(); // 3. create statement
				ResultSet rs = st.executeQuery("SELECT * FROM Category"); // execute statement
				System.out.println("Your result: ");
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("Name");
					// System.out.println(id + "|" + name);
					Category cat = new Category(id, name);
					cats.add(cat);
					
				}
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
				}
				con.close();
			} else if (cmd == 2) {
				System.out.println("Please enter ID: ");
				int id = scan.nextInt();
				List<Category> cats = new ArrayList<Category>();
				Connection con = jdbcUtil.getConnection();
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				// Statement st = con.createStatement(); // 3. create statement
				// ResultSet rs = st.executeQuery("SELECT * FROM Category WHERE ID= " + id);
				// //execute statement
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE ID=?"); // prepare
																									// statement
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				System.out.println("Your result: ");
				if (rs.next()) {
					String name = rs.getString("Name");
					//System.out.println(id + "|" + name);
					Category cat = new Category(id, name);
					cats.add(cat);
				}
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
				}
				con.close();
			} else if (cmd == 3) {
				scan.nextLine(); // xu ly ky tu ENTER -> clear buffer /flush
				System.out.println("Please ENTER keyword: ");
				String keyword = scan.nextLine();
				List<Category> cats = new ArrayList<Category>();
				Connection con = jdbcUtil.getConnection();
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE NAME LIKE ?");
				pst.setString(1, "%" + keyword + "%");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("Name");
					//System.out.println(id + " | " + name);
					Category cat = new Category(id, name);
					cats.add(cat);
				}
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
				}
				con.close();
			}
		}
		scan.close();
		System.out.println("END PROGRAM");
	}

}
