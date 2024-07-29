package ticktacktoeserver;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Server {

    ServerSocket serverSocket;
    Thread serverThread;
//     Button btnStartServer;
//    Button brnStopServer;
//    Text txtServerStatus;
    boolean isServerRun;
//    Server server;

    Server() {
        isServerRun = false;

//        serverThread.start();
//        isServerRun=true;
//        btnStartServer.setOnAction(( event) -> {
//                   if(isServerRun==false){
//                   txtServerStatus.setText("Server Start");
//                   serverThread.start();
//                   isServerRun=true;
//                   }
//        });
//      brnStopServer.setOnAction(( event) -> {
//          if(isServerRun==true){
//                   isServerRun=false;
//                   txtServerStatus.setText("Server Closed");
//                   serverThread.interrupt();
//          }
//        });
//serverThread.start(); // Start the thread here
//        isServerRun = true;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(5005);
        } catch (IOException ex) {
            System.out.println("ServerException");
        }
        serverThread = new Thread() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    Socket s;
                    try {
                        s = serverSocket.accept();
                        new ClientHandler(s);
                    } catch (Exception ex) {
                        System.out.println("ServerException");
                    }

                }
            }
        };
        serverThread.start();
        isServerRun = true;
    }


public void stopServer() {
        if (isServerRun==true) {
            serverThread.interrupt();
           
            try {
                serverSocket.close(); // Close the server socket
            } catch (IOException ex) {
                System.out.println("Error closing server socket");
            }
            isServerRun = false;
   
        }
    }}
