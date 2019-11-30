package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Category;
import utils.jdbcUtil;

public class CategoryDAO {
	public static List<Category> getAll() throws Exception {
		List<Category> cats = new ArrayList<Category>();
		Connection con = jdbcUtil.getConnection();
		Statement st = con.createStatement(); // 3. create statement
		ResultSet rs = st.executeQuery("SELECT * FROM Category"); // execute statement
		System.out.println("Your result: ");
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			Category cat = new Category(id, name);
			cats.add(cat);
		}
		con.close();
		return cats;
	}

	public static Category getDetails(int id) throws Exception {
		Category cat = null;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE ID=?"); // prepare // statement
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		System.out.println("Your result: ");
		if (rs.next()) {
			String name = rs.getString("Name");
			cat = new Category(id, name);
		}
		con.close();
		return cat;
	}

	public static List<Category> search(String keyword) throws Exception {
		List<Category> cats = new ArrayList<Category>();
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE NAME LIKE ?");
		pst.setString(1, "%" + keyword + "%");
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			Category cat = new Category(id, name);
			cats.add(cat);
		}
		con.close();
		return cats;
	}
	
	public static boolean insert(Category newCat) throws Exception{
		//cach xu ly theo boolean
		boolean result = false;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO Category(Name) VALUES(?)");
		pst.setString(1, newCat.name);
		int n = pst.executeUpdate();
		if(n > 0) {
			result = true;
		}
		return result;
	}

}
