import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn {
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton logInButton1;
    public JPanel panel1;
    private JButton backButton;
    private JLabel label1;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JPanel panel2;
    private String user1;
    private String pass1;
    private String r;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public LogIn(JFrame frame) {

        logInButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connect=new MySqlConnection();
                    conn=connect.getConnection();
                    s=conn.createStatement();
                    user1=textField1.getText();
                    pass1=passwordField1.getText();
                    s.execute("select * from user where username='"+user1+"';");
                    rez=s.getResultSet();
                    rez.next();
                    String pass=rez.getString("password");
                    if(pass1.equals(pass)) {
                        Boolean admin=rez.getBoolean("admin");
                        if(admin==true){
                            Dimension a=new Dimension();
                            frame.setContentPane(new PageAdmin(frame).panel1);
                            a.height=768;
                            a.width=1366;
                            panel1.setMinimumSize(a);
                            frame.setVisible(true);
                        }
                        else{
                            Dimension a=new Dimension();
                            Integer user=rez.getInt("iduser");
                            frame.setContentPane(new PageUser(frame, user).panel1);
                            a.height=768;
                            a.width=1366;
                            panel1.setMinimumSize(a);
                            frame.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Parola incorecta!");
                    }
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label1.setLayout(panel2.getLayout());
    }
}