package jdbcdem02;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Anuja@2005";  // ← check this password!

	static {
		try {
			Class.forName(DB_DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static Connection getConnection() throws Exception{
		Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
		return con;
	}
}
