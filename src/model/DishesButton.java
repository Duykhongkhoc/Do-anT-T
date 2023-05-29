package model;

import connection.ExcuteSQLStatement;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import view.QuanLyThucDonJPanel;
import static view.QuanLyThucDonJPanel.*;

public final class DishesButton extends JButton {

    private String maMonAn;
    private String tenMon;
    private String link_img;
    private Object tenLoaiMonAn; // trong csdl ung voi MALMA
    private int donGia;
    private ArrayList<String> nguyenLieu = new ArrayList<>();

    public DishesButton(String link_img, String tenMon, Object tenLoaiMonAn, String maMonAn, int donGia, ArrayList<String> nguyenLieu) {
        try {
            setLink_img(link_img);
            ImageIcon dishIcon = new ImageIcon(getClass().getResource(getLink_img()));
            Image dishImage = dishIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            javax.swing.ImageIcon scaledDishIcon = new ImageIcon(dishImage);
            setIcon(scaledDishIcon);
        } catch (Exception e) {
            ImageIcon dishIcon = new ImageIcon(getLink_img());
            Image dishImage = dishIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            javax.swing.ImageIcon scaledDishIcon = new ImageIcon(dishImage);
            setIcon(scaledDishIcon);
        }
        setTenMon(tenMon);
        setTenLoaiMonAn(tenLoaiMonAn);
        setMaMonAn(maMonAn);
        setDonGia(donGia);
        setNguyenLieu(nguyenLieu);
        setIconTextGap(20);
        setBackground(Color.decode("#E9F7FF"));
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(BOTTOM);
        addActionListener(DishesButtonActionListener);
    }

    ActionListener DishesButtonActionListener = (ActionEvent e) -> {
        suaLoaiMonAn_jComboBox.setSelectedItem(tenLoaiMonAn);
        suaMaMonAn_jTextField.setText(this.getMaMonAn());
        suaTenMonAn_jTextField.setText(this.getTenMon());
        suaPathAnhMonAn_jTextField.setText(this.getLink_img());
        suaDonGia_jTextField.setText(String.valueOf(donGia));
        for (String str : nguyenLieu) {
            if (suaShowNguyenLieu_jTextArea.getText().equals("")) {
                suaShowNguyenLieu_jTextArea.setText(str);
            } else {
                suaShowNguyenLieu_jTextArea.setText(suaShowNguyenLieu_jTextArea.getText() + "\n" + str);
            }
        }
        String[] temp = suaShowNguyenLieu_jTextArea.getText().split("[\\n]");

        nguyenLieuSua = new ArrayList<>(Arrays.asList(temp));
        //Hien dialog sua de sua
        xoa_suaMonAn_jDialog.pack();
        xoa_suaMonAn_jDialog.setLocationRelativeTo(null);
        xoa_suaMonAn_jDialog.setVisible(true);
        //Xu ly

        switch (trangThaiChonXoaOrSua) {
            case 1 -> {
                suaThongTin();
                trangThaiChonXoaOrSua = 0;
                break;
            }
            default -> {
                trangThaiChonXoaOrSua = 0;
                break;
            }
        }
        //suaShowNguyenLieu phai o cuoi de thuc hien dung
        suaShowNguyenLieu_jTextArea.setText("");
    };

    public void suaThongTin() {
        String tenMonNew = suaTenMonAn_jTextField.getText();
        if (!tenMonNew.equals(getTenMon())) {
            ExcuteSQLStatement.ExcuteSQLUpdate("update MONAN set TENMON = '" + suaTenMonAn_jTextField.getText() + "' where MAMON = '" + getMaMonAn() + "'");
            setTenMon(tenMonNew);
        }

        String link_imgNew = suaPathAnhMonAn_jTextField.getText();
        if (!link_imgNew.equals(this.link_img)) {
            ExcuteSQLStatement.ExcuteSQLUpdate("update MONAN set LINK_IMAGE = '" + link_imgNew + "' where MAMON = '" + getMaMonAn() + "'");
            setLink_img(link_imgNew);
            try {
                ImageIcon dishIcon = new ImageIcon(getClass().getResource(link_imgNew));
                Image dishImage = dishIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledDishIcon = new ImageIcon(dishImage);
                setIcon(scaledDishIcon);
            } catch (Exception e) {
                ImageIcon dishIcon = new ImageIcon(link_imgNew);
                Image dishImage = dishIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledDishIcon = new ImageIcon(dishImage);
                setIcon(scaledDishIcon);
            }
        }
        // Thay doi loai mon an
        String tenLoaiMonAnNew = suaLoaiMonAn_jComboBox.getSelectedItem().toString();
        if (!tenLoaiMonAnNew.equals(getTenLoaiMonAn())) {
            String sql = "SELECT MALMA FROM LOAIMONAN where TENLMA ='" + tenLoaiMonAnNew + "'";
            ResultSet loaiMonAnResultSet = ExcuteSQLStatement.ExcuteSQLQuery(sql);
            String maLoaiMonAnNew;
            try {
                while (loaiMonAnResultSet.next()) {
                    maLoaiMonAnNew = loaiMonAnResultSet.getString("MALMA");
                    ExcuteSQLStatement.ExcuteSQLUpdate("update MONAN set MALMA = '" + maLoaiMonAnNew + "' where MAMON = '" + getMaMonAn() + "'");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyThucDonJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            setTenLoaiMonAn(suaLoaiMonAn_jComboBox.getSelectedItem());
            //Doan nay can xem lai de xem vi tri xuat hien
            for (JPanel loaiMonAnJPanel : loaiMonAnJPanels) {
                if (getTenLoaiMonAn().equals(loaiMonAnJPanel.getName())) {
                    loaiMonAnJPanel.add(this);
                }
            }
        }
        //Thay doi don gia
        int donGiaNew = Integer.parseInt(suaDonGia_jTextField.getText());
        if (donGiaNew != getDonGia()) {
            ExcuteSQLStatement.ExcuteSQLUpdate("update MONAN set DONGIA = " + donGiaNew + " where MAMON = '" + getMaMonAn() + "'");
            setDonGia(Integer.parseInt(suaDonGia_jTextField.getText()));
        }
        //Thay doi nguyen lieu
        
        System.out.println("getNguyen lieu:" + getNguyenLieu());
        System.out.println("nguyen lieu sua 2: "+nguyenLieuSua);
        if (!getNguyenLieu().equals(nguyenLieuSua)) {
            nguyenLieu.clear();
            for (String nguyenlieutemp : nguyenLieuSua) {
                nguyenLieu.add(nguyenlieutemp);
            }
            for (String tenNL : nguyenLieu) {
                String sqlStatementMaNguyenLieu = "select MANL from KHONGUYENLIEU where TENNL = '" + tenNL + "'";
                ResultSet maNguyenLieuResultSet = ExcuteSQLStatement.ExcuteSQLQuery(sqlStatementMaNguyenLieu);
                try {
                    while (maNguyenLieuResultSet.next()) {
                        String sqlStatementUpdateCheBien = "insert into CHEBIEN values ('" + getMaMonAn() + "', '" + maNguyenLieuResultSet.getString("MANL") + "')";
                        ExcuteSQLStatement.ExcuteSQLUpdate(sqlStatementUpdateCheBien);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyThucDonJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        nguyenLieuSua.clear();
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
        setText(this.tenMon);
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public Object getTenLoaiMonAn() {
        return tenLoaiMonAn;
    }

    public void setTenLoaiMonAn(Object tenLoaiMonAn) {
        this.tenLoaiMonAn = tenLoaiMonAn;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public ArrayList<String> getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(ArrayList<String> nguyenLieu) {
        this.nguyenLieu = new ArrayList<>(nguyenLieu);
    }
}
