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
                sendQuery = queryQueue.poll(1, TimeUnit.SECONDS);
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
                break;

        }

    }

}
