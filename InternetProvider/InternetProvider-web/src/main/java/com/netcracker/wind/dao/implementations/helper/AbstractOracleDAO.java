package com.netcracker.wind.dao.implementations.helper;

import com.netcracker.wind.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana
 */
public abstract  class AbstractOracleDAO {
    
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 30;
    public static final int ALL_RECORDS = 0;
    
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(AbstractOracleDAO.class.getName());
    
    protected static final String ROWS = "total_rows";
    protected int rows;
    
    public int getRows() {
        return rows;
    }
    
    /**
     *
     * @param query SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found service instances
     */
    private List findWhere(String query, Object[] param) {
        List objects = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
//            LOGGER.info("### Query: " + query);
            stat = con.prepareStatement(query);
            if (param != null) {
                for (int i = 0; i < param.length; ++i) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            objects = parseResult(rs);
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
        return objects;
    }

    /**
     * Paginated SELECT.
     *
     * @param query SQL statement
     * @param param the list of parameters
     * @param pageNumber number of desired page (starts from 1)
     * @param pageSize number of rows per desired page (starts from 1)
     * @return list of found rows
     */
    protected List findWhere(String query, Object[] param, int pageNumber,
            int pageSize) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be greater "
                    + "than 0: " + pageNumber);
        }
        if (pageSize < 0) {
            throw new IllegalArgumentException("Page size must be greater or "
                    + "equal 0: " + pageSize);
        }
        List p = new ArrayList();
        for (int i = 0; i < param.length; ++i) {
            p.add(param[i]);
        }
        int rowFrom = (pageNumber - 1) * pageSize;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM (SELECT ROWNUM ROW_NUM, SUBQ.* FROM (");
        sb.append(query);
        if (pageSize > 0) {
            sb.append(") SUBQ WHERE ROWNUM <= ?) WHERE ROW_NUM > ?");
            int rowTo = pageNumber * pageSize;
            p.add(rowTo);
        } else {
            sb.append(") SUBQ) WHERE ROW_NUM > ?");
        }
        p.add(rowFrom);
        return this.findWhere(sb.toString(), p.toArray());
    }

    public void delete(String deleteQuery, int id) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(deleteQuery);
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

    /**
     *
     * @param rs result return from database
     * @return list of found service instances
     */
    protected abstract List parseResult(ResultSet rs);

}
