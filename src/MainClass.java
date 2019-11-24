import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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

				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				Statement st = con.createStatement(); // 3. create statement
				ResultSet rs = st.executeQuery("SELECT * FROM Category"); // execute statement
				System.out.println("Your result: ");
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("Name");
					System.out.println(id + "|" + name);
				}
				con.close();
			} else if (cmd == 2) {
				System.out.println("Please enter ID: ");
				int id = scan.nextInt();
				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
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
					System.out.println(id + "|" + name);
				}
				con.close();
			} else if (cmd == 3) {
				scan.nextLine(); // xu ly ky tu ENTER -> clear buffer /flush
				System.out.println("Please ENTER keyword: ");
				String keyword = scan.nextLine();
				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE NAME LIKE ?");
				pst.setString(1, "%" + keyword + "%");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("Name");
					System.out.println(id + " | " + name);
				}
				con.close();
			}
		}
		scan.close();
		System.out.println("END PROGRAM");
	}

}
