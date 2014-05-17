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
    public static final String NAME = "name";
    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String PROVIDER_LOCATION_NAME
            = "provider_location_name";
    public static final String SERVICE_LOCATION_ID = "service_location_id";
    public static final String SERVICE_LOCATION_NAME = "service_location_name";
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_NAME = "service_name";
    public static final String COMPLETE_DATE = "complete_date";
    private static final String SELECT
            = "SELECT so.id AS " + ID + ", so.name AS " + NAME + ", pl.id AS "
            + PROVIDER_LOCATION_ID + ", pl.name AS " + PROVIDER_LOCATION_NAME
            + ", sl.id AS " + SERVICE_LOCATION_ID + ", sl.name AS "
            + SERVICE_LOCATION_NAME + ", s.id AS " + SERVICE_ID + ", s.name AS "
            + SERVICE_NAME + ", so.completedate AS " + COMPLETE_DATE + " "
            + "FROM service_orders so JOIN provider_locations pl "
            + "ON so.provider_location_id = pl.id "
            + "JOIN service_locations sl ON so.service_location_id = sl.id "
            + "JOIN services s ON so.service_id = s.id ";

    private static final String WHERE = "WHERE scenario LIKE ?";

    private static final String WHERE_FROM
            = "so.completedate >= TO_DATE(?, '" + DATE_FORMAT + "')";

    private static final String WHERE_TO
            = "so.completedate <= TO_DATE(?, '" + DATE_FORMAT + "')";

    private static final String WHERE_FULL
            = "so.completedate BETWEEN TO_DATE(?, '" + DATE_FORMAT + "') AND "
            + " TO_DATE(?, '" + DATE_FORMAT + "')";
    
    private static final String ORDER_BY = " ORDER BY so.id";

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
                String name = rs.getString(NAME);
                int providerLocationId = rs.getInt(PROVIDER_LOCATION_ID);
                String providerLocationName
                        = rs.getString(PROVIDER_LOCATION_NAME);
                int serviceLocationId = rs.getInt(SERVICE_LOCATION_ID);
                String serviceLocationName
                        = rs.getString(SERVICE_LOCATION_NAME);
                int serviceId = rs.getInt(SERVICE_ID);
                String serviceName = rs.getString(SERVICE_NAME);
                Date date = rs.getDate(COMPLETE_DATE);
                SiOrder order = new SiOrder();
                order.setId(id);
                order.setName(name);
                order.setProviderLocationId(providerLocationId);
                order.setProviderLocationName(providerLocationName);
                order.setServiceLocationId(serviceLocationId);
                order.setServiceLocationName(serviceLocationName);
                order.setServiceId(serviceId);
                order.setServiceName(serviceName);
                order.setCompleteDate(date);
                orders.add(order);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return orders;
    }

    @Override
    public void delete(String deleteQuery, int id) {
    }

    @Override
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
        query.append(ORDER_BY);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i < param.size(); ++i) {
                sdf.parse(param.get(i));
            }
            orders = super.findWhere(query.toString(), param.toArray());
        } catch (ParseException ex) {
            LOGGER.error(null, ex);
        }
        return orders;
    }

}
