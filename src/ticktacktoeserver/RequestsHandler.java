
package ticktacktoeserver;

import java.sql.SQLException;


public class RequestsHandler {
    
  
  public RequestsHandler(){
  
  }
  
  public void Signup(String query){
  String [] playerInfo = query.split(",");
  try{
    DataAccessObject.insertPlayer(playerInfo);  
  
  }
  catch(SQLException e){
  
  }

  }
  
  
  
}
