package ticktacktoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket serverSocket;

    Server() {
        System.out.println("server");

              
        try {
            System.out.println("try1");
            serverSocket = new ServerSocket(5005);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
            new Thread() {
                public void run() {
                      System.out.println("run");
                    while (true) {
                          System.out.println("while run");
                        Socket s;
                        try {
                            s = serverSocket.accept();
                             new ClientHandler(s);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
                    }
                }
            }.start();
        
    }

}
