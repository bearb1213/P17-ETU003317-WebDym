package Util;

import java.sql.*; 

public class MyCoSQL {
    static Connection connex;
    static Statement state;

    public static Connection  GetConnection() throws Exception{
        String url="jdbc:mysql://localhost:3306/db_s2_ETU003317";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connex=DriverManager.getConnection(url,"ETU003317","SvK1xeNP");
            connex.setAutoCommit(true);
            return connex;
        } catch(Exception e) { 
            //e=new Exception("Connexion impossible");
            throw e;
        }
    }
}