import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Bai7 extends JFrame {

    private final DefaultListModel<Integer> model;
    private final JList<Integer> dsSo;
    private final JTextField txtNhap;
    private final JCheckBox chkSoAm;

    public Bai7() {
        setTitle("Bài 7 - Quản lý danh sách số nguyên");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel trái: Chứa các nút tác vụ ---
        JPanel pnlTrai = new JPanel(new GridLayout(6, 1, 5, 5));
        pnlTrai.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));

        JButton btnChan = new JButton("Tô đen số chẵn");
        JButton btnLe = new JButton("Tô đen số lẻ");
        JButton btnSNT = new JButton("Tô đen số nguyên tố");
        JButton btnBoChon = new JButton("Bỏ tô đen");
        JButton btnXoa = new JButton("Xóa các giá trị đang tô đen");
        JButton btnTong = new JButton("Tổng giá trị trong JList");

        pnlTrai.add(btnChan);
        pnlTrai.add(btnLe);
        pnlTrai.add(btnSNT);
        pnlTrai.add(btnBoChon);
        pnlTrai.add(btnXoa);
        pnlTrai.add(btnTong);

        // --- Panel phải: Nhập liệu và danh sách ---
        JPanel pnlPhai = new JPanel(new BorderLayout(5, 5));
        pnlPhai.setBorder(BorderFactory.createTitledBorder("Nhập Thông Tin"));

        JPanel pnlNhap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNhap.add(new JLabel("Nhập thông tin:"));
        txtNhap = new JTextField(10);
        JButton btnNhap = new JButton("Nhập");
        chkSoAm = new JCheckBox("Cho nhập số âm");
        pnlNhap.add(txtNhap);
        pnlNhap.add(btnNhap);
        pnlNhap.add(chkSoAm);
        
        model = new DefaultListModel<>();
        dsSo = new JList<>(model);
        dsSo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pnlPhai.add(pnlNhap, BorderLayout.NORTH);
        pnlPhai.add(new JScrollPane(dsSo), BorderLayout.CENTER);

        // --- Panel dưới: Nút đóng ---
        JPanel pnlDuoi = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDong = new JButton("Đóng chương trình");
        pnlDuoi.add(btnDong);
        // panel tren de lam tieu de
        JPanel pnlTren = new JPanel(new BorderLayout());
        JLabel lblTieuDe = new JLabel("Tieu De Bai 7", SwingConstants.CENTER);
        pnlTren.add(lblTieuDe, BorderLayout.CENTER);
        add(pnlTren, BorderLayout.NORTH);
       

        // --- Gắn các panel vào cửa sổ ---
        add(pnlTrai, BorderLayout.WEST);
        add(pnlPhai, BorderLayout.CENTER);
        add(pnlDuoi, BorderLayout.SOUTH);

        // --- GÁN SỰ KIỆN VÀ XỬ LÝ TRỰC TIẾP ---

        // Sự kiện cho nút "Nhập"
        btnNhap.addActionListener(e -> {
            String input = txtNhap.getText().trim();
            if (input.isEmpty()) {
                return;
            }
            try {
                int so = Integer.parseInt(input);
                if (so < 0 && !chkSoAm.isSelected()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng check vào ô 'Cho nhập số âm' để nhập số âm.");
                    return;
                }
                model.addElement(so);
                txtNhap.setText("");
                txtNhap.requestFocus();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Sự kiện cho nút "Tô đen số chẵn"
        btnChan.addActionListener(e -> {
            List<Integer> viTriCanChon = new ArrayList<>();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i) % 2 == 0) {
                    viTriCanChon.add(i);
                }
            }
            int[] mangChon = viTriCanChon.stream().mapToInt(i -> i).toArray();
            dsSo.setSelectedIndices(mangChon);
        });

        // Sự kiện cho nút "Tô đen số lẻ"
        btnLe.addActionListener(e -> {
            List<Integer> viTriCanChon = new ArrayList<>();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i) % 2 != 0) {
                    viTriCanChon.add(i);
                }
            }
            int[] mangChon = viTriCanChon.stream().mapToInt(i -> i).toArray();
            dsSo.setSelectedIndices(mangChon);
        });
        
        // Sự kiện cho nút "Tô đen số nguyên tố"
        btnSNT.addActionListener(e -> {
            List<Integer> viTriCanChon = new ArrayList<>();
            for (int i = 0; i < model.getSize(); i++) {
                if (isPrime(model.getElementAt(i))) {
                    viTriCanChon.add(i);
                }
            }
            int[] mangChon = viTriCanChon.stream().mapToInt(i -> i).toArray();
            dsSo.setSelectedIndices(mangChon);
        });
        
        // Sự kiện cho nút "Bỏ tô đen"
        btnBoChon.addActionListener(e -> {
            dsSo.clearSelection();
        });
        
        // Sự kiện cho nút "Xóa các giá trị đang tô đen"
        btnXoa.addActionListener(e -> {
            List<Integer> dsGiaTriChon = dsSo.getSelectedValuesList();
            if (dsGiaTriChon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa có giá trị nào được chọn để xóa!");
                return;
            }
            for (Integer giaTri : dsGiaTriChon) {
                model.removeElement(giaTri);
            }
        });
        
        // Sự kiện cho nút "Tổng giá trị trong JList"
        btnTong.addActionListener(e -> {
            long tong = 0;
            for (int i = 0; i < model.getSize(); i++) {
                tong += model.getElementAt(i);
            }
            JOptionPane.showMessageDialog(this, "Tổng tất cả các giá trị trong JList là: " + tong, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Sự kiện cho nút "Đóng chương trình"
        btnDong.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đóng chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        // Bắt sự kiện nhấn nút X trên thanh tiêu đề của cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    // --- CÁC HÀM PHỤ (nếu cần) ---

    // Hàm kiểm tra số nguyên tố vẫn nên để riêng vì nó là một logic tái sử dụng
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // --- HÀM MAIN ĐỂ CHẠY CHƯƠNG TRÌNH ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai7 bai7 = new Bai7();
            bai7.setVisible(true);
        });
    }
}