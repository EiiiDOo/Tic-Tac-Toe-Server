package ticktacktoeserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    public static LinkedBlockingQueue<String> queryQueue = new LinkedBlockingQueue<>();
    public static Map<String, ClientHandler> clientMap = new ConcurrentHashMap<>();
    RequestsHandler rh;

    ClientHandler(Socket cs) {

        rh = new RequestsHandler();
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
        } catch (IOException ex) {
            System.out.print("Can't connect to stream");
        }
        start();
    }

    public void run() {
        while (true) {
            String recievedQuery = null;
            String sendQuery = null;
            try {
                //recieve
                if (dis.available() > 0) {
                    recievedQuery = dis.readLine();
                    recievedQueryHandler(recievedQuery);
                }

                // send 
                sendQuery = queryQueue.poll(500, TimeUnit.MILLISECONDS);
                if (sendQuery != null) {
                    querySender(sendQuery);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void querySender(String responseToClient) {
       
        String[] parsedResponse = responseToClient.split(",");
        if (parsedResponse[1].equalsIgnoreCase("loginstatus") || parsedResponse[1].equalsIgnoreCase("signupstatus")) {
            if (Boolean.parseBoolean(parsedResponse[2])) {        
                clientMap.get(parsedResponse[0]).ps.println(responseToClient);
            } else {
                clientMap.get(parsedResponse[0]).ps.println(responseToClient);
                clientMap.remove(parsedResponse[0]);
            }
        } 
        clientMap.get(parsedResponse[0]).ps.println(responseToClient);

    }

    void recievedQueryHandler(String query) {
        StringTokenizer st = new StringTokenizer(query, ",");
        String q = st.nextToken();
        String playerStatus;
        String[] parse;
        switch (q) {
            case "signup":
                System.out.println("Signup");
                playerStatus = rh.Signup(query);
                parse = playerStatus.split(",");
                ClientHandler.queryQueue.add(playerStatus);
                clientMap.put(parse[0], this);

                break;
            case "login":
                System.out.println("login");
                playerStatus = rh.Login(query);
                parse = playerStatus.split(",");
                ClientHandler.queryQueue.add(playerStatus);
                clientMap.put(parse[0], this);
                break;

            case "getuserdata":
                System.out.println("getuserdata");
                playerStatus = rh.GetUserData(query);
                ClientHandler.queryQueue.add(playerStatus);
                break;
            case "gethistory":
                System.out.println("gethistory");
                
                playerStatus = rh.GetUserHistory(query);
                ClientHandler.queryQueue.add(playerStatus);

            case "getavailableplayers":
                 System.out.println("getavailableplayers");
                parse = query.split(",");
                String availabplePlayers=rh.availablePlayers(parse[1]);
                ClientHandler.queryQueue.add(availabplePlayers);
                break;
            case "playinvite":
                 System.out.println("playinvite");
                 String sendInviteTo=rh.sendGameInvite(query);
                 ClientHandler.queryQueue.add(sendInviteTo);
                break;
            case "acceptinvite":
                 System.out.println("acceptinvite");
                 System.out.println(query);
                 String acceptMessage=rh.setTwoPlayersToStartAGame(query);
                 System.out.println("~~"+acceptMessage+"~~");
                 String [] acceptMessages = acceptMessage.split("~");
                 ClientHandler.queryQueue.add(acceptMessages[0]);
                 ClientHandler.queryQueue.add(acceptMessages[1]);
                break;
            case "rejectinvite":
                 System.out.println("rejectinvite");
                 System.out.println(query);
                 String[] parseReject = query.split(",");
                 ClientHandler.queryQueue.add(parseReject[2]+",rejectinvite,"+parseReject[1]);
                break;
            case "playedmove":
                 System.out.println("playedmove");
                 System.out.println(query);
                 String[] parseMove = query.split(",");
                ClientHandler.queryQueue.add(parseMove[2]+",playedmove,"+parseMove[1]+","+parseMove[3]+","+parseMove[4]);
                break;
            case "win":
                 System.out.println("win");
                 System.out.println(query);
                 String didWin = rh.incrementScore(query);
                 String[] parseWin = query.split(",");
                ClientHandler.queryQueue.add(parseWin[2]+",win,"+parseWin[1]+","+parseWin[3]+","+parseWin[4]);
                break;
            case "lose":
                
                 System.out.println("lose");
                 System.out.println(query);
              //   String[] parseMove = query.split(",");
             //   ClientHandler.queryQueue.add(parseMove[2]+",playedmove,"+parseMove[1]+","+parseMove[3]+","+parseMove[4]);
                break;
            case "draw":
                 System.out.println("draw");
                 System.out.println(query);
             //    String[] parseMove = query.split(",");
             //   ClientHandler.queryQueue.add(parseMove[2]+",playedmove,"+parseMove[1]+","+parseMove[3]+","+parseMove[4]);
                break;
            case "save":
                 System.out.println("save");
                 System.out.println(query);
                 String didIsave = rh.saveMatch(query);
             //    String[] parseMove = query.split(",");
             //   ClientHandler.queryQueue.add(parseMove[2]+",playedmove,"+parseMove[1]+","+parseMove[3]+","+parseMove[4]);
                break;
    

        }

    }

}
