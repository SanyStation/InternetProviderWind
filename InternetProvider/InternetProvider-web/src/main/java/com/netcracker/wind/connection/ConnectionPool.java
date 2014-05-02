package com.netcracker.wind.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Anatolii
 */
public class ConnectionPool {

    private final String NAME = "jdbc/wind";
    private static final ConnectionPool connectionPool = new ConnectionPool();
    private static final Logger LOGGER
            = Logger.getLogger(ConnectionPool.class.getName());

    private DataSource dataSource;

    private ConnectionPool() {
        Context initialContext;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(NAME);
        } catch (NamingException ex) {
            LOGGER.error(null, ex);
        }
    }

    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return connection;
    }

    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
    }

}
