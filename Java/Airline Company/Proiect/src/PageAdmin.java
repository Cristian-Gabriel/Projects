import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class PageAdmin {
    public JPanel panel1;
    private JButton adaugaCursaButton;
    private JButton logOutButton;
    private JTable table1;
    private JButton adaugaAvionButton;
    private JButton adaugaAeroportButton;
    private JButton stergeCursaButton;
    private JButton utilizatoriButton;
    private JButton rezervariButton;
    private JButton avioaneButton;
    private JButton aeroporturiButton;
    private JButton pilotiButton;
    private JButton refreshButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;
    private Integer a;

    public PageAdmin(JFrame frame) {
        adaugaCursaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new AddCursa(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new LogIn(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        stergeCursaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call StergereDinTabelCursa(?);");

                        // remove selected row from the model
                        Object cursa = table1.getValueAt(table1.getSelectedRow(), 0);
                        a = Integer.valueOf(cursa.toString());

                        s.setInt(1,a);
                        s.executeUpdate();


                        JOptionPane.showMessageDialog(null, "Cursa stearsa!");
                    }catch(SQLException f){
                        f.printStackTrace();
                    }
                }
            }
        });
        utilizatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new DeleteUser(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        rezervariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new DeleteRezervare(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        avioaneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new Avioane(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        aeroporturiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new Aeroporturi(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        pilotiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new Piloti(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new PageAdmin(frame).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
    }

    private java.sql.Date convertDateToSql(java.util.Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1=new JTable();
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","OrasPlecare","OrasDestinatie","OraPlecare","OraDestinatie","DataPlecarii","Aeroport","LocuriDisponibile","Pret"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[9];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            s=conn.createStatement();
            s.execute("SELECT * FROM cursa, orar, aeroport where cursa.idcursa=orar.idcursa and orar.idaeroport=aeroport.idaeroport;");
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idcursa");
                row[1]=rez.getString("OrasPlecare");
                row[2]=rez.getString("OrasDestinatie");
                row[3]=rez.getTime("OraPlecare");
                row[4]=rez.getTime("OraDestinatie");
                row[5]=rez.getDate("DATA");
                row[6]=rez.getString("Nume");
                row[7]=rez.getInt("LocuriRamase");
                row[8]=rez.getInt("pret");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
