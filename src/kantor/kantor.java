/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kantor;

import gudang.gudang;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import koneksi.koneksi;
import koneksi.login;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author User
 */
public class kantor extends javax.swing.JFrame {

    static boolean maximixed = true;
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    private DefaultTableModel tabmode3;
    private DefaultTableModel tabmode4;
    public final Connection conn = new koneksi().connect();
    
    JasperReport jr;
    JasperDesign jd;
    JasperPrint jp;
    
    HashMap params = new HashMap();
    FileInputStream reportStream;
            
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
    
    public void noTable3(){
        int Baris = tabmode3.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode3.setValueAt(nomor +".",a,0);
        }
    }
    
    public void noTable4(){
        int Baris = tabmode4.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode4.setValueAt(nomor +".",a,0);
        }
    }
    
    public void auto_id(){
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select MAX(RIGHT(id_req,3)) as AN from tb_req_truk");
            while(rs.next()){
                if(rs.first()==false){
                    txtId.setText("REQ001");
                }else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String AN = String.valueOf(set_id);
                    int next_id = AN.length();
                    for(int i=0;i<4-next_id;i++){
                        AN = "0"+AN;
                    }
                    txtId.setText("REQ"+AN);
                }
            }
        }catch(Exception e){
            txtId.setText("REQ001");
        }
    }
    
    public void auto_id_admin(){
        try{
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select MAX(RIGHT(id,3)) as AN from tb_user");
            while(rs.next()){
                if(rs.first()==false){
                    txtIdKry.setText("ADM001");
                }else{
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String AN = String.valueOf(set_id);
                    int next_id = AN.length();
                    for(int i=0;i<3-next_id;i++){
                        AN = "0"+AN;
                    }
                    txtIdKry.setText("ADM"+AN);
                }
            }
        }catch(Exception e){
            txtIdKry.setText("ADM001");
        }
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
        tbReq.setModel(tabmode2);
        String sql = "select * from tb_req_truk order by tgl_req asc";
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
    
    public void dataTable3(){
        Object[] Baris = {"No","ID Admin","Username","Password","Posisi Admin"};
        tabmode3 = new DefaultTableModel(null, Baris);
        tbAkun.setModel(tabmode3);
        String sql = "select * from tb_user order by id asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String id = hasil.getString("id");
                String user = hasil.getString("username");
                String pass = hasil.getString("password");
                String level = hasil.getString("level");
                String[] data = {"",id,user,pass,level};
                tabmode3.addRow(data);
                noTable3();
            }
        } catch (Exception e){
        }
    }
    
    public void dataTable4(){
        Object[] Baris = {"ID Permintaan","No Polisi","Jenis Truk","Dari Tanggal","Sampai Tanggal","Rute","Tanggal Permintaan"};
        tabmode4 = new DefaultTableModel(null, Baris);
        tbTransaksi.setModel(tabmode4);
        
    }
    
    public void tanggal(){
        Date tgl = new Date();
        tglReq.setDate(tgl);
    }
    
    public void clear_table(){
        int clear = tabmode4.getRowCount();
        for(int i=clear-1; i>=0; i--){
            tabmode4.removeRow(i);
        }
    }
    
    protected void kosong(){
        txtNoPol.setText(null);
        txtRute.setText(null);
        tglAwal.setCalendar(null);
        tglAkhir.setCalendar(null);
        txtJenis.setText(null);
        txtAn.setText(null);
        clear_table();
    }
    
    protected void clear_akun(){
        txtUsername.setText(null);
        txtPass.setText(null);
        cbPosisi.setSelectedItem("-Pilih Posisi Admin-");
    }
    
    public kantor(String txtUsername) {
        initComponents();
        auto_id();
        auto_id_admin();
        dataTable();
        dataTable2();
        dataTable3();
        dataTable4();
        tanggal();
        jLabel6.setText(txtUsername);
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
        btnReqs = new javax.swing.JButton();
        btnAkun = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        contentPanelHead = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contentPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_truk = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNoPol = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        labelKodePart1 = new javax.swing.JLabel();
        labelKategori1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtJenis = new javax.swing.JTextField();
        tglAwal = new com.toedter.calendar.JDateChooser();
        labelKeterangan2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tglAkhir = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        labelKeterangan = new javax.swing.JLabel();
        labelKeterangan1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tglReq = new com.toedter.calendar.JDateChooser();
        labelKategori2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtRute = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnRefresh1 = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        btnReq = new javax.swing.JButton();
        btnPrintTransaksi = new javax.swing.JButton();
        labelKeterangan5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtAn = new javax.swing.JTextField();
        labelKategori8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtKet = new javax.swing.JTextField();
        contentPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReq = new javax.swing.JTable();
        labelKeterangan3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tglAwal1 = new com.toedter.calendar.JDateChooser();
        tglAkhir1 = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        labelKeterangan4 = new javax.swing.JLabel();
        labelKategori3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtRute1 = new javax.swing.JTextField();
        btnUbah1 = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtIdReq = new javax.swing.JTextField();
        btnRefresh2 = new javax.swing.JButton();
        btnPrint1 = new javax.swing.JButton();
        contentPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAkun = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        labelKategori4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        labelKategori5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        labelKategori6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        cbPosisi = new javax.swing.JComboBox<>();
        btnEdit = new javax.swing.JButton();
        labelKategori7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIdKry = new javax.swing.JTextField();
        btnRefresh3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(14, 78, 117));
        mainPanel.setPreferredSize(new java.awt.Dimension(1390, 829));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(187, 224, 255));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_4.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_6.png"))); // NOI18N
        btnClose.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_6.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_30px.png"))); // NOI18N
        btnMin.setContentAreaFilled(false);
        btnMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_30px_1.png"))); // NOI18N
        btnMin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_30px_1.png"))); // NOI18N
        btnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinActionPerformed(evt);
            }
        });

        btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Maximize_Window_30px.png"))); // NOI18N
        btnMax.setContentAreaFilled(false);
        btnMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMax.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Maximize_Window_30px_1.png"))); // NOI18N
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
                .addGap(55, 55, 55)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
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
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        menuPanel.setBackground(new java.awt.Color(0, 42, 71));

        btnData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnData.setText("DATA UNIT TRUK");
        btnData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataActionPerformed(evt);
            }
        });

        btnReqs.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReqs.setText("DATA PERMINTAAN");
        btnReqs.setToolTipText("");
        btnReqs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReqsActionPerformed(evt);
            }
        });

        btnAkun.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAkun.setText("DATA AKUN");
        btnAkun.setToolTipText("");
        btnAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAkunActionPerformed(evt);
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
                    .addComponent(btnReqs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnData, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReqs, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(699, Short.MAX_VALUE))
        );

        mainPanel.add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 200, 860));

        contentPanel.setBackground(new java.awt.Color(14, 78, 117));
        contentPanel.setLayout(new java.awt.CardLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Hallo, ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout contentPanelHeadLayout = new javax.swing.GroupLayout(contentPanelHead);
        contentPanelHead.setLayout(contentPanelHeadLayout);
        contentPanelHeadLayout.setHorizontalGroup(
            contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(982, Short.MAX_VALUE))
        );
        contentPanelHeadLayout.setVerticalGroup(
            contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATA UNIT TRUK");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BUAT PERMINTAAN UNIT TRUK");

        txtNoPol.setBackground(new java.awt.Color(33, 140, 193));
        txtNoPol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNoPol.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNoPol.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(":");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKodePart1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKodePart1.setForeground(new java.awt.Color(255, 255, 255));
        labelKodePart1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKodePart1.setText("No Polisi");
        labelKodePart1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKategori1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori1.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori1.setText("Jenis Truk");
        labelKategori1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(":");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtJenis.setBackground(new java.awt.Color(33, 140, 193));
        txtJenis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtJenis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtJenis.setEnabled(false);

        tglAwal.setBackground(new java.awt.Color(33, 140, 193));
        tglAwal.setDateFormatString("dd-MM-yyyy");
        tglAwal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelKeterangan2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan2.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan2.setText("Dari Tanggal");
        labelKeterangan2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(":");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tglAkhir.setBackground(new java.awt.Color(33, 140, 193));
        tglAkhir.setDateFormatString("dd-MM-yyyy");
        tglAkhir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(":");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKeterangan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan.setText("Sampai Tanggal");
        labelKeterangan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKeterangan1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan1.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan1.setText("Tanggal Permintaan");
        labelKeterangan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText(":");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tglReq.setBackground(new java.awt.Color(33, 140, 193));
        tglReq.setDateFormatString("dd-MM-yyyy");
        tglReq.setEnabled(false);
        tglReq.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelKategori2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori2.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori2.setText("Untuk Rute");
        labelKategori2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(":");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtRute.setBackground(new java.awt.Color(33, 140, 193));
        txtRute.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRute.setForeground(new java.awt.Color(255, 255, 255));
        txtRute.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnTambah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTambah.setText("TAMBAH DATA");
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        txtId.setBackground(new java.awt.Color(33, 140, 193));
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
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

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48.png"))); // NOI18N
        btnPrint.setContentAreaFilled(false);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrint.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DAFTAR TRANSAKSI");

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane4.setViewportView(tbTransaksi);

        btnReq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReq.setText("BUAT PERMINTAAN");
        btnReq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReqActionPerformed(evt);
            }
        });

        btnPrintTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48.png"))); // NOI18N
        btnPrintTransaksi.setContentAreaFilled(false);
        btnPrintTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintTransaksi.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrintTransaksi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrintTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintTransaksiActionPerformed(evt);
            }
        });

        labelKeterangan5.setBackground(new java.awt.Color(0, 0, 0));
        labelKeterangan5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan5.setText("A/n");
        labelKeterangan5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        txtAn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAn.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelKeterangan5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator4)
                    .addComponent(txtAn, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnReq, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrintTransaksi)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrintTransaksi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelKeterangan5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAn, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReq, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        labelKategori8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori8.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori8.setText("ID Permintaan");
        labelKategori8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText(":");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtKet.setBackground(new java.awt.Color(14, 78, 117));
        txtKet.setForeground(new java.awt.Color(14, 78, 117));
        txtKet.setBorder(null);
        txtKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanel1Layout = new javax.swing.GroupLayout(contentPanel1);
        contentPanel1.setLayout(contentPanel1Layout);
        contentPanel1Layout.setHorizontalGroup(
            contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh1)
                        .addGap(128, 128, 128))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(contentPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addComponent(labelKeterangan1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tglReq, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tglAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(tglAwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelKodePart1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtJenis, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtNoPol)))))
                    .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtRute, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPanel1Layout.createSequentialGroup()
                            .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtKet, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPanel1Layout.createSequentialGroup()
                                    .addComponent(labelKategori8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(12, 12, 12)
                            .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        contentPanel1Layout.setVerticalGroup(
            contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint)
                    .addComponent(btnRefresh1)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tglReq, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelKodePart1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNoPol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addComponent(labelKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel1Layout.createSequentialGroup()
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tglAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tglAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelKategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRute, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKategori8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(contentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        contentPanel.add(contentPanel1, "card2");

        contentPanel2.setBackground(new java.awt.Color(14, 78, 117));

        tbReq.setModel(new javax.swing.table.DefaultTableModel(
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
        tbReq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReqMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbReq);

        labelKeterangan3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan3.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan3.setText("Dari Tanggal");
        labelKeterangan3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(":");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tglAwal1.setBackground(new java.awt.Color(33, 140, 193));
        tglAwal1.setDateFormatString("dd-MM-yyyy");
        tglAwal1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tglAkhir1.setBackground(new java.awt.Color(33, 140, 193));
        tglAkhir1.setDateFormatString("dd-MM-yyyy");
        tglAkhir1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(":");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKeterangan4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan4.setForeground(new java.awt.Color(255, 255, 255));
        labelKeterangan4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan4.setText("Sampai Tanggal");
        labelKeterangan4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKategori3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori3.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori3.setText("Untuk Rute");
        labelKategori3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(":");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtRute1.setBackground(new java.awt.Color(33, 140, 193));
        txtRute1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRute1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnUbah1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUbah1.setText("UBAH");
        btnUbah1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbah1ActionPerformed(evt);
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

        txtIdReq.setBackground(new java.awt.Color(14, 78, 117));
        txtIdReq.setForeground(new java.awt.Color(14, 78, 117));
        txtIdReq.setBorder(null);

        btnRefresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50.png"))); // NOI18N
        btnRefresh2.setContentAreaFilled(false);
        btnRefresh2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh2ActionPerformed(evt);
            }
        });

        btnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48.png"))); // NOI18N
        btnPrint1.setContentAreaFilled(false);
        btnPrint1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrint1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-print-48 (1).png"))); // NOI18N
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanel2Layout = new javax.swing.GroupLayout(contentPanel2);
        contentPanel2.setLayout(contentPanel2Layout);
        contentPanel2Layout.setHorizontalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdReq, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 66, Short.MAX_VALUE))
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelKeterangan3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKeterangan4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKategori3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tglAkhir1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tglAwal1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtRute1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUbah1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint1)
                .addGap(42, 42, 42)
                .addComponent(btnRefresh2)
                .addGap(90, 90, 90))
        );
        contentPanel2Layout.setVerticalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel2Layout.createSequentialGroup()
                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanel2Layout.createSequentialGroup()
                                .addComponent(labelKeterangan3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelKeterangan4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel2Layout.createSequentialGroup()
                                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contentPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tglAwal1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tglAkhir1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contentPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelKategori3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRute1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(contentPanel2Layout.createSequentialGroup()
                                .addComponent(btnUbah1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(contentPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrint1)
                            .addComponent(btnRefresh2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdReq, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanel2, "card3");

        contentPanel3.setBackground(new java.awt.Color(14, 78, 117));

        tbAkun.setModel(new javax.swing.table.DefaultTableModel(
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
        tbAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAkunMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbAkun);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATA AKUN");

        labelKategori4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori4.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori4.setText("Posisi");
        labelKategori4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText(":");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setText("HAPUS");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setText("TAMBAH");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        labelKategori5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori5.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori5.setText("Password");
        labelKategori5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText(":");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtPass.setBackground(new java.awt.Color(33, 140, 193));
        txtPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelKategori6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori6.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori6.setText("Username");
        labelKategori6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(":");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtUsername.setBackground(new java.awt.Color(33, 140, 193));
        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbPosisi.setBackground(new java.awt.Color(33, 140, 193));
        cbPosisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Posisi Admin-", "Admin Gudang", "Admin Kantor" }));
        cbPosisi.setBorder(null);
        cbPosisi.setOpaque(false);

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEdit.setText("UBAH");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        labelKategori7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori7.setForeground(new java.awt.Color(255, 255, 255));
        labelKategori7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori7.setText("ID Admin");
        labelKategori7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText(":");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdKry.setBackground(new java.awt.Color(33, 140, 193));
        txtIdKry.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdKry.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtIdKry.setEnabled(false);

        btnRefresh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50.png"))); // NOI18N
        btnRefresh3.setContentAreaFilled(false);
        btnRefresh3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-update-left-rotation-50 (1).png"))); // NOI18N
        btnRefresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanel3Layout = new javax.swing.GroupLayout(contentPanel3);
        contentPanel3.setLayout(contentPanel3Layout);
        contentPanel3Layout.setHorizontalGroup(
            contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261)
                .addComponent(btnRefresh3)
                .addGap(107, 107, 107))
            .addGroup(contentPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanel3Layout.createSequentialGroup()
                        .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanel3Layout.createSequentialGroup()
                                .addComponent(labelKategori4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbPosisi, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel3Layout.createSequentialGroup()
                                .addComponent(labelKategori5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel3Layout.createSequentialGroup()
                                .addComponent(labelKategori6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPanel3Layout.createSequentialGroup()
                                .addComponent(labelKategori7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdKry, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        contentPanel3Layout.setVerticalGroup(
            contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanel3Layout.createSequentialGroup()
                .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRefresh3)
                        .addGap(11, 11, 11)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentPanel3Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(contentPanel3Layout.createSequentialGroup()
                        .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKategori7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdKry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKategori6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKategori5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(contentPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKategori4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPosisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(285, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanel3, "card5");

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
            kantor.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            kantor.this.setMaximizedBounds(env.getMaximumWindowBounds());
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

    private void btnReqsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReqsActionPerformed
        // TODO add your handling code here:
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(contentPanel2);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_btnReqsActionPerformed

    private void tb_trukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_trukMouseClicked
        // TODO add your handling code here:
        int bar = tb_truk.getSelectedRow();
        String a = tabmode.getValueAt(bar, 1).toString();
        String b = tabmode.getValueAt(bar, 2).toString();
        String c = tabmode.getValueAt(bar, 4).toString();
        String d = tabmode.getValueAt(bar, 7).toString();
        
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        Date dateValue2 = null;
        try{
            dateValue = date.parse((String)tb_truk.getValueAt(bar, 5));
            dateValue2 = date.parse((String)tb_truk.getValueAt(bar, 6));
        } catch (ParseException ex){
            Logger.getLogger(kantor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtNoPol.setText(a);
        txtJenis.setText(b);
        txtKet.setText(c);
        txtRute.setText(d);
        tglAwal.setDate(dateValue);
        tglAkhir.setDate(dateValue2);
        

    }//GEN-LAST:event_tb_trukMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if(txtKet.getText().equals("Tidak Tersedia")){
            JOptionPane.showMessageDialog(rootPane, "Unit Truk Tidak Tersedia", "Error Message", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
            String sql = "select*from tb_req_truk where no_pol = '"+txtNoPol.getText()+"' AND keterangan = 'Belum Ditanggapi'";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.first());
            if(rs.first()==true){
                JOptionPane.showMessageDialog(rootPane, "Truk Tidak Tersedia Atau Sudah Ada Pada Data Permintaan", "Error Message", JOptionPane.ERROR_MESSAGE);
            }else{
                String s = "";
                boolean exists = false;
                for (int i=0; i<tabmode4.getRowCount(); i++){    
                    s = tabmode4.getValueAt(i, 1).toString();
                    if(txtNoPol.getText().equals(s)){
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    String tampilan = "dd-MM-yyyy";
                    SimpleDateFormat fm = new SimpleDateFormat(tampilan);
                    String tanggal = String.valueOf(fm.format(tglReq.getDate()));
                    String tanggal1 = String.valueOf(fm.format(tglAwal.getDate()));
                    String tanggal2 = String.valueOf(fm.format(tglAkhir.getDate()));
                    tabmode4.addRow(new Object[]{
                        txtId.getText(),txtNoPol.getText(),txtJenis.getText(),tanggal1.toString(),tanggal2.toString(),txtRute.getText(),tanggal.toString()
                    });
                }else{
                    JOptionPane.showMessageDialog(null, "Data Sudah Ada Pada Daftar Transaksi","Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){   
            JOptionPane.showMessageDialog(rootPane, "Input Error"+e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAkunActionPerformed
        // TODO add your handling code here:
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(contentPanel3);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_btnAkunActionPerformed

    private void btnDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataActionPerformed
        // TODO add your handling code here:
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.revalidate();
        
        contentPanel.add(contentPanel1);
        contentPanel.repaint();
        contentPanel.revalidate();
    }//GEN-LAST:event_btnDataActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin "
            + "Menghapus Data","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from tb_user where id='"+txtIdKry.getText()+"'";
            try {
                PreparedStatement stat=conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                clear_akun();
                dataTable3();
                auto_id_admin();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String sql = "insert into tb_user values (?,?,?,?)";
                    try {
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtIdKry.getText());
                        stat.setString(2, txtUsername.getText());
                        stat.setString(3, txtPass.getText());
                        stat.setString(4, cbPosisi.getSelectedItem().toString());
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
                        clear_akun();
                        dataTable3();
                        auto_id_admin();
                    }catch (SQLException e){
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e.getMessage());
                    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        String sql = "update tb_user set username=?,password=?,level=? where id='"+txtIdKry.getText()+"'";
                    try {
                        PreparedStatement stat = conn.prepareStatement(sql);
                        stat.setString(1, txtUsername.getText());
                        stat.setString(2, txtPass.getText());
                        stat.setString(3, cbPosisi.getSelectedItem().toString());
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
                        clear_akun();
                        dataTable3();
                        auto_id_admin();
                    }catch (SQLException e){
                        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e.getMessage());
                    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void tbAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAkunMouseClicked
        // TODO add your handling code here:
        int bar = tbAkun.getSelectedRow();
        String a = tabmode3.getValueAt(bar, 0).toString();
        String b = tabmode3.getValueAt(bar, 1).toString();
        String c = tabmode3.getValueAt(bar, 2).toString();
        String d = tabmode3.getValueAt(bar, 3).toString();
        String e = tabmode3.getValueAt(bar, 4).toString();

        txtIdKry.setText(b);
        txtUsername.setText(c);
        txtPass.setText(d);
        cbPosisi.setSelectedItem(e);
    }//GEN-LAST:event_tbAkunMouseClicked

    private void btnRefresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh3ActionPerformed
        // TODO add your handling code here:
        dataTable3();
        clear_akun();
        auto_id_admin();
    }//GEN-LAST:event_btnRefresh3ActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        dataTable();
        kosong();
        tanggal();
        auto_id();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        login lg = new login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
            FileInputStream reportStream = new FileInputStream(new File("src/laporan/reportDataTruk.jasper"));
            HashMap params = new HashMap();
            params.put("username", jLabel6.getText());
            JasperReport jr = (JasperReport) JRLoader.loadObject(reportStream);
    JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
    JasperViewer jReportsViewer = new JasperViewer(jp, false);
    jReportsViewer.setFitWidthZoomRatio();
    jReportsViewer.setVisible(true);
    reportStream.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membuka file print "+e.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnRefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh2ActionPerformed
        // TODO add your handling code here:
        dataTable2();
        tglAwal1.setCalendar(null);
        tglAkhir1.setCalendar(null);
        txtRute1.setText(null);
    }//GEN-LAST:event_btnRefresh2ActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin "
            + "Menghapus Data","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from tb_req_truk where id_req='"+txtIdReq.getText()+"'";
            try {
                PreparedStatement stat=conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                txtRute1.setText(null);
                tglAwal1.setCalendar(null);
                tglAkhir1.setCalendar(null);
                dataTable2();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbah1ActionPerformed
        // TODO add your handling code here:
        String sql = "update tb_req_truk set dari=?,sampai=?,rute=? where id_req='"+txtIdReq.getText()+"'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(tglAwal1.getDate()));
            String tanggal2 = String.valueOf(fm.format(tglAkhir1.getDate()));
            stat.setString(1, tanggal.toString());
            stat.setString(2, tanggal2.toString());
            stat.setString(3, txtRute1.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            dataTable2();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e.getMessage());
        }
    }//GEN-LAST:event_btnUbah1ActionPerformed

    private void tbReqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReqMouseClicked
        // TODO add your handling code here:
        int bar = tbReq.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 1).toString();
        String b = tabmode2.getValueAt(bar, 6).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        Date dateValue2 = null;
        try{
            dateValue = date.parse((String)tbReq.getValueAt(bar, 4));
            dateValue2 = date.parse((String)tbReq.getValueAt(bar, 5));
        } catch (ParseException ex){
            Logger.getLogger(gudang.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtIdReq.setText(a);
        tglAwal1.setDate(dateValue);
        tglAkhir1.setDate(dateValue2);
        txtRute1.setText(b);
    }//GEN-LAST:event_tbReqMouseClicked

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        // TODO add your handling code here:
        try {
            FileInputStream reportStream = new FileInputStream(new File("src/laporan/report1.jasper"));
            HashMap params = new HashMap();
            params.put("username", jLabel6.getText());
            JasperReport jr = (JasperReport) JRLoader.loadObject(reportStream);
    JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
    JasperViewer jReportsViewer = new JasperViewer(jp, false);
    jReportsViewer.setFitWidthZoomRatio();
    jReportsViewer.setVisible(true);
    reportStream.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membuka file print "+e.getMessage());
        }
    }//GEN-LAST:event_btnPrint1ActionPerformed

    private void btnReqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReqActionPerformed
        // TODO add your handling code here:
        Statement st;
        try{
            st = conn.createStatement();
            for(int i=0; i<tabmode4.getRowCount(); i++){
                String a = tabmode4.getValueAt(i, 0).toString();
                String b = tabmode4.getValueAt(i, 1).toString();
                String c = tabmode4.getValueAt(i, 2).toString();
                String d = tabmode4.getValueAt(i, 3).toString();
                String e = tabmode4.getValueAt(i, 4).toString();
                String f = tabmode4.getValueAt(i, 5).toString();
                String g = tabmode4.getValueAt(i, 6).toString();
                String h = "Belum Ditanggapi";
                
                String sqlQuery = "insert into tb_req_truk values ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+h+"','"+g+"')";
                st.addBatch(sqlQuery);
            }
            
            int[] rowInserted = st.executeBatch();
            System.out.println("rows inserted = "+rowInserted);
            JOptionPane.showMessageDialog(rootPane, "Permintaan Berhasil Dibuat");
            clear_table();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, "input error"+e.getMessage());
        }
        
        
//        String sql = "insert into tb_req_truk values (?,?,?,?,?,?,?,?)";
//        String tampilan = "dd-MM-yyyy";
//        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
//        String tanggal = String.valueOf(fm.format(tglReq.getDate()));
//        String tanggal1 = String.valueOf(fm.format(tglAwal.getDate()));
//        String tanggal2 = String.valueOf(fm.format(tglAkhir.getDate()));
//        try {
//            PreparedStatement stat = conn.prepareStatement(sql);
//            stat.setString(8, tanggal.toString());
//            stat.setString(2, txtNoPol.getText());
//            stat.setString(3, txtJenis.getText());
//            stat.setString(4, tanggal1.toString());
//            stat.setString(5, tanggal2.toString());
//            stat.setString(6, txtRute.getText());
//            stat.setString(1, txtId.getText());
//            stat.setString(7, "Belum Ditanggapi");
//            
//            stat.executeUpdate();
//            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
//            kosong();
//            dataTable();
//            auto_id();
//            tanggal();
//        }catch (SQLException e){
//                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e.getMessage());
//        }
    }//GEN-LAST:event_btnReqActionPerformed

    private void btnPrintTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintTransaksiActionPerformed
        // TODO add your handling code here:
//        try{
//            JRTableModelDataSource ds = new JRTableModelDataSource(tabmode4);
//            String rs = "src/laporan/reportTransaksi.jasper";
//            
//            JasperReport jr = JasperCompileManager.compileReport(rs);
//            
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("title1","ID Permintaan");
//            
//            JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
//            
//            JasperViewer.viewReport(jp, false);
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(rootPane, "Gagal Print "+e.getMessage());
//        }
        
        try {
            FileInputStream reportStream = new FileInputStream(new File("src/laporan/reportTr.jasper"));
            HashMap params = new HashMap();
            params.put("user", txtAn.getText());
            params.put("username", jLabel6.getText());
            JasperReport jr = (JasperReport) JRLoader.loadObject(reportStream);
            JRTableModelDataSource ds = new JRTableModelDataSource(tabmode4);
    JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
    JasperViewer jReportsViewer = new JasperViewer(jp, false);
    jReportsViewer.setFitWidthZoomRatio();
    jReportsViewer.setVisible(true);
    reportStream.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membuka file print "+e.getMessage());
        }
    }//GEN-LAST:event_btnPrintTransaksiActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKetActionPerformed

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
//            java.util.logging.Logger.getLogger(kantor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(kantor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(kantor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(kantor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new kantor().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAkun;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnData;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnMax;
    private javax.swing.JButton btnMin;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JButton btnPrintTransaksi;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnRefresh2;
    private javax.swing.JButton btnRefresh3;
    private javax.swing.JButton btnReq;
    private javax.swing.JButton btnReqs;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah1;
    private javax.swing.JComboBox<String> cbPosisi;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel contentPanel1;
    private javax.swing.JPanel contentPanel2;
    private javax.swing.JPanel contentPanel3;
    private javax.swing.JPanel contentPanelHead;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelKategori1;
    private javax.swing.JLabel labelKategori2;
    private javax.swing.JLabel labelKategori3;
    private javax.swing.JLabel labelKategori4;
    private javax.swing.JLabel labelKategori5;
    private javax.swing.JLabel labelKategori6;
    private javax.swing.JLabel labelKategori7;
    private javax.swing.JLabel labelKategori8;
    private javax.swing.JLabel labelKeterangan;
    private javax.swing.JLabel labelKeterangan1;
    private javax.swing.JLabel labelKeterangan2;
    private javax.swing.JLabel labelKeterangan3;
    private javax.swing.JLabel labelKeterangan4;
    private javax.swing.JLabel labelKeterangan5;
    private javax.swing.JLabel labelKodePart1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTable tbAkun;
    private javax.swing.JTable tbReq;
    public javax.swing.JTable tbTransaksi;
    private javax.swing.JTable tb_truk;
    private com.toedter.calendar.JDateChooser tglAkhir;
    private com.toedter.calendar.JDateChooser tglAkhir1;
    private com.toedter.calendar.JDateChooser tglAwal;
    private com.toedter.calendar.JDateChooser tglAwal1;
    private com.toedter.calendar.JDateChooser tglReq;
    private javax.swing.JTextField txtAn;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdKry;
    private javax.swing.JTextField txtIdReq;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtKet;
    private javax.swing.JTextField txtNoPol;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtRute;
    private javax.swing.JTextField txtRute1;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
