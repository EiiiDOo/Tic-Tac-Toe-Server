package ticktacktoeserver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.jdbc.ClientDriver;

public class DataAccessObject {
    
          
    public static void selectAllContact() throws SQLException {
  //  List<Contact> contactList = new ArrayList<Contact>();
    
   
    DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB","root","root");

    String queryString = new String("SELECT * FROM contacts");
    PreparedStatement pst = connection.prepareStatement(queryString) ;
    ResultSet rs = pst.executeQuery() ;
    while(rs.next())
    {
                int id = rs.getInt("ID");
                String fn = rs.getString("F_NAME");
                String mn = rs.getString("M_NAME");
                String ln = rs.getString("L_NAME");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
            //    contactList.add(new Contact(id,fn,mn,ln,email,phone));
               
    }
    pst.close();
    connection.close();
   // return contactList;
    }
     public static String getContact(int id) throws SQLException {
    String contact=""+id+",";
    DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB","root","root");

    String queryString = new String("SELECT * FROM contacts WHERE id = '"+id+"';");
    PreparedStatement pst = connection.prepareStatement(queryString) ;
    ResultSet rs = pst.executeQuery() ;
    
               contact+= rs.getString("F_NAME")+",";
                contact+= rs.getString("M_NAME")+",";
                contact+= rs.getString("L_NAME")+",";
                contact+= rs.getString("EMAIL")+",";
                contact+= rs.getString("PHONE");
    pst.close();
    connection.close();
    return contact;
    }
    
    public static void insertContact() throws SQLException {
  DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB","root","root");
    
    String queryString = new String("INSERT INTO contacts (ID, F_NAME, M_NAME , L_NAME , EMAIL , PHONE ) VALUES (?, ?, ?,?,?,?)");
    
    PreparedStatement pst = connection.prepareStatement(queryString) ;
//    pst.setInt (1, contact.getID() );
//    pst.setString(2, contact.getF_name());
//    pst.setString(3, contact.getM_name());
//    pst.setString(4, contact.getL_name());
//    pst.setString(5, contact.getEmail());
//    pst.setString(6, contact.getPhone());
//    
    int rs = pst.executeUpdate() ;
   
    pst.close();
    connection.close();
    
    }
    
     public static void deleteContact() throws SQLException {
  DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB","root","root");
    
    String queryString = new String("DELETE FROM contacts WHERE ID = ?");
    
    PreparedStatement pst = connection.prepareStatement(queryString) ;
   // pst.setInt (1, contact.getID() );
    
    int rs = pst.executeUpdate() ;
   
    pst.close();
    connection.close();
    
    }
      
     public static void update() throws SQLException {
  DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB","root","root");
    
    String queryString =new String( "UPDATE contacts SET F_NAME = ?, M_NAME = ?, L_NAME = ?, EMAIL = ?, PHONE = ? WHERE ID = ?");
    
    PreparedStatement pst = connection.prepareStatement(queryString) ;
   
//    pst.setString(1, contact.getF_name());
//    pst.setString(2, contact.getM_name());
//    pst.setString(3, contact.getL_name());
//    pst.setString(4, contact.getEmail());
//    pst.setString(5, contact.getPhone());
//    pst.setInt (6, contact.getID() ); 
//    
    
    int rs = pst.executeUpdate() ;
   
    pst.close();
    connection.close();
    
    }
    
    
    
}



