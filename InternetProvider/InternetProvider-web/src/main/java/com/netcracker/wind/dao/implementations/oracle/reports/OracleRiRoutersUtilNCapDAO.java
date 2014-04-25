package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.dao.interfaces.reports.IRiRoutersUtilNCapDAO;
import com.netcracker.wind.entities.reports.RiRouterUtilNCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleRiRoutersUtilNCapDAO extends AbstractOracleDAO
        implements IRiRoutersUtilNCapDAO {

    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String UTILIZATION = "utilization";
    private static final String QUERY
            = "SELECT devices.id AS " + ID + ", COUNT(ports.id) AS " + CAPACITY
            + ", COUNT(CASE WHEN ports.free = 0 THEN 0 END) AS " + UTILIZATION
            + " FROM devices LEFT JOIN ports ON devices.id = ports.device_id "
            + "GROUP BY devices.id";
    
    private static final Logger logger =
            Logger.getLogger(OracleRiMostProfRouterDAO.class.getName());

    public List<RiRouterUtilNCap> findAll() {
         return super.findWhere(QUERY, new ArrayList<String>());
    }

    @Override
    protected List<RiRouterUtilNCap> parseResult(ResultSet rs) {
        List<RiRouterUtilNCap> devices = new ArrayList<RiRouterUtilNCap>();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                int capacity = rs.getInt(CAPACITY);
                int utilization = rs.getInt(UTILIZATION);
                RiRouterUtilNCap device = new RiRouterUtilNCap();
                device.setId(id);
                device.setCapacity(capacity);
                device.setUtilization(utilization);
                devices.add(device);
            }
        } catch (SQLException ex) {
            logger.error(null, ex);
        }
        return devices;
    }

}
