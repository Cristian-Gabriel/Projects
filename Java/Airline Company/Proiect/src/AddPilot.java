import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddPilot {
    public JPanel panel1;
    private JButton logOutButton;
    private JButton backButton;
    private JTextField textField1;
    private JTextField numeTextField;
    private JTextField textField3;
    private JTextField prenumeTextField;
    private JTextField textField5;
    private JTextField adresaTextField;
    private JTextField textField7;
    private JTextField telefonTextField;
    private JButton submitButton;
    private JTextField textField2;
    private JTextField CNPTextField;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public AddPilot(JFrame frame) {
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connect = new MySqlConnection();
                    conn = connect.getConnection();
                    CallableStatement s = null;

                    String nume = textField1.getText();
                    String prenume=textField3.getText();
                    String adresa=textField5.getText();
                    String telefon=textField7.getText();
                    String cnp=textField2.getText();
                    s = conn.prepareCall("call AdaugarePilot(?,?,?,?,?);");

                    s.setString(1, nume);
                    s.setString(2, prenume);
                    s.setString(3, adresa);
                    s.setString(4, telefon);
                    s.setString(5,cnp);

                    s.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Adaugare cu suces!");


                    conn.close();
                } catch(SQLException f){
                    f.printStackTrace();
                }
            }
        });
    }
}
