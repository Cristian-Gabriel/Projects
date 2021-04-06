import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private JButton signUpButton;
    private JButton logInButton;
    public JPanel panel1=null;
    private JLabel Label;
    public JFrame frame=null;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public App(JFrame frame) {
        initialize(frame);
    }

    public static void main(String[] args) {
        JFrame frame=null;
        new App(frame);
    }

    public void initialize(JFrame frame) {
        if(frame==null){
            frame = new JFrame("Airport management");
            frame.setContentPane(this.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Dimension a=new Dimension();
        a.height=768;
        a.width=1366;
        frame.setMinimumSize(a);
        frame.pack();
        frame.setVisible(true);
        JFrame finalFrame = frame;
        logInButton.addActionListener(e -> {
            JPanel panel1 = new LogIn(finalFrame).panel1;
            a.height=768;
            a.width=1366;
            panel1.setMinimumSize(a);
            finalFrame.setContentPane(panel1);
            finalFrame.setVisible(true);
        });

        JFrame finalFrame1 = frame;
        JFrame finalFrame2 = frame;
        signUpButton.addActionListener(e -> {
            JPanel panel1=new SignUp(finalFrame2).panel1;
            a.height=768;
            a.width=1366;
            panel1.setMinimumSize(a);
            finalFrame1.setContentPane(panel1);
            finalFrame1.setVisible(false);
            finalFrame1.setVisible(true);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
