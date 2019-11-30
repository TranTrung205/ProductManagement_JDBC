import java.util.List;
import java.util.Scanner;

import daos.CategoryDAO;
import entities.Category;

public class MainClass {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("0.Exit");
			System.out.println("1.Get all categories");
			System.out.println("2.Get details a category");
			System.out.println("3.Search category");
			System.out.println("4.Add new category");
			System.out.println("5.Update category");
			System.out.println("6.Delete a record");
			System.out.println("Please enter cmd: ");
			int cmd = scan.nextInt();
			if (cmd == 0)
				break;
			else if (cmd == 1) {
//				List<Category> cat2 ; // tao ra cat2 nhung dang null-> khi truy suat thi bi nullpointer
//				List<Category> cats = new ArrayList<Category>(); // tao ra cats nhung da co vung nho, empty list ->
				// khong bi nullpointer
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
//				Connection con = jdbcUtil.getConnection();
//				Statement st = con.createStatement(); // 3. create statement
//				ResultSet rs = st.executeQuery("SELECT * FROM Category"); // execute statement
//				System.out.println("Your result: ");
//				while (rs.next()) {
//					int id = rs.getInt("ID");
//					String name = rs.getString("Name");
//					// System.out.println(id + "|" + name);
//					Category cat = new Category(id, name);
//					cats.add(cat);
//					
//				}
				List<Category> cats = CategoryDAO.getAll();
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
				}
				// con.close();
			} else if (cmd == 2) {
				System.out.println("Please enter ID to search: ");
				int id = scan.nextInt();
//				List<Category> cats = new ArrayList<Category>();
//				Connection con = jdbcUtil.getConnection();
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
				// Statement st = con.createStatement(); // 3. create statement
				// ResultSet rs = st.executeQuery("SELECT * FROM Category WHERE ID= " + id);
				// //execute statement
//				PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE ID=?"); // prepare																					// statement
//				pst.setInt(1, id);
//				ResultSet rs = pst.executeQuery();
//				System.out.println("Your result: ");
//				if (rs.next()) {
//					String name = rs.getString("Name");
//					//System.out.println(id + "|" + name);
//					Category cat = new Category(id, name);
//					cats.add(cat);
//				}
				Category cat = new CategoryDAO().getDetails(id);
				if (cat != null) {
					System.out.println(cat.id + " | " + cat.name);
				}
				// con.close();
			} else if (cmd == 3) {
				scan.nextLine(); // xu ly ky tu ENTER -> clear buffer /flush
				System.out.println("Please ENTER keyword: ");
				String keyword = scan.nextLine();
//				List<Category> cats = new ArrayList<Category>();
//				Connection con = jdbcUtil.getConnection();
//				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
//				Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya",
//						"AutgEfreya", "q6qkzXDL12"); // 2. create Connection
//				PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE NAME LIKE ?");
//				pst.setString(1, "%" + keyword + "%");
//				ResultSet rs = pst.executeQuery();
//				while (rs.next()) {
//					int id = rs.getInt("ID");
//					String name = rs.getString("Name");
//					// System.out.println(id + " | " + name);
//					Category cat = new Category(id, name);
//					cats.add(cat);
//				}
				List<Category> cats = CategoryDAO.search(keyword);
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
				}
//				con.close();
			}
			else if(cmd ==4) {
				scan.nextLine(); //flush || clear buffer
				System.out.println("Please input name to insert: ");
				String name = scan.nextLine();
				Category newCat = new Category(0, name);
//				boolean result = CategoryDAO.insert(newCat);
//				if(result) {
//					System.out.println("INSERT OK");
//				}
//				else {
//					System.out.println("INSERT FAIL !!!");
//				}
				int newID = CategoryDAO.insert2int(newCat);
				if(newID > 0) {
					System.out.println("INSERT OK with ID : " + newID);
				}
				else {
					System.out.println("INSERT FAIL");
				}
					
			}
			else if(cmd == 5) {
				System.out.println("Please input ID you want to update: ");
				int newID = scan.nextInt();
				scan.nextLine();
				System.out.println("Please input Name to update: ");
				String newName = scan.nextLine();
				Category newCat = new Category(newID, newName);
				boolean result = CategoryDAO.update(newCat);
				if(result) {
					System.out.println("INSERT OK");
				}
				else {
					System.out.println("INSERT FAIL !!!");
				}
			}
			else if (cmd == 6) {
				System.out.println("Please input ID you want to remove: ");
				int removeID = scan.nextInt();
				boolean result = CategoryDAO.delete(removeID);
				if(result) {
					System.out.println("DELETE OK");
				}
				else {
					System.out.println("DELETE FAIL !!!");
				}
			}
		}
		scan.close();
		System.out.println("END PROGRAM");
	}

}
