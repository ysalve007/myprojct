/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class dbconnection {
    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement prestmt=null;

    public static Statement connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "fast_project";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName, userName, password);
            // If we are going to insert a Duplicate entry for PRIMARYKEY, we will get an exception
            // com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
            statement = connection.createStatement();
            //System.out.println("Connected to the database");


        } catch (Exception e) {

            e.printStackTrace();
        }
        finally
        {
            
        }
        return statement;
    }
// public static Connection getConn() throws Exception {
//        String url = "jdbc:mysql://localhost:3306/";
//        String dbName = "fogcomputing";
//        String driver = "com.mysql.jdbc.Driver";
//        String userName = "root";
//        String password = "";
//
//        try {
//            Class.forName(driver).newInstance();
//            connection = DriverManager.getConnection(url + dbName, userName, password);
//           
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        return connection ;
//    }
    public static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static long getInsertId() {
        try {
            Object[] insertIds = getInsertIds();
            return Long.parseLong(""+insertIds[0]);
        } catch (Exception e) {
            return -1;
        }
    }

    public static Object[] getInsertIds() {
        try {
            if (statement != null) {
                dbconn.statement = statement;
            } else {
                statement = dbconn.statement;
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            ArrayList<Long> keys = new ArrayList<Long>();
            while (generatedKeys.next()) {
                keys.add(generatedKeys.getLong(1));
            }
            return keys.toArray();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
}
