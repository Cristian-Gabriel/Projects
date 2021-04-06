import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Search {
    private JTable table1;
    private JButton backButton;
    public JPanel panel1;
    private JButton rezervaButton;
    private JComboBox comboBox1;
    private JCheckBox bagajCheckBox;
    private JButton refreshButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;
    private String text1;
    private String text2;

    public Search(JFrame frame,String text,String textt,Integer user) {
        text1=text;
        text2=textt;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dimension a = new Dimension();
                    frame.setContentPane(new PageUser(frame,user).panel1);
                    a.height = 768;
                    a.width = 1366;
                    panel1.setMinimumSize(a);
                    frame.setVisible(true);
                }catch(Exception f){
                    f.printStackTrace();
                }
            }
        });
        rezervaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1) {
                    try {
                        connect = new MySqlConnection();
                        conn = connect.getConnection();
                        CallableStatement s = null;
                        s = conn.prepareCall("call AdaugareRezervare(?,?,?,?);");

                        // remove selected row from the model
                        Object cursa = table1.getValueAt(table1.getSelectedRow(), 0);
                        Integer a = Integer.valueOf(cursa.toString());
                        Integer locuri;
                        locuri = Integer.parseInt((String)comboBox1.getSelectedItem());
                        Boolean bagaj;
                        if(bagajCheckBox.isSelected())
                            bagaj=true;
                        else
                            bagaj=false;
                        Integer us=user;
                        s.setInt(1,a);
                        s.setInt(2,locuri);
                        s.setInt(3,us);
                        s.setBoolean(4,bagaj);

                        Object b = table1.getValueAt(table1.getSelectedRow(), 7);
                        a = Integer.valueOf(b.toString());
                        if(locuri<=a) {
                            s.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Rezervare cu succes!");
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Locuri indisponibile!");
                    }catch(SQLException f){
                        f.printStackTrace();
                    }
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension a = new Dimension();
                try {
                    frame.setContentPane(new Search(frame,text,textt,user).panel1);
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
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID","OrasPlecare","OrasDestinatie","OraPlecare","OraDestinatie","DataPlecarii","Aeroport","LocuriDisponibile","Pret"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[9];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            CallableStatement s = null;
            s = conn.prepareCall("call CautareDupaOrase(?,?);");
            s.setString(1,text1);
            s.setString(2,text2);
            s.execute();
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idcursa");
                row[1]=rez.getString("OrasPlecare");
                row[2]=rez.getString("OrasDestinatie");
                row[3]=rez.getTime("OraPlecare");
                row[4]=rez.getTime("OraDestinatie");
                row[5]=rez.getDate("DATA");
                row[6]=rez.getString("Nume");
                row[7]=rez.getInt("LocuriRamase");
                row[8]=rez.getInt("pret");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
