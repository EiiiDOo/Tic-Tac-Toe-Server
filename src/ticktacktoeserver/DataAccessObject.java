package ticktacktoeserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

public class DataAccessObject {

    static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new ClientDriver());
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static void closeDatabase() throws SQLException {
        connection.close();
    }
}
