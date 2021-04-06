import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteRezervare {
    private JTable table1;
    public JPanel panel1;
    private JButton backButton;
    private JButton refreshButton;
    private MySqlConnection connect;
    private Statement s;
    private ResultSet rez;
    private Connection conn;

    public DeleteRezervare(JFrame frame) {
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 =new JTable();
        table1.setModel(new DefaultTableModel(new Object[][] {},new String[] {"idrezervare","idcursa","LocuriAchizitionate","Nume","Prenume","Adresa","CNP","NrTelefon","iduser","Bagaj"}));
        DefaultTableModel model=(DefaultTableModel) table1.getModel();
        Object[] row=new Object[10];
        try{
            connect=new MySqlConnection();
            conn=connect.getConnection();
            s=conn.createStatement();
            s.execute("SELECT * FROM rezervare;");
            rez=s.getResultSet();
            while(rez.next()){
                row[0]=rez.getInt("idrezervare");
                row[1]=rez.getInt("idcursa");
                row[2]=rez.getInt("LocuriAchizitionate");
                row[3]=rez.getString("Nume");
                row[4]=rez.getString("Prenume");
                row[5]=rez.getString("Adresa");
                row[6]=rez.getString("CNP");
                row[7]=rez.getString("nrTelefon");
                row[8]=rez.getInt("iduser");
                row[9]=rez.getBoolean("Bagaj");
                model.addRow(row);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
