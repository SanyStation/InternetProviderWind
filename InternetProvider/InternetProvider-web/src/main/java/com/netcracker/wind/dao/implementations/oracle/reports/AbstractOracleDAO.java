package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public abstract class AbstractOracleDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List findWhere(String query, List param) {
        List entities = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            System.out.println("### Query: " + query);
            stat = con.prepareStatement(query);
            if (param != null) {
                for (int i = 0; i < param.size(); ++i) {
                    System.out.println("### param [" + i + "] = " + param.get(i));
                    stat.setObject(i + 1, param.get(i));
                }
            }
            rs = stat.executeQuery();
            entities = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractOracleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractOracleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbstractOracleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return entities;
    }

    protected abstract List parseResult(ResultSet rs);

}
