package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.reports.ISiOrdersDAO;
import com.netcracker.wind.entities.reports.SiOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleSiOrdersDAO extends AbstractOracleDAO
        implements ISiOrdersDAO {

    public static final String DATE_FORMAT = "YYYY-MM-DD";
    public static final String ID = "id";
    public static final String COMPLETE_DATE = "completedate";
    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String PROVIDER_LOCATION_NAME
            = "provider_location_name";
    public static final String SERVICE_LOCATION_ID = "service_location_id";
    public static final String SERVICE_NAME = "service_name";

    public static final String SELECT
            = "SELECT so.id AS " + ID + ", " + COMPLETE_DATE + ", "
            + PROVIDER_LOCATION_ID + ", pl.name AS " + PROVIDER_LOCATION_NAME 
            + ", " + SERVICE_LOCATION_ID + ", s.name AS " + SERVICE_NAME + " "
            + "FROM service_orders so JOIN provider_locations pl "
            + "ON so.provider_location_id = pl.id "
            + "JOIN services s ON so.service_id = s.id";

    public static final String WHERE = "WHERE scenario LIKE ?";

    public static final String WHERE_FROM
            = COMPLETE_DATE + " >= TO_DATE(?, '" + DATE_FORMAT + "')";

    public static final String WHERE_TO
            = COMPLETE_DATE + " <= TO_DATE(?, '" + DATE_FORMAT + "')";

    public static final String WHERE_FULL
            = COMPLETE_DATE + " BETWEEN TO_DATE(?, '" + DATE_FORMAT + "') AND "
            + " TO_DATE(?, '" + DATE_FORMAT + "')";

    private static final Logger LOGGER
            = Logger.getLogger(OracleSiOrdersDAO.class.getName());

    private final ISiOrdersDAO.Scenario scenario;

    public OracleSiOrdersDAO(ISiOrdersDAO.Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    protected List parseResult(ResultSet rs) {
        List<SiOrder> orders = new ArrayList();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                Date date = rs.getDate(COMPLETE_DATE);
                int providerLocationId = rs.getInt(PROVIDER_LOCATION_ID);
                String providerLocationName
                        = rs.getString(PROVIDER_LOCATION_NAME);
                int serviceLocationId = rs.getInt(SERVICE_LOCATION_ID);
                String serviceName = rs.getString(SERVICE_NAME);
                SiOrder order = new SiOrder();
                order.setId(id);
                order.setCompleteDate(date);
                order.setProviderLocationId(providerLocationId);
                order.setProviderLocationName(providerLocationName);
                order.setServiceLocationId(serviceLocationId);
                order.setServiceName(serviceName);
                orders.add(order);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return orders;
    }
    
    @Override
    public void delete(String deleteQuery, int id) {}
    
    public List<SiOrder> findDateFromTo(String dateFrom, String dateTo) {
        List<SiOrder> orders = null;
        List<String> param = new ArrayList<String>();
        StringBuilder query = new StringBuilder(SELECT);
        query.append(" ");
        query.append(WHERE);
        param.add(scenario.toString());
        if (dateFrom.isEmpty() && dateTo.isEmpty()) {
        } else if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
            query.append(" AND ");
            query.append(WHERE_FULL);
            param.add(dateFrom);
            param.add(dateTo);
        } else if (dateTo.isEmpty()) {
            query.append(" AND ");
            query.append(WHERE_FROM);
            param.add(dateFrom);
        } else if (dateFrom.isEmpty()) {
            query.append(" AND ");
            query.append(WHERE_TO);
            param.add(dateTo);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i < param.size(); ++i) {
                sdf.parse(param.get(i));
            }
            orders = super.findWhere(query.toString(), param.toArray(), 
                    DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        } catch (ParseException ex) {
            LOGGER.error(null, ex);
        }
        return orders;
    }

}
