/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.dbaccess;

import java.sql.Connection;
import hgksoft.acquy.dbconnection.DBConnectionService;

/**
 *
 * @author HNTIN
 */
public class DBMapper {

    /**
     * @uml.property name="connection"
     */
    private Connection connection;

    /**
     * The constructor.
     *
     * @throws Exception
     */
    public DBMapper() throws Exception {
        try {
            connection = DBConnectionService.getConnection();
        } catch (Exception e) {
            System.out.println("Failed in constructor method in MapperDB:" + e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * The constructor.
     *
     * @param con
     */
    public DBMapper(Connection con) {
        connection = con;
    }

    /**
     * Close the connection
     *
     * @Throws: Exception
     * @throws Exception
     * @Modify: Date :	User: Decription:
     */
    public void closeConnection() throws Exception {
        try {
            getConnection().close();
        } catch (Exception e) {
            System.out.println("Failed in closeConnection method in MapperDB:" + e);
            throw e;
        }
    }

    /**
     * @return @uml.property name="connection"
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection
     * @uml.property name="connection"
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
