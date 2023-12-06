/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gudang;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import koneksi.login;

/**
 *
 * @author User
 */
public class gudang extends javax.swing.JFrame {

    public String kap, kapasitas;
    static boolean maximixed = true;
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    public final Connection conn = new koneksi().connect();
    
    public void noTable(){
        int Baris = tabmode.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor +".",a,0);
        }
    }
    
    public void noTable2(){
        int Baris = tabmode2.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode2.setValueAt(nomor +".",a,0);
        }
    }
    
    protected void kosong(){
        txtNoPol.setText(null);
        cbJenis.setSelectedItem("-Pilih Jenis Truk-");
        txtKapasitas.setText(null);
        cbKet.setSelectedItem("-Pilih Keterangan-");
        tglAwal.setCalendar(null);
        tglAkhir.setCalendar(null);
        txtRute.setText(null);
    }
    
    public void dataTable(){
        Object[] Baris = {"No","No Polisi","Jenis Truk","Kapasitas Muatan","Keterangan","Dari Tanggal","Sampai Tanggal","Rute"};
        tabmode = new DefaultTableModel(null, Baris);
        tb_truk.setModel(tabmode);
        String sql = "select * from tb_unit_truk order by jenis_truk asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String no_pol = hasil.getString("no_pol");
                String jenis = hasil.getString("jenis_truk");
                String kapasitas = hasil.getString("kapasitas");
                String keterangan = hasil.getString("keterangan");
                String dari = hasil.getString("dari");
                String sampai = hasil.getString("sampai");
                String rute = hasil.getString("rute");
                String[] data = {"",no_pol,jenis,kapasitas,keterangan,dari,sampai,rute};
                tabmode.addRow(data);
                noTable();
            }
        } catch (Exception e){
        }
    }
    
    public void dataTable2(){
        Object[] Baris = {"No","ID Permintaan","No Polisi","Jenis Truk","Dari Tanggal","Sampai Tanggal","Rute","Keterangan","Tanggal Permintaan"};
        tabmode2 = new DefaultTableModel(null, Baris);
        tb_req.setModel(tabmode2);
        String sql = "select * from tb_req_truk order by keterangan asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id = hasil.getString("id_req");
                String no_pol = hasil.getString("no_pol");
                String jenis = hasil.getString("jenis_truk");
                String dari = hasil.getString("dari");
                String sampai = hasil.getString("sampai");
                String rute = hasil.getString("rute");
                String keterangan = hasil.getString("keterangan");
                String tgl = hasil.getString("tgl_req");
                String[] data = {"",id,no_pol,jenis,dari,sampai,rute,keterangan,tgl};
                tabmode2.addRow(data);
                noTable2();
            }
        } catch (Exception e){
        }
    }
    
    public void pencarian(String sql){
        Object[] Baris = {"No","ID Permintaan","No Polisi","Jenis Truk","Dari Tanggal","Sampai Tanggal","Rute","Keterangan","Tanggal Permintaan"};
        tabmode2 = new DefaultTableModel(null, Baris);
        tb_req.setModel(tabmode2);
        int brs = tb_req.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode2.removeRow(1);
        }
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id = hasil.getString("id_req");
                String no_pol = hasil.getString("no_pol");
                String jenis = hasil.getString("jenis_truk");
                String dari = hasil.getString("dari");
                String sampai = hasil.getString("sampai");
                String rute = hasil.getString("rute");
                String keterangan = hasil.getString("keterangan");
                String tgl = hasil.getString("tgl_req");
                String[] data = {"",id,no_pol,jenis,dari,sampai,rute,keterangan,tgl};
                tabmode2.addRow(data);
                noTable2();
            }
        } catch(Exception e){
        }
    }
    
    public gudang(String txtUsername) {
        initComponents();
        dataTable();
        dataTable2();
        jLabel3.setText(txtUsername);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnMin = new javax.swing.JButton();
        btnMax = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        menuPanel = new javax.swing.JPanel();
        btnData = new javax.swing.JButton();
        btnReq = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        contentPanelHead = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contentPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_truk = new javax.swing.JTable();
        labelKodePart = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNoPol = new javax.swing.JTextField();
        labelNamaPart = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKapasitas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        labelKategori = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelKeterangan = new javax.swing.JLabel();
        cbJenis = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelKeterangan1 = new javax.swing.JLabel();
        labelKeterangan2 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        cbKet = new javax.swing.JComboBox<>();
        btnUbah = new javax.swing.JButton();
        labelKategori2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtRute = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        contentPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_req = new javax.swing.JTable();
        btnTanggapi = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnRefresh1 = new javax.swing.JButton();
        txtDari = new javax.swing.JTextField();
        txtSampai = new javax.swing.JTextField();
        txtRute1 = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtNoPol1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(14, 78, 117));
        mainPanel.setPreferredSize(new java.awt.Dimension(1390, 829));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(187, 224, 255));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_4.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_6.png"))); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_30px.png"))); // NOI18N
        btnMin.setContentAreaFilled(false);
        btnMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_30px_1.png"))); // NOI18N
        btnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinActionPerformed(evt);
            }
        });

        btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Maximize_Window_30px.png"))); // NOI18N
        btnMax.setContentAreaFilled(false);
        btnMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMax.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Maximize_Window_30px_1.png"))); // NOI18N
        btnMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEM INFORMASI MONITORING TRUK PT KALIBARU JAYA ABADI");

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-log-out-32.png"))); // NOI18N
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeluar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-log-out-32 (1).png"))); // NOI18N
        btnKeluar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-log-out-32 (1).png"))); // NOI18N
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMax, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 60));

        menuPanel.setBackground(new java.awt.Color(0, 42, 71));

        btnData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnData.setText("DATA UNIT TRUK");
        btnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataActionPerformed(evt);
            }
        });

        btnReq.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReq.setText("PERMINTAAN UNIT TRUK");
        btnReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1206, 1206, 1206))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnData, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReq, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(751, Short.MAX_VALUE))
        );

        mainPanel.add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 200, 860));

        contentPanel.setBackground(new java.awt.Color(14, 78, 117));
        contentPanel.setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Hallo, ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout contentPanelHeadLayout = new javax.swing.GroupLayout(contentPanelHead);
        contentPanelHead.setLayout(contentPanelHeadLayout);
        contentPanelHeadLayout.setHorizontalGroup(
            contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(982, Short.MAX_VALUE))
        );
        contentPanelHeadLayout.setVerticalGroup(
            contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(715, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanelHead, "card3");

        contentPanel1.setBackground(new java.awt.Color(14, 78, 117));

        tb_truk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_truk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_trukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_truk);

        labelKodePart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKodePart.setForeground(new java.awt.Color(255, 255, 255));
        labelKodePart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKodePart.setText("No Polisi");
        labelKodePart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(":");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNoPol.setBackground(new java.awt.Color(33, 140, 193));
        txtNoPol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNoPol.setForeground(new java.awt.Color(255, 255, 255));
        txtNoPol.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNoPol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoPolKeyPressed(evt);
            }
        });

        labelNamaPart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNamaPart.setForeground(new java.awt.Color(255, 255, 255));
        labelNamaPart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNamaPart.setText("Jenis Truk");
        labelNamaPart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtKapasitas.setBackground(new java.awt.Color(33, 140, 193));
        txtKapasitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKapasitas.setForeground(new java.awt.Color(255, 255, 255));
        txtKapasitas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtKapasitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKapasitasKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(":");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKategori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori.setText("Kapasitas Muatan");
        labelKategori.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(":");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(":");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKeterangan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan.setText("Sampai Tanggal");
        labelKeterangan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cbJenis.setBackground(new java.awt.Color(33, 140, 193));
        cbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Jenis Truk-", "CDD Long", "Engkel Fuso", "Wingbox" }));
        cbJenis.setBorder(null);
        cbJenis.setOpaque(false);
        cbJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJenisActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(":");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText(":");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKeterangan1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan1.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan1.setText("Keterangan");
        labelKeterangan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKeterangan2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan2.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan2.setText("Dari Tanggal");
        labelKeterangan2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnSimpan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpan.setText("SIMPAN");
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        cbKet.setBackground(new java.awt.Color(33, 140, 193));
        cbKet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Keterangan-", "Tersedia", "Tidak Tersedia" }));
        cbKet.setBorder(null);
        cbKet.setOpaque(false);
        cbKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKetActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUbah.setText("UBAH");
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        labelKategori2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori2.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori2.setText("Rute");
        labelKategori2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(":");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtRute.setBackground(new java.awt.Color(33, 140, 193));
        txtRute.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRute.setForeground(new java.awt.Color(255, 255, 255));
        txtRute.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtRute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRuteKeyPressed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50.png"))); // NOI18N
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanel1Layout = new javax.swing.GroupLayout(contentPanel1);
        contentPanel1.setLayout(contentPanel1Layout);
        contentPanel1Layout.setHorizontalGroup(
            contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel1Layout.createSequentialGroup()
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addComponent(cbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addComponent(cbKet, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNoPol, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(txtKapasitas)
                                    .addComponent(txtRute))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh)
                                .addGap(63, 63, 63))))
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        contentPanel1Layout.setVerticalGroup(
            contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNoPol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanel1Layout.createSequentialGroup()
                                            .addComponent(labelKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5)
                                            .addComponent(labelNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbKet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelKategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(txtRute, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRefresh))))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanel1, "card2");

        contentPanel2.setBackground(new java.awt.Color(14, 78, 117));

        tb_req.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_req.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_reqMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_req);

        btnTanggapi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTanggapi.setText("TANGGAPI");
        btnTanggapi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTanggapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTanggapiActionPerformed(evt);
            }
        });

        txtCari.setBackground(new java.awt.Color(33, 140, 193));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(204, 204, 204));
        txtCari.setText("Ketik Untuk Cari");
        txtCari.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCariFocusLost(evt);
            }
        });
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        btnRefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50.png"))); // NOI18N
        btnRefresh1.setContentAreaFilled(false);
        btnRefresh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        txtDari.setBackground(new java.awt.Color(14, 78, 117));
        txtDari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDari.setForeground(new java.awt.Color(14, 78, 117));
        txtDari.setBorder(null);

        txtSampai.setBackground(new java.awt.Color(14, 78, 117));
        txtSampai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSampai.setForeground(new java.awt.Color(14, 78, 117));
        txtSampai.setBorder(null);

        txtRute1.setBackground(new java.awt.Color(14, 78, 117));
        txtRute1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRute1.setForeground(new java.awt.Color(14, 78, 117));
        txtRute1.setBorder(null);

        txtId.setBackground(new java.awt.Color(14, 78, 117));
        txtId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtId.setForeground(new java.awt.Color(14, 78, 117));
        txtId.setBorder(null);

        txtNoPol1.setBackground(new java.awt.Color(14, 78, 117));
        txtNoPol1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNoPol1.setForeground(new java.awt.Color(14, 78, 117));
        txtNoPol1.setBorder(null);

        javax.swing.GroupLayout contentPanel2Layout = new javax.swing.GroupLayout(contentPanel2);
        contentPanel2.setLayout(contentPanel2Layout);
        contentPanel2Layout.setHorizontalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnTanggapi, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRute1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(426, 426, 426)
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh1)))
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(txtNoPol1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        contentPanel2Layout.setVerticalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtNoPol1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTanggapi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel2Layout.createSequentialGroup()
                                .addComponent(txtDari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnRefresh1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanel2, "card4");

        mainPanel.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 1190, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1390, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1370, 829);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinActionPerformed

    private void btnMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaxActionPerformed
        if(maximixed){
            gudang.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gudang.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximixed = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximixed = true;
        }
    }//GEN-LAST:event_btnMaxActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        String ObjButton[] = {"YES","NO"};
        int pilihan = JOptionPane.showOptionDialog(null,"Apakah Anda yakin ingin keluar?","Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,ObjButton,ObjButton[1]);
        if(pilihan == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReqActionPerformed
        // TODO add your handling code here:
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(contentPanel2);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_btnReqActionPerformed

    private void txtNoPolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoPolKeyPressed
        
    }//GEN-LAST:event_txtNoPolKeyPressed

    private void txtKapasitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKapasitasKeyPressed
        
    }//GEN-LAST:event_txtKapasitasKeyPressed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        
    }//GEN-LAST:event_btnCloseMouseExited

    private void tb_trukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_trukMouseClicked
        int bar = tb_truk.getSelectedRow();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String h = tabmode.getValueAt(bar, 7).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        Date dateValue2 = null;
        try{
            dateValue = date.parse((String)tb_truk.getValueAt(bar, 5));
            dateValue2 = date.parse((String)tb_truk.getValueAt(bar, 6));
        } catch (ParseException ex){
            Logger.getLogger(gudang.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtNoPol.setText(b);
        cbJenis.setSelectedItem(c);
        txtKapasitas.setText(d);
        cbKet.setSelectedItem(e);
        tglAwal.setDate(dateValue);
        tglAkhir.setDate(dateValue2);
        txtRute.setText(h);
    }//GEN-LAST:event_tb_trukMouseClicked

    private void cbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJenisActionPerformed
        kap = (String) cbJenis.getSelectedItem();
        if(kap == "CDD Long"){
            kapasitas = "6 Ton/27 M³";
        }else if(kap == "Engkel Fuso"){
            kapasitas = "8 Ton/35-38 M³";
        }else{
            kapasitas = "12 Ton/45-48 M³";
        }
        
        txtKapasitas.setText(kapasitas);
    }//GEN-LAST:event_cbJenisActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(txtNoPol.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kode Barang tidak boleh kosong");
        }else{
            if(cbKet.getSelectedItem()=="Tidak Tersedia" || cbKet.getSelectedItem()=="Booked"){
                if(tglAkhir.getCalendar() == null || tglAwal.getCalendar()==null){
                    JOptionPane.showMessageDialog(null, "Tanggal tidak boleh kosong");
                }else{
                    String sql = "insert into tb_unit_truk values (?,?,?,?,?,?,?)";
                    String tampilan = "dd-MM-yyyy";
                    SimpleDateFormat fm = new SimpleDateFormat(tampilan);
                    String tanggal = String.valueOf(fm.format(tglAwal.getDate()));
                    String tanggal2 = String.valueOf(fm.format(tglAkhir.getDate()));
                    try {
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtNoPol.getText());
                        stat.setString(2, cbJenis.getSelectedItem().toString());
                        stat.setString(3, txtKapasitas.getText());
                        stat.setString(4, cbKet.getSelectedItem().toString());
                        stat.setString(5, tanggal.toString());
                        stat.setString(6, tanggal2.toString());
                        stat.setString(7, txtRute.getText());
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
                        kosong();
                        dataTable();
                        txtNoPol.requestFocus();
                    }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                String sql = "insert into tb_unit_truk values (?,?,?,?,?,?,?)";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.setString(1, txtNoPol.getText());
                    stat.setString(2, cbJenis.getSelectedItem().toString());
                    stat.setString(3, txtKapasitas.getText());
                    stat.setString(4, cbKet.getSelectedItem().toString());
                    stat.setString(5, "-");
                    stat.setString(6, "-");
                    stat.setString(7, txtRute.getText());
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
                    kosong();
                    dataTable();
                    txtNoPol.requestFocus();
                }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin "
            + "Menghapus Data","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from tb_unit_truk where no_pol='"+txtNoPol.getText()+"'";
            try {
                PreparedStatement stat=conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                kosong();
                dataTable();
                txtNoPol.requestFocus();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void cbKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKetActionPerformed
        if(cbKet.getSelectedItem()=="Tersedia"){
            tglAwal.setCalendar(null);
            tglAkhir.setCalendar(null);
            txtRute.setText("");
        }
    }//GEN-LAST:event_cbKetActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if(cbKet.getSelectedItem()=="Tidak Tersedia"){
                if(tglAkhir.getCalendar() == null || tglAwal.getCalendar()==null){
                    JOptionPane.showMessageDialog(null, "Tanggal tidak boleh kosong");
                }else{
                    String sql = "update tb_unit_truk set no_pol=?,jenis_truk=?,kapasitas=?,keterangan=?,dari=?,sampai=?,rute=? where no_pol='"+txtNoPol.getText()+"'";
                    String tampilan = "dd-MM-yyyy";
                    SimpleDateFormat fm = new SimpleDateFormat(tampilan);
                    String tanggal = String.valueOf(fm.format(tglAwal.getDate()));
                    String tanggal2 = String.valueOf(fm.format(tglAkhir.getDate()));
                    try {
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtNoPol.getText());
                        stat.setString(2, cbJenis.getSelectedItem().toString());
                        stat.setString(3, txtKapasitas.getText());
                        stat.setString(4, cbKet.getSelectedItem().toString());
                        stat.setString(5, tanggal.toString());
                        stat.setString(6, tanggal2.toString());
                        stat.setString(7, txtRute.getText());
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
                        kosong();
                        dataTable();
                        txtNoPol.requestFocus();
                    }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui"+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
            String sql = "update tb_unit_truk set no_pol=?,jenis_truk=?,kapasitas=?,keterangan=?,dari=?,sampai=?,rute=? where no_pol='"+txtNoPol.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtNoPol.getText());
                stat.setString(2, cbJenis.getSelectedItem().toString());
                stat.setString(3, txtKapasitas.getText());
                stat.setString(4, cbKet.getSelectedItem().toString());
                stat.setString(5, "-");
                stat.setString(6, "-");
                stat.setString(7, txtRute.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                kosong();
                dataTable();
                txtNoPol.requestFocus();
            } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbarui "+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataActionPerformed
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(contentPanel1);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_btnDataActionPerformed

    private void tb_reqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_reqMouseClicked
        // TODO add your handling code here:
        int bar = tb_req.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 1).toString();
        String b = tabmode2.getValueAt(bar, 2).toString();
        String c = tabmode2.getValueAt(bar, 4).toString();
        String d = tabmode2.getValueAt(bar, 5).toString();
        String e = tabmode2.getValueAt(bar, 6).toString();
        
        txtId.setText(a);
        txtNoPol1.setText(b);
        txtDari.setText(c);
        txtSampai.setText(d);
        txtRute1.setText(e);
    }//GEN-LAST:event_tb_reqMouseClicked

    private void btnTanggapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTanggapiActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "update tb_req_truk set keterangan='Sudah Ditanggapi' where id_req='"+txtId.getText()+"'";
            String sqli = "update tb_unit_truk set dari = '"+txtDari.getText()+"', sampai = "
                    + "'"+txtSampai.getText()+"', keterangan='Tidak Tersedia', rute = '"+txtRute1.getText()+"' "
                    + "where no_pol = '"+txtNoPol1.getText()+"'";
            PreparedStatement stat = conn.prepareStatement(sqli);
            PreparedStatement st = conn.prepareStatement(sql);
            st.execute();
            stat.execute();
            JOptionPane.showMessageDialog(rootPane, "Berhasil Menanggapi Permintaan");
            dataTable2();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Menanggapi "+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTanggapiActionPerformed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
        String sqlPencarian = "select * from tb_req_truk where id_req like '%"+txtCari.getText()
                +"%' or keterangan like '%"+txtCari.getText()+"%' or no_pol like '%"+txtCari.getText()+"%' or jenis_truk like '%"
                +txtCari.getText()+"%' or rute like '%"+txtCari.getText()+"%'";
        pencarian(sqlPencarian);
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtCariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariFocusGained
        // TODO add your handling code here:
        if(txtCari.getText().equals("Ketik Untuk Cari")){
            txtCari.setText("");
            txtCari.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtCariFocusGained

    private void txtCariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCariFocusLost
        // TODO add your handling code here:
        if(txtCari.getText().equals("")){
            txtCari.setText("Ketik Untuk Cari");
            txtCari.setForeground(new Color(204,204,204));
        }
    }//GEN-LAST:event_txtCariFocusLost

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        login lg = new login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtRuteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRuteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuteKeyPressed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        dataTable();
        kosong();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        dataTable2();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new gudang().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnMax;
    private javax.swing.JButton btnMin;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnReq;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTanggapi;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbJenis;
    private javax.swing.JComboBox<String> cbKet;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel contentPanel1;
    private javax.swing.JPanel contentPanel2;
    private javax.swing.JPanel contentPanelHead;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelKategori;
    private javax.swing.JLabel labelKategori2;
    private javax.swing.JLabel labelKeterangan;
    private javax.swing.JLabel labelKeterangan1;
    private javax.swing.JLabel labelKeterangan2;
    private javax.swing.JLabel labelKodePart;
    private javax.swing.JLabel labelNamaPart;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTable tb_req;
    private javax.swing.JTable tb_truk;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtDari;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKapasitas;
    private javax.swing.JTextField txtNoPol;
    private javax.swing.JTextField txtNoPol1;
    private javax.swing.JTextField txtRute;
    private javax.swing.JTextField txtRute1;
    private javax.swing.JTextField txtSampai;
    // End of variables declaration//GEN-END:variables
}
