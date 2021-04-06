import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Aeroporturi {
    public JPanel panel1;
    private JButton backButton;
    private JButton refreshButton;
    private JButton stergeAeroportButton;
    private JTable table1;
    private JButton adaugaAeroportButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public Aeroporturi(JFrame frame) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension a = new Dimension();
                try {
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
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension a = new Dimension();
                try {
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
        stergeAeroportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call StergereAeroport(?);");

                        // remove selected row from the model
                        Object cursa = table1.getValueAt(table1.getSelectedRow(), 0);
                        Integer a = Integer.valueOf(cursa.toString());

                        s.setInt(1,a);
                        s.executeUpdate();


                        JOptionPane.showMessageDialog(null, "Aeroport sters!");
                    }catch(SQLException f){
                        f.printStackTrace();
                    }
                }
            }
        });
        adaugaAeroportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension a = new Dimension();
                try {
                    frame.setContentPane(new AddAirport(frame).panel1);
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
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","Nume","Oras","Adresa"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[6];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            s=conn.createStatement();
            s.execute("SELECT * FROM aeroport;");
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idaeroport");
                row[1]=rez.getString("Nume");
                row[2]=rez.getString("Oras");
                row[3]=rez.getString("Adresa");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
