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

    public static String getPlayerData(String username) throws SQLException {
        String player=null;
        String queryString = new String("SELECT * FROM PLAYERS WHERE USERNAME = '" + username + "';");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
              player=username + ",";
            player += rs.getString("FIRSTNAEM") + ",";
            player += rs.getString("LASTNAME") + ",";
            player += rs.getString("MALE") + ",";
            player += rs.getString("SCORE");
        } 
        pst.close();
        return player;
    }
     public static ArrayList<String> getAvailablePlayers() throws SQLException {
        ArrayList<String> players = new ArrayList<>();
        String queryString = new String("SELECT * FROM PLAYERS WHERE AVAILABLE = true;");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
              players.add(rs.getString("USERNAME"));
        } 
        pst.close();
        return players;
    }
    public static void closeDatabase() throws SQLException {
        connection.close();
    }
}
