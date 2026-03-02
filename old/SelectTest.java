import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.driver.OracleLog;
import oracle.jdbc.pool.OracleDataSource;

public class SelectTest {
	public static void main(String args[]) {

		OracleConnection oc = new OracleConnection();
		String url = oc.getUrl();
		String user = oc.getUser();
		String password = oc.getPassword();


		String sql = "SELECT id FROM set_a";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(url, user, password);

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int column2Value = resultSet.getInt("id");

				System.out.println("id: "  + column2Value);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("JDBCドライバが見つかりません:" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLエラー: " + e.getMessage());
		} finally {
			try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			} catch (SQLException e) {
				System.err.println("リソース解放エラー:" + e.getMessage());
			}
		}
	}
}
