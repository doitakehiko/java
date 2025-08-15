import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.driver.OracleLog;
import oracle.jdbc.pool.OracleDataSource;

public class HealthManagement {
	public static void main(String args[]) {


		OracleConnection oc = new OracleConnection();
		String url = oc.getUrl();
		String user = oc.getUser();
		String password = oc.getPassword();

		final int RECORD_DATE_IDX = 0;
		final int WEIGHT_IDX = 1;
		final int FAT_IDX = 2;
		final int MUSCLE_IDX = 3;
		List<java.sql.Date> record_dateList = new ArrayList<>();
		List<Double> weightList = new ArrayList<>();
		List<Double> body_fatList = new ArrayList<>();
		List<Double> skeletal_muscleList = new ArrayList<>();

		String filePath = "s:\\java\\health_management.csv";

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
			String[] stringArray = line.split(",");
			   try {
				String strDate = stringArray[RECORD_DATE_IDX];
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date date = sdFormat.parse(strDate);
				String str = new SimpleDateFormat("yyyy-MM-dd").format(date);

				java.sql.Date sqlDate= java.sql.Date.valueOf(str);
				record_dateList.add( sqlDate );
				weightList.add( Double.parseDouble(stringArray[WEIGHT_IDX] ));
				body_fatList.add( Double.parseDouble(stringArray[FAT_IDX] ));
				skeletal_muscleList.add( Double.parseDouble(stringArray[MUSCLE_IDX] ));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO health_management (record_date, weight, body_fat, skeletal_muscle) VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);

			for (int i = 0; i < record_dateList.size(); i++ ) {
				pstmt.setDate(1, record_dateList.get(i));
				pstmt.setDouble(2, weightList.get(i));
				pstmt.setDouble(3, body_fatList.get(i));
				pstmt.setDouble(4, skeletal_muscleList.get(i));
				pstmt.addBatch();
			}

			int[] updateCounts = pstmt.executeBatch();
			connection.rollback(); // ロールバック

//			connection.commit();

			System.out.println("挿入成功: " + updateCounts.length + "件");

		} catch (ClassNotFoundException e) {
			System.err.println("JDBCドライバが見つかりません:" + e.getMessage());
		} catch (SQLException e) {

			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback(); // ロールバック
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			System.err.println("SQLエラー: " + e.getMessage());
		} finally {
			try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pstmt != null) {
				pstmt.close();
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
