import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    // Đảm bảo trỏ đúng đến CSDL 'bai7'
    private static final String URL = "jdbc:mysql://localhost:3306/bai7";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return conn;
    }
}