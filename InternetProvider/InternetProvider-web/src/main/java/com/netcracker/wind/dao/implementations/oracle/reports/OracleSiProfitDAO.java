package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.ISiProfitDAO;
import com.netcracker.wind.entities.reports.SiProfit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleSiProfitDAO extends AbstractOracleDAO
        implements ISiProfitDAO {

    public static final String PROVIDER_LOCATION_ID = "provider_location_id";
    public static final String SERVICE_ID = "service_id";
    public static final String COMPLETE_DATE = "completedate";
    public static final String DATE_FORMAT = "YYYY-MM-DD";
    public static final String SUM = "sum";

    public static final String SELECT
            = "SELECT so." + PROVIDER_LOCATION_ID + ", so." + SERVICE_ID
            + ", SUM(p.price) AS " + SUM + " "
            + "FROM service_orders so "
            + "INNER JOIN prices p ON so.service_id = p.service_id "
            + "AND so.provider_location_id = p.provider_location_id";

    public static final String WHERE_FROM
            = "WHERE " + COMPLETE_DATE + " >= TO_DATE(?, '"
            + DATE_FORMAT + "')";

    public static final String WHERE_TO
            = "WHERE " + COMPLETE_DATE + " <= TO_DATE(?, '"
            + DATE_FORMAT + "')";

    public static final String WHERE_FULL
            = "WHERE completedate BETWEEN TO_DATE(?, 'YYYY-MM-DD') "
            + "AND TO_DATE(?, 'YYYY-MM-DD')";

    public static final String GROUP_ORDER
            = "GROUP BY so.provider_location_id, so.service_id "
            + "ORDER BY sum DESC";

    public List<SiProfit> findByMonth(int month, int year) {
        return null;
    }

    public List<SiProfit> findByDateFromTo(String dateFrom, String dateTo) {
        List<SiProfit> orders = null;
        String where;
        List<String> param = new ArrayList<String>();
        if (dateFrom.isEmpty() && dateTo.isEmpty()) {
            where = "";
        } else if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
            where = WHERE_FULL;
            param.add(dateFrom);
            param.add(dateTo);
        } else if (dateTo.isEmpty()) {
            where = WHERE_FROM;
            param.add(dateFrom);
        } else {
            where = WHERE_TO;
            param.add(dateTo);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i < param.size(); ++i) {
                sdf.parse(param.get(i));
            }
            orders = super.findWhere(SELECT + " " + where + " " + GROUP_ORDER,
                    param);
        } catch (ParseException ex) {
            Logger.getLogger(AbstractOracleSiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public List<SiProfit> findAll() {
        return super.findWhere(SELECT + " " + GROUP_ORDER, new ArrayList());
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
            Logger.getLogger(OracleSiProfitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profits;
    }

}
