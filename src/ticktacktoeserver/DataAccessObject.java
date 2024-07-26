package ticktacktoeserver;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import org.apache.derby.jdbc.ClientDriver;

public class DataAccessObject {

    public static String insertPlayer(String[] playerInfo) throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");

        String queryString = "INSERT INTO Players (USERNAME, FIRSTNAME, LASTNAME, PASSWORD, MALE, SCORE, PICTURE, AVAILABLE, LOGGEDOFF, INGAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(queryString);

        pst.setString(1, playerInfo[1]); // USERNAME
        pst.setString(2, playerInfo[2]); // FIRSTNAME
        pst.setString(3, playerInfo[3]); // LASTNAME
        pst.setString(4, playerInfo[4]); //PASSWORD
        pst.setBoolean(5, (playerInfo[5].toUpperCase() == "TRUE" ? true : false)); //male
        pst.setInt(6, 0);// isMale 
        Blob imageBlob = connection.createBlob();
        imageBlob.setBytes(1, Base64.getDecoder().decode(playerInfo[7]));
        pst.setBlob(7, imageBlob);
        pst.setBoolean(8, true);
        pst.setBoolean(9, false);
        pst.setBoolean(10, false);
        int rs = pst.executeUpdate();
        pst.close();
        connection.close();

        return Integer.toString(rs);
    }

}
