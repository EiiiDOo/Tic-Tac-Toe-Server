package ticktacktoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    
    Server(){
      
        try {
            serverSocket = new ServerSocket(5005);
            while(true){
            Socket s = serverSocket.accept();
            new ClientHandler(s);
            }
        } catch (IOException ex) {
            System.out.println("Couldn't create server");
        }
    }

}
