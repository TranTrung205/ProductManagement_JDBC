package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcUtil {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 1. load driver
		Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/AutgEfreya", "AutgEfreya",
				"q6qkzXDL12"); // 2. create Connection
		return con;
	}
}
