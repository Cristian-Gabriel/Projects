import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteUser {
    private JTable table2;
    private JButton stergeUserButton;
    private JButton backButton;
    public JPanel panel1;
    private JButton refreshButton;
    private JButton veziRezervariButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public DeleteUser(JFrame frame) {
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
        stergeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table2.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call StergereUser(?);");

                        // remove selected row from the model
                        Object cursa = table2.getValueAt(table2.getSelectedRow(), 0);
                        Integer a = Integer.valueOf(cursa.toString());

                        s.setInt(1,a);
                        s.executeUpdate();


                        JOptionPane.showMessageDialog(null, "User sters!");
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
        veziRezervariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table2.getSelectedRow() != -1) {
                    try {
                        Object cursa = table2.getValueAt(table2.getSelectedRow(), 0);
                        Integer user = Integer.valueOf(cursa.toString());
                        Dimension a = new Dimension();
                        frame.setContentPane(new VeziRezervari(frame, user).panel1);
                        a.height = 768;
                        a.width = 1366;
                        panel1.setMinimumSize(a);
                        frame.setVisible(true);
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table2 =new JTable();
        table2.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","Username","Password","Nume","Prenume","Adresa","CNP","NrTelefon"}));
        DefaultTableModel model=(DefaultTableModel) table2.getModel();
        Object[] row=new Object[9];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            s=conn.createStatement();
            s.execute("SELECT * FROM user where admin=0;");
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("iduser");
                row[1]=rez.getString("username");
                row[2]=rez.getString("password");
                row[3]=rez.getString("Nume");
                row[4]=rez.getString("Prenume");
                row[5]=rez.getString("Adresa");
                row[6]=rez.getString("CNP");
                row[7]=rez.getString("nrTelefon");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
