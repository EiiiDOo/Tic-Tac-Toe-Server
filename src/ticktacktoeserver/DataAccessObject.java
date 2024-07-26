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

    public static String getPlayerData(String username) throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String player = null;
        String queryString = new String("SELECT * FROM PLAYERS WHERE USERNAME = '" + username + "';");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            player = username + ",";
            player += rs.getString("FIRSTNAEM") + ",";
            player += rs.getString("LASTNAME") + ",";
            player += rs.getString("MALE") + ",";
            player += rs.getString("SCORE");
        }
        pst.close();
        return player;
    }

    public static String getAvailablePlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = new String("SELECT * FROM PLAYERS WHERE AVAILABLE = true;");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players+=rs.getString("USERNAME")+",";
        }
        pst.close();
        connection.close();
        return players;
    }
    public static String getLoggedOffPlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = new String("SELECT * FROM PLAYERS WHERE LOGGEDOFF = true;");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players+=rs.getString("USERNAME")+",";
        }
        pst.close();
        connection.close();
        return players;
    }
    public static String getInGamePlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = new String("SELECT * FROM PLAYERS WHERE INGAME = true;");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players+=rs.getString("USERNAME")+",";
        }
        pst.close();
        connection.close();
        return players;
    }

    public static String login(String username,String password) throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String queryString = new String("SELECT * FROM PLAYERS WHERE USERNAME = '" + username +"' AND PASSWORD = '"+password+ "';");
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        pst.close();
        if (rs.next()) {
            return "true";
        }

        return "false";
    }
}
