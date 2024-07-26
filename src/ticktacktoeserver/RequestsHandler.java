package ticktacktoeserver;

import java.sql.SQLException;

public class RequestsHandler {

    public RequestsHandler() {

    }

    public void Signup(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus ="";
        try {
            DataAccessObject.insertPlayer(playerInfo);
        } catch (SQLException e) {

        }
       ClientHandler.queryQueue.add("signupstatus,true");

    }
    public void Login(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus ="" ;
        try {
          playerStatus =   DataAccessObject.login(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }

        ClientHandler.queryQueue.add("loginstatus,"+playerStatus);

    }
    public  void  SendDataToClient() throws SQLException {

      
    }
}
