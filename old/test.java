import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
	String url = "jdbc:oracle:thin:@oracle:1521/ORCLPDB1";
	String user = "takehiko";
	String password = "ria1man1+";

		String sql = "SELECT count(id) as count FROM country_master WHERE country_name_jp = ? and country_name_en = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "アンティグア・バーブーダ");
            pstmt.setString(2, "Antigua & Barbuda");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("cout" + rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
