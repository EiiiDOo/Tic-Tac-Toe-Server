package ticktacktoeserver;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestsHandler {

    public RequestsHandler() {

    }

    public String Signup(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.insertPlayer(playerInfo);

        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;

    }

    public String Login(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.login(playerInfo);
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


    public String GetUserData(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.getPlayerData(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;
    }
    public String GetUserHistory(String query) {
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.getPlayerHistory(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;
    }
    public String sendGameInvite(String query){
        String [] data =  query.split(",");
        String inviteString = data[2]+",playinvite,"+data[1];
        return inviteString;
    }
    public String setTwoPlayersInGame(String query){
            String [] data =  query.split(",");
            String startUser1 = data[2]+",startmatch,"+data[1];
            String startUser2 = data[1]+",startmatch,"+data[2];
            String startMatchQuery = startUser1 ;
            startMatchQuery +="~";
            startMatchQuery +=startUser2;
          try {
              DataAccessObject.setTwoPlayersInGame(data);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
    
       return startMatchQuery;
  
    }
    
    
}
