/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.ui;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import poly.com.dao.PhieuMuonDAO;
import poly.com.model.PhieuMuon;

/**
 *
 * @author PC
 */
public class QuanLyPhieuMuon extends javax.swing.JFrame {

    ArrayList<PhieuMuon> lists;
    int current;
    public QuanLyPhieuMuon() {
        initComponents();
        setLocationRelativeTo(null);
        load();
        setStatus(true);
    }
    public void load(){
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        lists = pmdao.load();
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            model.setRowCount(0);
            for(PhieuMuon pm: lists){
                Object[] row = new Object[]{
                    pm.getMaPhieuMuon(),pm.getMaSV(),pm.getMaSach(),pm.getSoLuong(),pm.getNgayMuon(),pm.getNgayHenTra()
                };
                model.addRow(row);
            }
    }
    public void insert(){
        PhieuMuon pm =  new PhieuMuon();
        pm.setMaSV(txtMaSV.getText());
        pm.setMaSach(txtMaSach.getText());
        pm.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        PhieuMuonDAO pmdao =new PhieuMuonDAO() ;
        if (pmdao.insert(pm)>0 )
        {    
            JOptionPane.showMessageDialog(null, "Thêm Phiếu Mượn thành công");
        }
        else{
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin trong Phiếu Mượn");
        }     
    }
        public void update(){
        PhieuMuon pm =  new PhieuMuon();
        pm.setMaPhieuMuon(txtMaPhieuMuon.getText());
        pm.setMaSV(txtMaSV.getText());
        pm.setMaSach(txtMaSach.getText());
        pm.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        Date date = jDateChooser1.getDate();
        Date date1 = jDateChooser2.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dff = new SimpleDateFormat("yyyy-MM-dd").format(date);
        pm.setNgayMuon(df);
        pm.setNgayHenTra(dff);
        PhieuMuonDAO pmdao =new PhieuMuonDAO() ;
        if (pmdao.update(pm)>0 )
        {    
            JOptionPane.showMessageDialog(null, "cập nhật thành công");
        }
        else{
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }     
    }
    public void delete(){
        int index = tblBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Phiếu Mượn trong bảng để xóa", "Thông Báo", 1);
            return;
        }
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        
        int tk = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không");
      if (tk==JOptionPane.YES_OPTION)  
      {
        if (pmdao.delete(txtMaPhieuMuon.getText()))
        {
            JOptionPane.showMessageDialog(this, "Xóa Phiếu Mượn thành công", "Thông Báo", 1);
            
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống", "Thông Báo", 0);
        }
        
      }
      else
      {
          return;
          
      }
    }
    public void loadMaSV(){
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        lists = pmdao.SearchMaSV(txtSearch.getText());
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            model.setRowCount(0);
            for(PhieuMuon pm: lists){
                Object[] row = new Object[]{
                    pm.getMaPhieuMuon(),pm.getMaSV(),pm.getMaSach(),pm.getSoLuong(),pm.getNgayMuon(),pm.getNgayHenTra()
                };
                model.addRow(row);
            }
    }


    public void display(){
        PhieuMuon pm = lists.get(current);
        txtMaPhieuMuon.setText(pm.getMaPhieuMuon());
        txtMaSV.setText(pm.getMaSV());
        txtMaSach.setText(pm.getMaSach());
        txtSoLuong.setText(String.valueOf(pm.getSoLuong()));
        try {
            DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(current, 4));
            jDateChooser1.setDate(date1);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(current, 5));
            jDateChooser2.setDate(date2);
        } catch (ParseException ex) {   
        }
    }
    public void clear(){
        txtMaPhieuMuon.setText("");
        txtMaSV.setText("");
        txtMaSach.setText("");
        txtSoLuong.setText("");
        lblMaSV1.setText("");
        lblMaSach1.setText("");
        lblSoLuong1.setText("");
        setStatus(true);
    }
    public void setStatus(boolean insertable){
    btnThem.setEnabled(insertable);
    btnSua.setEnabled(!insertable);
    btnXoa.setEnabled(!insertable);
    }
    public void inphieumuon(){
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        pmdao.inphieumuon(Integer.parseInt(txtMaPhieuMuon.getText()));
    }
    public void indsphieumuon() throws JRException{
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        pmdao.indsphieumuon();
    }
    public boolean valiform() {
        if (txtMaSV.getText().equals("")) {
            lblMaSV1.setText("Chưa nhập Mã Sinh Viên");
            txtMaSV.requestFocus();
            return false;
        }else if (txtMaSach.getText().equals("")) {
            lblMaSach1.setText("Chưa nhập Mã Sách");
            txtMaSach.requestFocus();
            return false;
        }else if (txtSoLuong.getText().equals("")) {
            lblSoLuong1.setText("Chưa nhập Số Lượng");
            txtSoLuong.requestFocus();       
            return false;
        }else if (!(txtSoLuong.getText().matches("\\d{1,3}"))) {
            lblSoLuong1.setText("Số Lượng phải là số, lớn hơn 0");
            txtSoLuong.requestFocus();
            return false;
        }else if ((!(Integer.parseInt(txtSoLuong.getText())> 0))) {
            lblSoLuong1.setText("Số Lượng phải lớn hơn 0");
            txtSoLuong.requestFocus();
            return false;
        }else 
        return true;
    };
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtMaSV = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        lblSoLuong1 = new javax.swing.JLabel();
        lblMaSV1 = new javax.swing.JLabel();
        lblMaSach1 = new javax.swing.JLabel();
        btnTaoMoi = new javax.swing.JButton();
        btnQuanLy = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnPrintPM = new javax.swing.JButton();
        btnPrintDS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setSize(new java.awt.Dimension(1000, 650));

        jPanel3.setBackground(new java.awt.Color(197, 197, 197));
        jPanel3.setMinimumSize(new java.awt.Dimension(1000, 650));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 650));

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Mượn", "Mã SV", "Mã Sách", "Số Lượng", "Ngày Mượn", "Ngày Hẹn Trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.setRowHeight(30);
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBang);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtMaPhieuMuon.setEditable(false);
        txtMaPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuMuonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Mã Phiếu Mượn:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Mã Sinh Viên:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Mã Sách:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Số Lượng:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Ngày Mượn: ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Ngày Trả:");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        txtMaSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSVKeyReleased(evt);
            }
        });

        txtMaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSachKeyReleased(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        lblSoLuong1.setForeground(new java.awt.Color(255, 0, 0));

        lblMaSV1.setForeground(new java.awt.Color(255, 0, 0));

        lblMaSach1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSV1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(73, 73, 73)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 30, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(lblSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lblMaSV1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoLuong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        btnTaoMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Create.png"))); // NOI18N
        btnTaoMoi.setText("Tạo Mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnQuanLy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Home.png"))); // NOI18N
        btnQuanLy.setText("Quản Lý");
        btnQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Save.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Save as.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Quản Lý Phiếu Mượn");

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo mã SV, mã Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Search.png"))); // NOI18N

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnPrintPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrintPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Card file.png"))); // NOI18N
        btnPrintPM.setText("In PM");
        btnPrintPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPMActionPerformed(evt);
            }
        });

        btnPrintDS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrintDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/com/icon/Print.png"))); // NOI18N
        btnPrintDS.setText("In DS");
        btnPrintDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(btnPrintPM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btnPrintDS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintPM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintDS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        current = tblBang.getSelectedRow();
        display();
        setStatus(false);
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        clear();
        
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(valiform()==true){
            insert();
            load();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
        clear();
        load();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(valiform()==true){
            update();
            load();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnPrintPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPMActionPerformed
        int index = tblBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 Phiếu Mượn trong bảng để In", "Thông Báo", 1);
            return;
        }
        else{
        inphieumuon();
        }
        
    }//GEN-LAST:event_btnPrintPMActionPerformed

    private void btnPrintDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDSActionPerformed
        try {
            indsphieumuon();
        } catch (JRException ex) {
            Logger.getLogger(QuanLyPhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintDSActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadMaSV();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyActionPerformed
        QuanLyUI qlui = new QuanLyUI();
        qlui.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnQuanLyActionPerformed

    private void txtMaSVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSVKeyReleased
        if(!txtMaSV.getText().equals("")){
            lblMaSV1.setText(null);
        }
    }//GEN-LAST:event_txtMaSVKeyReleased

    private void txtMaSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSachKeyReleased
        if(!txtMaSach.getText().equals("")){
            lblMaSach1.setText(null);
        }
    }//GEN-LAST:event_txtMaSachKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        if(!txtSoLuong.getText().equals("")){
            lblSoLuong1.setText(null);
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtMaPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuMuonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyPhieuMuon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrintDS;
    private javax.swing.JButton btnPrintPM;
    private javax.swing.JButton btnQuanLy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaSV1;
    private javax.swing.JLabel lblMaSach1;
    private javax.swing.JLabel lblSoLuong1;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
