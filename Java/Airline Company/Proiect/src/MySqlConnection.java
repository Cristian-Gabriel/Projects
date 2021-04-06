import java.sql.*;

public class MySqlConnection {
    private Connection connection;

    @SuppressWarnings("deprecation")
    public MySqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
            ex.printStackTrace(System.err);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aeroport?serverTimezone=UTC&&user=root&password=cristiroot");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
