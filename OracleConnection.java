import java.sql.*;
import oracle.jdbc.driver.*;

public class OracleConnection  implements DoConnection {

	public static String getUrl() {
		return url;
	}
	public static String getUser() {
		return user;
	}
	public static String getPassword() {
		return password;
	}

	public static Connection getoc() {

		OracleConnection oc = new OracleConnection();
		String url = oc.getUrl();
		String user = oc.getUser();
		String password = oc.getPassword();


		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(url, user, password);
			return connection;

		} catch (ClassNotFoundException e) {
			System.err.println("not found jdbc driver:" + e.getMessage());
			return connection;
		} catch (SQLException e) {
			System.err.println("SQL error :" + e.getMessage());
		 	return connection;
		} finally {
		 	return connection;
		}
	}


	
	public static boolean closeConnection( Connection con ) {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Resource not closed:" + e.getMessage());
			return false;
		}
		return true;
	}
}
