import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddAirport {
    private JTextField textField1;
    private JTextField numeTextField;
    private JTextField textField3;
    private JTextField adresaTextField;
    private JButton adaugaButton;
    public JPanel panel1;
    private JButton logOutButton;
    private JButton backButton;
    private JTextField orasTextField;
    private JTextField textField4;
    private MySqlConnection connect;
    private Connection conn;
    private String nume;
    private String Adresa;
    private String Oras;

    public AddAirport(JFrame frame) {
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connect = new MySqlConnection();
                    conn = connect.getConnection();
                    CallableStatement s = null;

                    nume = textField1.getText();
                    Adresa=textField3.getText();
                    Oras=textField4.getText();
                    s = conn.prepareCall("call AdaugareAeroport(?,?,?);");

                    s.setString(1, nume);
                    s.setString(2,Oras);
                    s.setString(3, Adresa);

                    s.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Adaugare cu suces!");


                    conn.close();
                } catch(SQLException f){
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
        backButton.addActionListener(new ActionListener() {
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
    }
}
