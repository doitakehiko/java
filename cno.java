import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.driver.OracleLog;
import oracle.jdbc.pool.OracleDataSource;

public class cno {
	public static void main (String[] args) {
		String strPath = "";
		File readFile = null;
	        final String STR_USAGE = "Usage:java cno filename";
	        final String STR_FILE_NOT_FOUND = " is not found.";
	        final String strEncod = "utf-8";
	        //final String strEncod = "sjis";
		final String strSplit = ",";
		String strReadPath = "";
		String name_jp = "";
		String name_en = "";
		String prename_jp = "";
		String prename_en = "";
		int no = 0;

		List<String> list = new ArrayList<>();

		String[] strWord = null;

		if (args.length == 1 ) {
			strReadPath = args[0];
			readFile = new File( strReadPath );
			if (!readFile.exists()){
				System.out.println( readFile + STR_FILE_NOT_FOUND );
				return;
			} 
		} else if (args.length == 0) {
	                System.out.println(STR_USAGE);
	                return;
        	}

		OracleConnection oc = new OracleConnection();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), strEncod));
			String line = null;

			while ((line = br.readLine()) != null) {
				strWord = line.split(strSplit, 0);
				name_jp = strWord[0];
				name_en = strWord[1];
				no = Integer.valueOf(strWord[2]);
				int countRes = checkCno( name_jp , name_en , oc.getoc());
				if ( countRes >= 0 ) {
					if ( insertCno( name_jp , name_en , no, oc.getoc()) ) {
						System.out.println(":true");
					}
					else 
					{
						System.out.println(":false");
					}
					
				} else if ( countRes >= 1 ) {
					prename_jp = name_jp;
					prename_en = name_en;
					while ((line = br.readLine()) != null) {
						strWord = line.split(strSplit, 0);
						name_jp = strWord[0];
						name_en = strWord[1];
						no = Integer.valueOf(strWord[2]);
						if( prename_jp == name_jp && prename_en == name_en ) {
							if ( insertCno( name_jp , name_en , no, oc.getoc()) ) {
								System.out.println(":true");
							}
							else 
							{
								System.out.println(":falsehere2");

							}
						} else {
							break;
						}
					}

				} else {
					br.close();
					if ( oc.closeConnection(  oc.getoc() ) ) {
						System.out.println("db closed");
					}else {
						System.out.println("db closed error");
					}
					return;
				}
			}
			if ( oc.closeConnection(  oc.getoc() ) ) {

				System.out.println("db closed");
			}else {
				System.out.println("db closed error");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean insertCno ( String namejp, String nameen, int no, Connection connection)	{
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtIns = null;

		String sql = "SELECT id FROM country_master WHERE country_name_jp = ? and country_name_en = ?";


		String sqlIns = "INSERT INTO country_code_master ( country_master_id, country_code ) VALUES ( ? ,  ? )";
		int intIdNo = 0;

		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, namejp);
			pstmt.setString(2, nameen);
			resultSet = pstmt.executeQuery();

			pstmtIns = connection.prepareStatement(sqlIns);
			
			while (resultSet.next()) {

				intIdNo = resultSet.getInt("id");
				pstmtIns = connection.prepareStatement(sqlIns);
				pstmtIns.setInt(1,  intIdNo );
				pstmtIns.setInt(2,  no );
				pstmtIns.addBatch();
				int updateCounts[] = pstmtIns.executeBatch();
				if ( updateCounts.length == 0) {
					System.err.println("rollback:cno = " + no + "," + "id = " + intIdNo );
					connection.rollback();
					return false;
				}
				if ( updateCounts.length == 1) {
					System.out.println("commit:cno = " + no + "," + "id = " + intIdNo );
					connection.commit();
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("SQLError" + e.getMessage());
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
				System.err.println("Resource not closed:" + e.getMessage());
			}
		}
		System.err.println("cno = " + no + "," + "id = " + intIdNo );
		return false;
	}
	

	public static int checkCno( String namejp, String nameen, Connection conn) {
		String sql = "SELECT count(country_code_master.id) as count FROM country_master, country_code_master WHERE country_master.id =  country_code_master.country_master_id and country_master.country_name_jp = ? and country_master.country_name_en = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, namejp);
			pstmt.setString(2, nameen);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					return(rs.getInt("count"));				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
