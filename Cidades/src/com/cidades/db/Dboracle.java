package com.cidades.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Dboracle {
	
Connection con = null;
    
    public static Connection getConnectionSQL(String dbURL, String user, String password)
     throws SQLException, ClassNotFoundException 
       {
           Class.forName("oracle.jdbc.OracleDriver");
           Properties props = new Properties();
           props.put("user", user);
           props.put("password", password);
           props.put("autoReconnect", "true");

           return DriverManager.getConnection(dbURL, props);
       }
   
   public Connection Conect(String DtbName,String User, String Pwd) throws SQLException, ClassNotFoundException
   {        
       con = getConnectionSQL("jdbc:oracle:thin:@localhost:1521:" + DtbName, User, Pwd);
       return con;
   }
   
   public Connection Conectar() throws ClassNotFoundException, SQLException{
	   return Conect("Oracle12c", "system", "1234");
   }
}
