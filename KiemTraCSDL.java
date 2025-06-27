import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Lớp này là một chương trình độc lập, dùng để kiểm tra dữ liệu trong CSDL.
public class KiemTraCSDL {

    public static void main(String[] args) {
        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng SoNguyen
        String sql = "SELECT id, giaTri FROM SoNguyen ORDER BY id";

        System.out.println("Đang kết nối tới CSDL và truy vấn dữ liệu...");
        
        // Sử dụng try-with-resources để kết nối được đóng tự động
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- DỮ LIỆU HIỆN CÓ TRONG BẢNG SoNguyen ---");
            System.out.println("---------------------------------");
            System.out.printf("%-10s | %-10s\n", "ID", "Giá Trị");
            System.out.println("---------------------------------");
            
            // Lặp qua từng dòng kết quả và in ra
            while (rs.next()) {
                int id = rs.getInt("id");
                int giaTri = rs.getInt("giaTri");
                System.out.printf("%-10d | %-10d\n", id, giaTri);
            }
            System.out.println("---------------------------------");

        } catch (SQLException e) {
            // In ra lỗi nếu có vấn đề xảy ra
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}