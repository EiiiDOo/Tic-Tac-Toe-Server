package ticktacktoeserver;

import java.sql.SQLException;

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
    public  void  SendDataToClient() throws SQLException {

      
    }
}
