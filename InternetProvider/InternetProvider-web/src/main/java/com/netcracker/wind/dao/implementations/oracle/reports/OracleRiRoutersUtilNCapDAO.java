package com.netcracker.wind.dao.implementations.oracle.reports;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.interfaces.reports.IRiRoutersUtilNCapDAO;
import com.netcracker.wind.entities.reports.RiRoutersUtilNCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Kovriga
 */
public class OracleRiRoutersUtilNCapDAO implements IRiRoutersUtilNCapDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    
    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String UTILIZATION = "utilization";
    private static final String QUERY
            = "SELECT devices.id AS " + ID + ", COUNT(ports.id) AS " + CAPACITY 
            + ", COUNT(CASE WHEN ports.free = 0 THEN 0 END) AS " + UTILIZATION
            + " FROM devices LEFT JOIN ports ON devices.id = ports.device_id "
            + "GROUP BY devices.id";

    public List<RiRoutersUtilNCap> find() {
        List<RiRoutersUtilNCap> devices = null;
        Connection con = connectionPool.getConnection();
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(QUERY);
            rs = stat.executeQuery();
            devices = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OracleRiRoutersUtilNCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleRiRoutersUtilNCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleRiRoutersUtilNCapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return devices;
    }
    
    private List<RiRoutersUtilNCap> parseResult(ResultSet rs) {
        List<RiRoutersUtilNCap> devices = new ArrayList<RiRoutersUtilNCap>();
        try {
            while (rs.next()) {
                int id = rs.getInt(ID);
                int capacity = rs.getInt(CAPACITY);
                int utilization = rs.getInt(UTILIZATION);
                RiRoutersUtilNCap device = new RiRoutersUtilNCap();
                device.setId(id);
                device.setCapacity(capacity);
                device.setUtilization(utilization);
                devices.add(device);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleRiRoutersUtilNCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return devices;
    }

}
