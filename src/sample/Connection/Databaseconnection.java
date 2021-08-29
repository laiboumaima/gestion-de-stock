package sample.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnection {
    public Connection databaselink;
    // Replace below database url, username and password with your actual database credentials

   public  Connection getconnention(){
         String DATABASE_URL = "jdbc:mysql://localhost:3306/products";
          String DATABASE_USERNAME = "root";
        String DATABASE_PASSWORD = "0000";
     //  private static final String INSERT_QUERY = "INSERT INTO product (id, name, firstprice,price,quantity,category) VALUES (?, ?, ?,?,?,?)";
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           // Class.forName("com.mysql.cj.jdbc.Drive");
           databaselink = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
       } catch (SQLException | ClassNotFoundException throwables) {
           throwables.printStackTrace();
       }

       return databaselink;
   }
}
