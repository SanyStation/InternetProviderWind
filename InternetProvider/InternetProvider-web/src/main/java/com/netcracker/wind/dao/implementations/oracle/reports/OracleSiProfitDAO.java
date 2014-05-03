package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.reports.ISiProfitDAO;
import com.netcracker.wind.entities.reports.SiProfit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleSiProfitDAO extends AbstractOracleDAO
        implements ISiProfitDAO {
    
    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String PROVIDER_LOCATION_NAME
            = "provider_location_name";
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_NAME = "service_name";
    public static final String SUM = "sum";
    public static final String QUERY
            = "SELECT pl.id AS " + PROVIDER_LOCATION_ID + ", "
            + "pl.name AS " + PROVIDER_LOCATION_NAME + ", "
            + "s.id AS " + SERVICE_ID + ", s.name AS " + SERVICE_NAME + ", "
            + "SUM(p.price) AS " + SUM + " "
            + "FROM service_orders so JOIN service_instances si "
            + "ON si.service_order_id = so.id "
            + "JOIN prices p "
            + "ON so.provider_location_id = p.provider_location_id "
            + "AND so.service_id = p.service_id "
            + "JOIN provider_locations pl ON so.provider_location_id = pl.id "
            + "JOIN services s ON so.service_id = s.id "
            + "WHERE completedate <= TO_DATE(?, 'YYYY-MM-DD') "
            + "AND si.status = 'active' "
            + "GROUP BY pl.id, pl.name, s.id, s.name "
            + "ORDER BY sum DESC";

    private final Logger LOGGER
            = Logger.getLogger(OracleSiProfitDAO.class.getName());

    @Override
    protected List<SiProfit> parseResult(ResultSet rs) {
        List<SiProfit> profits = new ArrayList<SiProfit>();
        try {
            while (rs.next()) {
                int providerLocationId = rs.getInt(PROVIDER_LOCATION_ID);
                String providerLocationName
                        = rs.getString(PROVIDER_LOCATION_NAME);
                int serviceId = rs.getInt(SERVICE_ID);
                String serviceName = rs.getString(SERVICE_NAME);
                double sum = rs.getDouble(SUM);
                SiProfit sp = new SiProfit();
                sp.setProviderLocationId(providerLocationId);
                sp.setProviderLocationName(providerLocationName);
                sp.setServiceId(serviceId);
                sp.setServiceName(serviceName);
                sp.setSum(sum);
                profits.add(sp);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return profits;
    }
    
    @Override
    public void delete(String deleteQuery, int id) {}
    
    public List<SiProfit> findByDateTo(String dateTo) {
        List<SiProfit> orders = null;
        List<String> param = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (dateTo.isEmpty()) {
            dateTo = sdf.format(Calendar.getInstance().getTime());
        }
        dateTo += "-01";
        try {
            sdf.parse(dateTo);
            param.add(dateTo);
            orders = super.findWhere(QUERY, param.toArray(),
                    DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        } catch (ParseException ex) {
            LOGGER.error(null, ex);
        }
        return orders;
    }

}
