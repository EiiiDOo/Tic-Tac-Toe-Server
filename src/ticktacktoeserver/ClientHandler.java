package ticktacktoeserver;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ticktacktoeserver.DataAccessObject.getContact;

public class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
 
    public LinkedBlockingQueue<String> queryQueue = new LinkedBlockingQueue<>();

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
            
            String recievedQuery = null;
            String sendQuery = null;
            try {
                //
                if (dis.available() > 0) {
              
                recievedQuery = dis.readLine();
                recievedQueryHandler(recievedQuery);
              
                
                }
                // send 
               sendQuery = queryQueue.poll(2, TimeUnit.SECONDS);
               if(sendQuery !=null){
                   
               querySender(sendQuery);
               
               }
               
                TimeUnit.SECONDS.sleep(2);

            } catch (Exception ex) {
             System.out.print("cant read from stream");     
            }
        }
    }

    void querySender(String msg) {
    
      
    }
    void recievedQueryHandler(String query){
       

    }
    public String getUserData(int id) throws SQLException{
        return getContact(id);
    }
    
}


