-- Lệnh này sẽ tạo một cơ sở dữ liệu mới có tên là 'bai7' nếu nó chưa tồn tại.
-- Nó cũng thiết lập bảng mã utf8mb4 để hỗ trợ tốt nhất cho tiếng Việt có dấu.
CREATE DATABASE IF NOT EXISTS `bai7` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Lệnh này để chọn CSDL 'bai7' làm nơi thực hiện các lệnh tiếp theo.
-- Đây là lệnh quan trọng để tránh lỗi "No database selected".
USE `bai7`;

-- --------------------------------------------------------

--
-- Lệnh tạo cấu trúc cho bảng `SoNguyen`
-- Bảng này sẽ lưu trữ các số nguyên mà người dùng nhập vào.
--
CREATE TABLE IF NOT EXISTS `SoNguyen` (
  -- Cột 'id' là khóa chính, tự động tăng giá trị (1, 2, 3...) cho mỗi dòng mới.
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  -- Cột 'giaTri' để lưu giá trị số nguyên, không được phép rỗng (NOT NULL).
  `giaTri` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đổ một vài dữ liệu mẫu vào bảng `SoNguyen` để bạn kiểm tra
--
INSERT INTO `SoNguyen` (`giaTri`) VALUES
(10),
(15),
(-8),
(20),
(37);