package ticktacktoeserver;

import java.sql.SQLException;
import java.util.Random;
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
        System.out.println(query);
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.getPlayerHistory(playerInfo);
             System.out.println(playerStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerStatus;
    }
        public boolean isLoggedOfOrInGame(String query) {
        String[] playerInfo = query.split(",");
       
        boolean ilori = false; 
        try {
            ilori = DataAccessObject.isLoggedOfOrInGame(playerInfo[1]);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public String sendGameInvite(String query){
        String [] data =  query.split(",");
        String inviteString = data[2]+",playinvite,"+data[1];
        return inviteString;
    }
    public String setTwoPlayersToStartAGame(String query){
            Random random = new Random(); 
            int randomInt = random.nextInt(2);
            String [] data =  query.split(",");
            String startMatchQuery = null;
          try {
            String player1Data = DataAccessObject.getPlayerDataForMatchInit(data[1]);
            String player2Data = DataAccessObject.getPlayerDataForMatchInit(data[2]);
            String player1Coin =Integer.toString( randomInt) ;
            String player2Coin = Integer.toString(randomInt==0? 1:0); 

            String startUser1 = data[2]+",startmatch,"+player2Coin+","+player1Coin+","+player1Data+","+player2Data;
            String startUser2 = data[1]+",startmatch,"+player1Coin+","+player2Coin+","+player2Data+","+player1Data;

            startMatchQuery = startUser1 ;
            startMatchQuery +="~";
            startMatchQuery +=startUser2;
              DataAccessObject.setTwoPlayersInGame(data);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
    
       return startMatchQuery;
  
    }
    public String incrementScore(String query){
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.incrementScore(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;
    }
    public String saveMatch(String query){
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.saveMatch(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return playerStatus;
    }

    public String surrMatch(String query){
    
       String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.surrMatch(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return query;
    }
        public String setTwoPlayersAvailable(String query){
  
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.setTwoPlayersAvailable(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return query;
 
  
    }
               public String setTwoPlayersLoggedOff(String query){
  
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.setTwoPlayersLoggedOff(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return query;
 
  
    }
               public String setPlayerAvailable(String query){
  
        String[] playerInfo = query.split(",");
        String playerStatus = "";
        try {
            playerStatus = DataAccessObject.setPlayerAvailable(playerInfo);
        } catch (SQLException e) {
            System.out.println("SQL exception");
        }
        return query;
 
  
    }

}
