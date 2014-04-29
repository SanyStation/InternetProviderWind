package com.netcracker.wind.dao.implementations.helper;

import com.netcracker.wind.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana
 */
public abstract class AbstractDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(AbstractDAO.class.getName());

    protected List findWhere(String query, Object[] param) {
        List circuits = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(query);
            if (param != null) {
                for (int i = 0; i < param.length; ++i) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            circuits = parseResult(rs);
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(con);
        }
        return circuits;
    }

    public void delete(String deleteQuary, int id) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(deleteQuary);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(con);
        }
    }

    protected abstract List parseResult(ResultSet rs);
}
