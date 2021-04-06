import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RezervariUser {
    public JPanel panel1;
    private JTable table1;
    private JButton logOutButton;
    private JButton backButton;
    private JButton anuleazaRezervareaButton;
    private JButton refreshButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;
    private Integer user1;

    public RezervariUser(JFrame frame, Integer user) {
        user1=user;
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new PageUser(frame,user).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        anuleazaRezervareaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call StergereRezervare(?);");

                        // remove selected row from the model
                        Object cursa = table1.getValueAt(table1.getSelectedRow(), 0);
                        Integer a = Integer.valueOf(cursa.toString());

                        s.setInt(1,a);
                        s.executeUpdate();


                        JOptionPane.showMessageDialog(null, "Rezervare anulata!");
                    }catch(SQLException f){
                        f.printStackTrace();
                    }
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new RezervariUser(frame,user).panel1);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 =new JTable();
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"idrezervare","idcursa","LocuriAchizitionate","Nume","Prenume","Adresa","CNP","NrTelefon","iduser","Bagaj"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[10];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            CallableStatement s = null;
            s = conn.prepareCall("call CautareDupaUser(?);");
            s.setInt(1,user1);
            s.execute();
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idrezervare");
                row[1]=rez.getInt("idcursa");
                row[2]=rez.getInt("LocuriAchizitionate");
                row[3]=rez.getString("Nume");
                row[4]=rez.getString("Prenume");
                row[5]=rez.getString("Adresa");
                row[6]=rez.getString("CNP");
                row[7]=rez.getString("nrTelefon");
                row[8]=rez.getInt("iduser");
                row[9]=rez.getBoolean("Bagaj");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
