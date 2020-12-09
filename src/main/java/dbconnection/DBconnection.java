package dbconnection;

import java.sql.DriverManager;
import java.sql.*;

public class DBconnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/ftv";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "password";

    public static Connection ConnectToDB() {
        Connection conn = null;

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        }
        return conn;
    }
}
