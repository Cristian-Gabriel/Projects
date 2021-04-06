import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Piloti {
    private JTable table1;
    private JButton backButton;
    private JButton stergePilotButton;
    public JPanel panel1;
    private JButton adaugaPilotButton;
    private JButton refreshButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;


    public Piloti(JFrame frame) {
        backButton.addActionListener(new ActionListener() {
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
        stergePilotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call StergerePilot(?);");

                        // remove selected row from the model
                        Object cursa = table1.getValueAt(table1.getSelectedRow(), 0);
                        Integer a = Integer.valueOf(cursa.toString());

                        s.setInt(1,a);
                        s.executeUpdate();


                        JOptionPane.showMessageDialog(null, "Pilot sters!");
                    }catch(SQLException f){
                        f.printStackTrace();
                    }
                }
            }
        });
        adaugaPilotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new AddPilot(frame).panel1);
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1=new JTable();
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","Nume","Prenume","Adresa","CNP","Telefon"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[9];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            s=conn.createStatement();
            s.execute("SELECT * FROM pilot;");
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idpilot");
                row[1]=rez.getString("Nume");
                row[2]=rez.getString("Prenume");
                row[3]=rez.getString("Adresa");
                row[4]=rez.getString("CNP");
                row[5]=rez.getString("nrTelefon");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
