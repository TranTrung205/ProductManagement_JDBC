package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Product;
import utils.jdbcUtil;

public class ProductDAO {
	public static List<Product> getAll() throws Exception {
		List<Product> prods = new ArrayList<Product>();
		Connection con = jdbcUtil.getConnection();
		Statement st = con.createStatement(); // 3. create statement
		ResultSet rs = st.executeQuery("SELECT * FROM Product"); // execute statement
		System.out.println("Your result: ");
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			int price = rs.getInt("Price");
			int catID = rs.getInt("CatID");
			Product prod = new Product(id, name, price, catID);
			prods.add(prod);
		}
		con.close();
		return prods;
	}

	public static Product getProductDetail(int id) throws Exception {
		Product prod = null;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Product WHERE ID=?"); // prepare // statement
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		System.out.println("Your result: ");
		if (rs.next()) {
			String name = rs.getString("Name");
			int price = rs.getInt("Price");
			int catID = rs.getInt("CatID");
			prod = new Product(id, name, price, catID);
		}
		con.close();
		return prod;
	}

	public static List<Product> search(int threshold) throws Exception {
		List<Product> prods = new ArrayList<Product>();
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Product WHERE PRICE > ?");
		pst.setInt(1, threshold);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			int price = rs.getInt("Price");
			int catID = rs.getInt("CatID");
			Product prod = new Product(id, name, price, catID);
			prods.add(prod);
		}
		con.close();
		return prods;
	}

	public static int insert(Product newProduct) throws Exception {
		// cach xu ly theo ID
		int result = 0;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO Product(Name,Price,CatID) VALUES(?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, newProduct.name);
		pst.setInt(2, newProduct.price);
		pst.setInt(3, newProduct.catID);
		int n = pst.executeUpdate();
		if (n > 0) {
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				// insert 1 dong nen dung IF< con nhieu dong thi dung WHILE
				int newID = rs.getInt(1);
				result = newID;
			}
		}
		con.close();
		return result;
	}

	public static boolean update(Product newProd) throws Exception {
		boolean result = false;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("UPDATE Product SET Name=?,Price=?,CatID=? WHERE ID=?");
		pst.setString(1, newProd.name);
		pst.setInt(2, newProd.price);
		pst.setInt(3, newProd.catID);
		pst.setInt(4, newProd.id);
		int n = pst.executeUpdate();
		if (n > 0) {
			result = true;
		}
		con.close();
		return result;
	}
	public static boolean delete(int id) throws Exception {
		boolean result = false;
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("DELETE FROM Product WHERE ID=?");
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		if (n > 0) {
			result = true;
		}
		con.close();
		return result;
	}
}
