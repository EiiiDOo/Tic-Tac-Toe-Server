package ticktacktoeserver;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestsHandler {

    public RequestsHandler() {

    }

    public String Signup(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus ="";
        try {
            playerStatus = DataAccessObject.insertPlayer(playerInfo);
       
        } catch (SQLException e) {

        }
       return playerStatus;

    }
    public String Login(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus ="" ;
        try {
          playerStatus =   DataAccessObject.login(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;
    }
    public String availablePlayers(String username){
        String availablePlayer =username+",getavailableplayers," ;
        try {
            availablePlayer +=   DataAccessObject.getAvailablePlayers();
        } catch (SQLException ex) {
            System.out.println("get all available players sql exception");;
        }
        return availablePlayer;
    }
    public  void  SendDataToClient() throws SQLException {

      
    }
}
