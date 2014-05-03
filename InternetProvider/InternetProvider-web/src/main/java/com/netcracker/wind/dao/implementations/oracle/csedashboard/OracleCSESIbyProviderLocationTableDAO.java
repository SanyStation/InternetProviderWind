/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.implementations.oracle.csedashboard;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import com.netcracker.wind.entities.csedashboard.SIRecordByProviderLocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Anna
 */
public class OracleCSESIbyProviderLocationTableDAO extends AbstractOracleDAO implements ICSEDashboardDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    public static final String ROWS_COUNT = "rows_count";
    public static final String PROVIDER_LOCATION_ID = "Provider_location_id";
    public static final String SI_ID = "SI_id";
    public static final String SI_STATUS = "SI_status";
    public static final String SERVICE_ORDER_ID = "Service_order_id";
    public static final String SERVICE_ID = "Service_id";
    public static final String SERVICE_NAME = "Service_name";
    private static final String SELECT_SI_BY_PROVIDER_LOCATION = "SELECT distinct p_l.id AS "
            + PROVIDER_LOCATION_ID + ", si.id AS" + SI_ID + ", si.status AS "
            + SI_STATUS + ", si.service_order_id AS " + SERVICE_ORDER_ID + ", si.service_id AS "
            + SERVICE_ID + ", s.name AS " + SERVICE_NAME
            + "FROM provider_locations p_l  JOIN service_orders so ON"
            + " p_l.id= so.provider_location_id "
            + "JOIN services s ON so.service_id = s.id "
            + "JOIN service_instances si ON s.id = si.service_id "
            + "AND si.service_order_id=so.id"
            + " where p_l.id= ? "
            + " ORDER BY  p_l.id ";

    private static final String SI_ROWS_COUNT_QUERY = "SELECT count(*) AS" + ROWS_COUNT
            + " FROM (" + SELECT_SI_BY_PROVIDER_LOCATION + ")";
    private static final String SELECT_PAGING = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (" + SELECT_SI_BY_PROVIDER_LOCATION + ") sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";

    private final Logger LOGGER
            = Logger.getLogger(OracleCSESIbyProviderLocationTableDAO.class.getName());

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
                    int provLoc_id = rs.getInt(PROVIDER_LOCATION_ID);
                    int si_id = rs.getInt(SI_ID);
                    String si_status = rs.getString(SI_STATUS);
                    int so_id = rs.getInt(SERVICE_ORDER_ID);
                    int service_id = rs.getInt(SERVICE_ID);
                    String service_name = rs.getString(SERVICE_NAME);
                    SIRecordByProviderLocation instance = new SIRecordByProviderLocation();
                    instance.setProvLoc_id(provLoc_id);
                    instance.setSi_id(si_id);
                    instance.setSi_status(si_status);
                    instance.setSo_id(so_id);
                    instance.setService_id(service_id);
                    instance.setService_name(service_name);
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

    public List getElementsFromOffset(long count, long offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
