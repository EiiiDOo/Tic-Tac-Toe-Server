
package ticktacktoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ServerHomePageController  extends Thread {
    
    Button btnStartServer;
    Button brnStopServer;
    Text txtServerStatus;
    boolean isServerRun;
    Thread serverThread;
    ServerSocket serverSocket;

    ServerHomePageController(Button btnStartServer, Button brnStopServer, Text txtServerStatus , Stage stage) {
      this.btnStartServer=btnStartServer;
      this.brnStopServer=brnStopServer;
      this.txtServerStatus=txtServerStatus;
      
     stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
         synchronized  public void handle(WindowEvent event) {
               

             
                 stop();
                    System.exit(0);
            }
        });
      btnStartServer.setOnAction(( event) -> {
          if(isServerRun == true ){
             this.start();
             txtServerStatus.setText("Server is Online");
             isServerRun = false;
          }
              txtServerStatus.setText("Server is Online");
            this.resume();
      
        });
      brnStopServer.setOnAction(( event) -> {
            this.suspend();
         txtServerStatus.setText("Server is Offline");
        });

        try {
            serverSocket = new ServerSocket(5005);
        } catch (IOException ex) {
            System.out.println("ServerException");
        }
     

    isServerRun = true;

    }
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

      }
    
