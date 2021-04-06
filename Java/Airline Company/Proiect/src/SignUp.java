import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignUp {
    public JPanel panel1;
    private JTextField numeTextField;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField prenumeTextField;
    private JTextField adresaTextField;
    private JTextField textField4;
    private JTextField textField3;
    private JTextField CNPTextField;
    private JTextField textField5;
    private JTextField telefonTextField;
    private JTextField numeDeUtilizatorTextField;
    private JTextField textField7;
    private JTextField parolaTextField;
    private JButton submitButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField parolaTextField1;
    private JButton logInButton;
    private JButton backButton;
    private MySqlConnection connect;
    private CallableStatement s;
    private ResultSet rez;
    private Connection conn;
    private String nume;
    private String prenume;
    private String adresa;
    private String cnp;
    private String telefon;
    private String user;
    private String parola1;
    private String parola2;

    public SignUp(JFrame frame) {
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            connect=new MySqlConnection();
                            conn=connect.getConnection();


                            nume=textField2.getText();
                            prenume=textField1.getText();
                            adresa=textField4.getText();
                            cnp=textField3.getText();
                            telefon=textField5.getText();
                            user=textField7.getText();
                            parola1=passwordField1.getText();
                            parola2=passwordField2.getText();

                            if(parola1.equals(parola2)) {
                                Statement st=null;
                                st=conn.createStatement();
                                st.execute("select * from user where username='"+user+"' and password='"+parola1+"';");
                                rez=st.getResultSet();
                                if(!rez.next()) {
                                    s = conn.prepareCall("call AdaugareUser(?,?,?,?,?,?,?,?);");

                                    s.setString(1, user);
                                    s.setString(2, parola1);
                                    s.setString(3, nume);
                                    s.setString(4, prenume);
                                    s.setString(5, adresa);
                                    s.setString(6, cnp);
                                    s.setString(7, telefon);
                                    s.setBoolean(8, false);

                                    s.execute();

                                    JOptionPane.showMessageDialog(null, "Sign Up cu succes!");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Cont existent!");
                                }

                            }else{
                                JOptionPane.showMessageDialog(null, "Parolele nu coincid!");
                            }

                            conn.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
        logInButton.addActionListener(new ActionListener() {
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
                    frame.setContentPane(new App(frame).panel1);
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
    public static void main(String[] args){

    }
}
