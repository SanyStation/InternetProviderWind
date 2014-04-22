package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.ISiOrdersDAO;
import com.netcracker.wind.entities.reports.SiOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public abstract class AbstractOracleSiDAO
        extends AbstractOracleDAO implements ISiOrdersDAO {

    public static final String DATE_FORMAT = "YYYY-MM-DD";
    public static final String ID = "id";
    public static final String COMPLETE_DATE = "completedate";
    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String SERVICE_LOCATION_ID = "service_location_id";
    public static final String SERVICE_ID = "service_id";
    public static final String STATUS = "status";
    
    public static final String SELECT
            = "SELECT " + ID + ", " + COMPLETE_DATE + ", "
            + PROVIDER_LOCATION_ID + ", " + SERVICE_LOCATION_ID + ", "
            + SERVICE_ID + ", " + STATUS + " "
            + "FROM service_orders";
    
    public static final String WHERE = "WHERE scenario LIKE ?";
    
    public static final String WHERE_FROM
            = COMPLETE_DATE + " >= TO_DATE(?, '" + DATE_FORMAT + "')";
    
    public static final String WHERE_TO
            = COMPLETE_DATE + " <= TO_DATE(?, '" + DATE_FORMAT + "')";
    
    public static final String WHERE_FULL
            = COMPLETE_DATE + " BETWEEN TO_DATE(?, '" + DATE_FORMAT + "') AND "
            + " TO_DATE(?, '" + DATE_FORMAT + "')";
    
    private final ISiOrdersDAO.Scenario scenario;

    public AbstractOracleSiDAO(ISiOrdersDAO.Scenario scenario) {
        this.scenario = scenario;
    }
        
    protected List<SiOrder> findDateFromTo(String select, String dateFrom,
            String dateTo) {
        List<SiOrder> orders = null;
        List<String> param = new ArrayList<String>();
        String where = WHERE;
        param.add(scenario.toString());
        if (dateFrom.isEmpty() && dateTo.isEmpty()) {
        } else if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
            where += " AND " + WHERE_FULL;
            param.add(dateFrom);
            param.add(dateTo);
        } else if (dateTo.isEmpty()) {
            where += " AND " + WHERE_FROM;
            param.add(dateFrom);
        } else if (dateFrom.isEmpty()) {
            where += " AND " + WHERE_TO;
            param.add(dateTo);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i < param.size(); ++i) {
                sdf.parse(param.get(i));
            }
            orders = super.findWhere(select + " " + where, param);
        } catch (ParseException ex) {
            Logger.getLogger(AbstractOracleSiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    @Override
    protected List parseResult(ResultSet rs) {
        List<SiOrder> orders = new ArrayList();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                Date date = rs.getDate(COMPLETE_DATE);
                int providerLocationId = rs.getInt(PROVIDER_LOCATION_ID);
                int serviceLocationId = rs.getInt(SERVICE_LOCATION_ID);
                int serviceId = rs.getInt(SERVICE_ID);
                String status = rs.getString(STATUS);
                SiOrder order = new SiOrder();
                order.setId(id);
                order.setCompleteDate(date);
                order.setProviderLocationId(providerLocationId);
                order.setServiceLocationId(serviceLocationId);
                order.setServiceId(serviceId);
                order.setStatus(status);
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleSiOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
}
