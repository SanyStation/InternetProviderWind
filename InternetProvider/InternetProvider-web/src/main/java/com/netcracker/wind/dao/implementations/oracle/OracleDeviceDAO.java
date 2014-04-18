package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.entities.Device;
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
 * @author Oksana
 */
public class OracleDeviceDAO implements IDeviceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String DELETE = "DELETE FROM DEVICES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO DEVICES (ID) VALUES (?)";
    private static final String SELECT = "SELECT * FROM DEVICES ";
    private static final String ID = "ID";
    // private static final String UPDATE = "";

    /**
     *
     * @param device
     */
    public void add(Device device) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, device.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param idDevice
     */
    public void delete(int idDevice) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(DELETE);
            stat.setInt(1, idDevice);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    /**
     *
     * @param idDevice
     * @return
     */
    public Device findByID(int idDevice) {
        List<Device> devices = findWhere("WHERE ID=?", new Object[]{idDevice});
        if (devices.isEmpty()) {
            return null;
        } else {
            return devices.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found devices
     */
    private List<Device> findWhere(String where, Object[] param) {
        List<Device> devices = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(SELECT + where);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            devices = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return devices;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded devices
     *
     */
    private List<Device> parseResult(ResultSet rs) {
        List<Device> devices = new ArrayList<Device>();
        try {
            while (rs.next()) {
                Device device = new Device();
                int id = rs.getInt(ID);
                device.setId(id);
                //device.setPortsList(DAOFactory.createPortDAO().findByDevice(id));
                devices.add(device);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OracleDeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return devices;
    }

    public List<Device> findAll() {
        return findWhere("", new Object[]{});
    }

}
