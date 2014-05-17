package com.netcracker.wind.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * This singleton class allow organize easy work with connection to database.
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

    /**
     * Method allow to get instance of {@link ConnectionPool} class
     *
     * @return instance {@link ConnectionPool}
     */
    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    /**
     * Method allows to get instance of {@link Connection}
     *
     * @return instance {@link Connection} or null if was throwing
     * {@link SQLException}
     */
    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return connection;
    }

    /**
     * Method allows close instance of {@link Connection}
     *
     * @param connection connection that should be close
     */
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
    }

}
