package ticktacktoeserver;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;


    ClientHandler(Socket cs) {
  
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
            String query = "";
            try {
                query = dis.readLine();
            } catch (IOException ex) {
             System.out.print("cant read from stream");     
            }
            queryHandler(query);
        }
    }

    void queryHandler(String msg) {
        
        
        
        
      
    }
}


