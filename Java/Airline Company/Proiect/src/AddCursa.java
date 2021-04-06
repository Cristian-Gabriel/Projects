import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class AddCursa {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField dataTextField;
    private JTextField orasTextField;
    private JTextField textField3;
    private JTextField orasDestinatieTextField;
    private JTextField textField4;
    private JTextField oraDePlecareTextField;
    private JTextField textField6;
    private JTextField oraDestinatieTextField;
    private JTextField textField5;
    private JTextField aeroportTextField;
    private JButton submitButton;
    private JButton logOutButton;
    private JTextField textField7;
    private JTextField avionTextField;
    private JTextField textField8;
    private JTextField pretTextField;
    private JButton backButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JTextField pilotTextField;
    private JComboBox comboBox4;
    private MySqlConnection connect;
    private PreparedStatement s;
    private ResultSet rez=null;
    private Connection conn;
    private Integer avion, aeroport;
    private Float suma;
    private String plecare, destinatie;
    private String avion1;


    public AddCursa(JFrame frame) throws Exception {
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connect = new MySqlConnection();
                    conn = connect.getConnection();
                    CallableStatement s = null;
                    boolean ok=true;
                    String item = comboBox1.getSelectedItem().toString();
                    Statement st = null;
                    st = conn.createStatement();
                    st.execute("select * from date_avion where Nume='"+item+"';");
                    rez=st.getResultSet();
                    if(rez.next()) {
                        avion = rez.getInt("id_avion");
                    }else{
                        ok=false;
                        JOptionPane.showMessageDialog(null, "Avion inexistent!");
                    }
                    avion1 = textField8.getText();
                    suma = Float.parseFloat(avion1);
                    avion1 = textField1.getText();
                    Date data = new SimpleDateFormat("yyyy-mm-dd").parse(avion1);
                    destinatie = textField3.getText();
                    avion1 = textField4.getText();
                    DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                    Time ora1=null;
                    try {
                        LocalTime localtime = LocalTime.parse(avion1);
                        ora1 = Time.valueOf(localtime);
                    }catch (Exception f){
                        JOptionPane.showMessageDialog(null,"Ora de start gresita!");
                        ok=false;
                    }
                    avion1 = textField6.getText();
                    Time ora2=null;
                    try {
                        LocalTime localtime = LocalTime.parse(avion1);
                        ora2 = Time.valueOf(localtime);
                    }catch (Exception f){
                        JOptionPane.showMessageDialog(null,"Ora de final gresita!");
                        ok=false;
                    }
                    String aero = comboBox2.getSelectedItem().toString();
                    String or= comboBox3.getSelectedItem().toString();
                    st = conn.createStatement();
                    st.execute("select idaeroport from aeroport where Nume='"+aero+"' and Oras='"+or+"';");
                    rez=st.getResultSet();
                    if(rez.next()) {
                        aeroport = rez.getInt("idaeroport");
                    }else{
                        ok=false;
                        JOptionPane.showMessageDialog(null, "Aeroport inexistent!");
                    }
                    String p=comboBox4.getSelectedItem().toString();
                    st = conn.createStatement();
                    st.execute("select idpilot from pilot where Nume='"+p+"';");
                    rez=st.getResultSet();
                    Integer pilot=null;
                    if(rez.next()) {
                        pilot = rez.getInt("idpilot");
                    }else{
                        ok=false;
                        JOptionPane.showMessageDialog(null, "Pilot inexistent!");
                    }
                    if(ok){
                        s = conn.prepareCall("call AdaugareCursa(?,?,?,?,?,?,?,?);");

                        s.setInt(1, avion);
                        s.setFloat(2, suma);
                        s.setDate(3, convertDateToSql(data));
                        s.setString(4, destinatie);
                        s.setTime(5, ora1);
                        s.setTime(6, ora2);
                        s.setInt(7, aeroport);
                        s.setInt(8,pilot);

                        //s=conn.prepareStatement("INSERT INTO orar (DATA,OrasPlecare,OrasDestinatie,OraPlecare,OraDestinatie,idaeroport) VALUES(?,?,?,?,?,?);");


                        s.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Adaugare cu suces!");
                    }


                    conn.close();
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private java.sql.Date convertDateToSql(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            connect = new MySqlConnection();
            conn = connect.getConnection();
            Statement s = null;
            s = conn.createStatement();
            s.execute("select * from aeroport;");
            rez=s.getResultSet();
            comboBox2=new JComboBox<>();
            while(rez.next()){
                String b=rez.getString("Nume");
                comboBox2.addItem(b);
            }
        }catch(SQLException f){
            f.printStackTrace();
        }
        try {
            connect = new MySqlConnection();
            conn = connect.getConnection();
            Statement s = null;
            s = conn.createStatement();
            s.execute("select * from date_avion;");
            rez=s.getResultSet();
            comboBox1=new JComboBox<>();
            while(rez.next()){
                String a=rez.getString("Nume");
                comboBox1.addItem(a);
            }
        }catch(SQLException f){
            f.printStackTrace();
        }
        try {
            connect = new MySqlConnection();
            conn = connect.getConnection();
            Statement s = null;
            s = conn.createStatement();
            s.execute("select * from aeroport;");
            rez=s.getResultSet();
            comboBox3=new JComboBox<>();
            while(rez.next()){
                String a=rez.getString("Oras");
                comboBox3.addItem(a);
            }
        }catch(SQLException f){
            f.printStackTrace();
        }
        try {
            connect = new MySqlConnection();
            conn = connect.getConnection();
            Statement s = null;
            s = conn.createStatement();
            s.execute("select * from pilot;");
            rez=s.getResultSet();
            comboBox4=new JComboBox<>();
            while(rez.next()){
                String a=rez.getString("Nume");
                comboBox4.addItem(a);
            }
        }catch(SQLException f){
            f.printStackTrace();
        }
    }
}
