package ticktacktoeserver;

import java.sql.SQLException;

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
}
