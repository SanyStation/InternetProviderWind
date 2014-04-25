package com.netcracker.wind.dao.implementations.oracle.reports;

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

    public static final String DATE_FORMAT = "YYYY-MM-DD";
    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String SERVICE_ID = "service_id";
    public static final String SUM = "sum";

    public static final String QUERY
            = "SELECT so." + PROVIDER_LOCATION_ID + ", so." + SERVICE_ID + ", "
            + "SUM(p.price) AS " + SUM + " "
            + "FROM service_orders so INNER JOIN service_instances si ON "
            + "si.service_order_id = so.id INNER JOIN prices p ON "
            + "so.provider_location_id = p.provider_location_id "
            + "AND so.service_id = p.service_id "
            + "WHERE completedate <= TO_DATE(?, 'YYYY-MM-DD') "
            + "AND si.status = 'active' "
            + "GROUP BY so.provider_location_id, so.service_id "
            + "ORDER BY sum DESC";
    
    private final Logger logger =
            Logger.getLogger(OracleSiProfitDAO.class.getName());

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
            orders = super.findWhere(QUERY, param);
        } catch (ParseException ex) {
            logger.error(null, ex);
        }
        return orders;
    }

    @Override
    protected List<SiProfit> parseResult(ResultSet rs) {
        List<SiProfit> profits = new ArrayList<SiProfit>();
        try {
            while (rs.next()) {
                int providerLocationId = rs.getInt(PROVIDER_LOCATION_ID);
                int serviceId = rs.getInt(SERVICE_ID);
                double sum = rs.getDouble(SUM);
                SiProfit sp = new SiProfit();
                sp.setProviderLocationId(providerLocationId);
                sp.setServiceId(serviceId);
                sp.setSum(sum);
                profits.add(sp);
            }
        } catch (SQLException ex) {
            logger.error(null, ex);
        }
        return profits;
    }

}
