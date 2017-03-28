/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HNTIN
 */
public class DBConnectionService {

    /**
     * loadJDBCDriver
     *
     * @throws Exception
     */
    protected static void loadJDBCDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("SQL JDBC Driver not found ...");
        }
    }

    /**
     * Return the connection.
     *
     * @return Connection to the database
     * @throws NamingException
     * @throws SQLException
     */
    public static Connection getConnection() throws Exception {
        Connection connect = null;
        if (connect == null) {
            loadJDBCDriver();
            String url = "jdbc:mysql://" + "localhost"
                    + ":" + "3306"
                    + "/" + "acquygiakhangdb?useUnicode=true&characterEncoding=UTF-8"
                    + "&user=" + "root"
                    + "&password=" + "root"
                    + "&autoReconnect=true"
                    + "&connectTimeout=300";

            try {
                connect = DriverManager.getConnection(url);
            } catch (java.sql.SQLException e) {
                throw new Exception("Can not access to Database Server ..." + url + e.getMessage());
            }
        }
        return connect;
    }
}
