package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.IRiMostProfRouterDAO;
import com.netcracker.wind.entities.reports.RiMostProfRouter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
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
            System.out.println("### DEBUG: devices.size() = " + devices.size());
            if (devices.size() > 0) {
                throw new Exception("Fucking error");
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(OracleRiMostProfRouterDAO.class.getName()).log(Level.ALL, null, sqlex);
        } catch (Exception ex) {
            Logger.getLogger(OracleRiMostProfRouterDAO.class.getName()).log(Level.ALL, null, ex);
        }
        return devices;
    }

}
