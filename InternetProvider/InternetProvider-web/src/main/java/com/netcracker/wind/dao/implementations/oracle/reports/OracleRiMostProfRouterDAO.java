package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
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

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PROFIT = "profit";
    private static final String QUERY
            = "SELECT d.id AS " + ID + ", d.name AS " + NAME + ", "
            + "SUM(prc.price) AS " + PROFIT + " "
            + "FROM devices d JOIN Ports p ON d.id = p.device_id "
            + "JOIN Circuits c ON c.Port_id = p.id "
            + "JOIN Service_Instances si ON c.Service_Instance_id = si.id "
            + "JOIN Service_Orders so ON si.Service_Order_id = so.id "
            + "JOIN Prices prc "
            + "ON prc.Provider_Location_id = so.Provider_Location_id "
            + "AND prc.Service_id = so.Service_id "
            + "WHERE p.free = 0 "
            + "GROUP BY d.id, d.name "
            + "ORDER BY profit DESC";

    private static final Logger LOGGER
            = Logger.getLogger(OracleRiMostProfRouterDAO.class.getName());

    @Override
    protected List<RiMostProfRouter> parseResult(ResultSet rs) {
        List<RiMostProfRouter> devices = new ArrayList<RiMostProfRouter>();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                String name = rs.getString(NAME);
                int profit = rs.getInt(PROFIT);
                RiMostProfRouter device = new RiMostProfRouter();
                device.setId(id);
                device.setName(name);
                device.setProfit(profit);
                devices.add(device);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return devices;
    }
    
    @Override
    public void delete(String deleteQuery, int id) {}
    
    public List<RiMostProfRouter> findAll() {
        return super.findWhere(QUERY, new Object[]{});
    }

}
