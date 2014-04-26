package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import com.netcracker.wind.dao.implementations.helper.AbstractDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Port;
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
public class OraclePortDAO extends AbstractDAO implements IPortDAO {

    private static final String UPDATE = "UPDATE PORTS SET FREE = ? WHERE "
            + "ID = ?";
    private static final String DELETE = "DELETE FROM PORTS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO PORTS (ID, DEVICE_ID, "
            + "FREE) VALUES (?, ?)";
    private static final String SELECT = "SELECT * FROM PORTS ";
    private static final String ID = "ID";
    private static final String DEVICE = "DEVICE_ID";
    private static final String FREE = "FREE";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();

    public void add(Port port) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, port.getId());
            stat.setInt(2, port.getDevice().getId());
            stat.setBoolean(3, port.isFree());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OraclePortDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OraclePortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idPort) {
        super.delete(DELETE, idPort);
    }

    public Port findByID(int idPort) {
        List<Port> ports = findWhere("WHERE ID = ?", new Object[]{idPort});
        if (ports.isEmpty()) {
            return null;
        } else {
            return ports.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found ports
     */
    @Override
    protected List<Port> findWhere(String where, Object[] param) {
       return super.findWhere(SELECT+where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded ports
     *
     */
    protected List<Port> parseResult(ResultSet rs) {
        List<Port> ports = new ArrayList<Port>();
        try {
            while (rs.next()) {
                Port port = new Port();
                int id = rs.getInt(ID);
                port.setId(id);
                port.setDevice(
                        factoryDAO.createDeviceDAO().findByID(rs.getInt(DEVICE))
                );
                port.setFree(rs.getBoolean(FREE));
//                port.setCircuits(factoryDAO.createCircuitDAO().findByPort(id));
                //TODO get(0) - ???
//                port.setCable(
//                        factoryDAO.createCableDAO().findByPort(id).get(0)
//                );
                ports.add(port);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OraclePortDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ports;
    }

    public void update(Port port) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setBoolean(1, port.isFree());
            stat.setInt(2, port.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OraclePortDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OraclePortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    public List<Port> findByDevice(int idDevice) {
        List<Port> ports
                = findWhere("WHERE DEVICE_ID = ?", new Object[]{idDevice});
        if (ports.isEmpty()) {
            return null;
        } else {
            return ports;
        }
    }

    public List<Port> findAll() {
        return findWhere("", new Object[]{});
    }

}
