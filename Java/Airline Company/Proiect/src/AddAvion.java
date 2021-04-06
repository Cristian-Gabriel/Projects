import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class AddAvion {
    private JTextField textField1;
    private JTextField numeAvionTextField;
    private JTextField textField3;
    private JTextField numeCompanieTextField;
    private JTextField textField5;
    private JTextField modelAvionTextField;
    private JTextField textField7;
    private JTextField numarLocuriTextField;
    private JButton button1;
    public JPanel panel1;
    private JButton backButton;
    private JButton logOutButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;
    private String numeAvion;
    private String numeCompanie;
    private String model;
    private Integer locuri;


    public AddAvion(JFrame frame) {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connect = new MySqlConnection();
                    conn = connect.getConnection();
                    CallableStatement s = null;

                    numeAvion = textField1.getText();
                    numeCompanie=textField3.getText();
                    model=textField5.getText();
                    locuri= Integer.parseInt(textField7.getText());
                        s = conn.prepareCall("call AdaugareAvion(?,?,?,?);");

                        s.setString(1, numeAvion);
                        s.setString(2, numeCompanie);
                        s.setString(3, model);
                        s.setInt(4, locuri);

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
    }
}
