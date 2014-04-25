package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.IRiMostProfRouterDAO;
import com.netcracker.wind.entities.reports.RiMostProfRouter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleRiMostProfRouterDAO extends AbstractOracleDAO
        implements IRiMostProfRouterDAO {

    private static final String ID = "device_id";
    private static final String PROFIT = "profit";
    private static final String QUERY
            = "SELECT " + ID + ", " + PROFIT + " "
            + "FROM device_profit "
            + "ORDER BY profit DESC";
    
    private static final Logger logger =
            Logger.getLogger(OracleRiMostProfRouterDAO.class.getName());

    public List<RiMostProfRouter> findAll() {
        return super.findWhere(QUERY, new ArrayList<String>());
    }
    
    @Override
    protected List<RiMostProfRouter> parseResult(ResultSet rs) {
        List<RiMostProfRouter> devices = new ArrayList<RiMostProfRouter>();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                int profit = rs.getInt(PROFIT);
                RiMostProfRouter device = new RiMostProfRouter();
                device.setId(id);
                device.setProfit(profit);
                devices.add(device);
            }
        } catch (SQLException sqlex) {
            logger.error(null, sqlex);
        }
        return devices;
    }

}
