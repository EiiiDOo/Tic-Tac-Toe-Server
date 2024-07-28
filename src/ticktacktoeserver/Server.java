package ticktacktoeserver;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket serverSocket;

    Server() {
        try {
            serverSocket = new ServerSocket(5005);
        } catch (IOException ex) {
            System.out.println("ServerException");
        }
        new Thread() {
            public void run() {
                while (true) {
                    Socket s;
                    try {
                        s = serverSocket.accept();
                        new ClientHandler(s);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }.start();

    }

}
