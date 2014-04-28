package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractDAO;
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
public class OracleDeviceDAO extends AbstractDAO implements IDeviceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String DELETE = "DELETE FROM DEVICES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO DEVICES (name) VALUES (?)";
    private static final String SELECT = "SELECT * FROM DEVICES ";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
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
            stat.setString(1, device.getName());
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
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param idDevice
     */
    public void delete(int idDevice) {
        super.delete(DELETE, idDevice);
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
    
    public Device findByName(String DeviceName) {
        List<Device> devices = findWhere("WHERE NAME=?", new Object[]{DeviceName});
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
    @Override
    protected List<Device> findWhere(String where, Object[] param) {
       return super.findWhere(SELECT + where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded devices
     *
     */

    protected List<Device> parseResult(ResultSet rs) {
        List<Device> devices = new ArrayList<Device>();
        try {
            while (rs.next()) {
                Device device = new Device();
                int id = rs.getInt(ID);
                device.setId(id);

                device.setName(rs.getString(NAME));
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
