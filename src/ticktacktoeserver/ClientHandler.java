package ticktacktoeserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    public static LinkedBlockingQueue<String> queryQueue = new LinkedBlockingQueue<>();
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
                sendQuery = queryQueue.poll(2, TimeUnit.SECONDS);
                if (sendQuery != null) {
                    querySender(sendQuery);
                }
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception ex) {
                System.out.print("cant read from stream");
            }
        }
    }

    void querySender(String msg) {
        
        ps.println(msg);

    }

    void recievedQueryHandler(String query) {

        StringTokenizer st = new StringTokenizer(query, ",");
        String q = st.nextToken();

        switch (q) {
            case "signup":
                System.out.println("Signup");
                rh.Signup(query);
                break;
            case "login":
                System.out.println("login");
                rh.Login(query);
                break;
            case "getuserdata":
                break;
             

        }

    }

}
