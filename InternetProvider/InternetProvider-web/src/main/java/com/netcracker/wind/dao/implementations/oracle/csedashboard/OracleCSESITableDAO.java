/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.implementations.oracle.csedashboard;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import com.netcracker.wind.entities.csedashboard.SIRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Anna
 */
public class OracleCSESITableDAO extends AbstractOracleDAO implements ICSEDashboardDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    public static final String ROWS_COUNT = "rows_count";
    public static final String SI_ID = "SI_id";
    public static final String USER_ID = "User_id";
    public static final String USER_NAME = "User_name";
    public static final String SERVICE_ORDER_ID = "Service_order_id";
    public static final String SERVICE_ID = "Service_id";
    public static final String SERVICE_NAME = "Service_name";
    public static final String SI_STATUS = "SI_status";
    private static final String SELECT_SI = "SELECT si.id AS" + SI_ID + ", si.user_id AS "
            + USER_ID + ", u.name AS" + USER_NAME + ", si.service_order_id AS"
            + SERVICE_ORDER_ID + ",si.service_id AS" + SERVICE_ID
            + ",s.name AS" + SERVICE_NAME + ", si.status  AS" + SI_STATUS
            + "FROM users u RIGHT JOIN service_instances si on u.id = si.user_id"
            + "LEFT JOIN services s ON si.service_id = s.id"
            + "ORDER BY si.id ";
    private static final String SI_ROWS_COUNT_QUERY = "SELECT count(*) AS" + ROWS_COUNT
            + " FROM (" + SELECT_SI + ")";
    private static final String SELECT_PAGING = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (" + SELECT_SI + ") sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";

    private final Logger LOGGER
            = Logger.getLogger(OracleCSESITableDAO.class.getName());

    @Override
    protected List parseResult(ResultSet rs) {
        List rows_count = new ArrayList();
        List service_instances = new ArrayList();
        try {
            if (rs.findColumn(ROWS_COUNT) != -1) {
                try {
                    while (rs.next()) {
                        long r_count = rs.getLong(ROWS_COUNT);
                        rows_count.add(r_count);
                    }
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
                return rows_count;
            } else {
                while (rs.next()) {
                    int si_id = rs.getInt(SI_ID);
                    int user_id = rs.getInt(USER_ID);
                    String user_name = rs.getString(USER_NAME);
                    int so_id = rs.getInt(SERVICE_ORDER_ID);
                    int service_id = rs.getInt(SERVICE_ID);
                    String service_name = rs.getString(SERVICE_NAME);
                    String si_status = rs.getString(SI_STATUS);
                    SIRecord instance = new SIRecord();
                    instance.setSi_id(si_id);
                    instance.setUser_id(user_id);
                    instance.setUser_name(user_name);
                    instance.setSo_id(so_id);
                    instance.setService_id(service_id);
                    instance.setService_name(service_name);
                    instance.setSi_status(si_status);
                    service_instances.add(instance);
                }
                return service_instances;
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return null;

    }

    public long getElementCount() {
        List rows_count;
        long r_count[];
        rows_count = super.findWhere(SI_ROWS_COUNT_QUERY, new Object[]{},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        r_count = (long[]) rows_count.toArray()[0];
        return r_count[0];
    }

    public List<SIRecord> getElementsFromOffset(long count, long offset) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        List<SIRecord> service_instances = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_PAGING);
            ps.setLong(1, count + offset);
            ps.setLong(2, count);
            rs = ps.executeQuery();
            service_instances = parseResult(rs);
        } catch (SQLException ex) {
             LOGGER.error(null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                     LOGGER.error(null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return service_instances;

    }
 
}
