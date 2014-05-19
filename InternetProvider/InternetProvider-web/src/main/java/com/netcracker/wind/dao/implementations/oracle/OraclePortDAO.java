package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import com.netcracker.wind.entities.Port;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * implementation of IPortDAO  for Oracle
 * @author Oksana
 */
public class OraclePortDAO extends AbstractOracleDAO implements IPortDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OraclePortDAO.class.getName());

    private static final String UPDATE = "UPDATE PORTS SET NAME = ?, "
            + "DEVICE_ID = ?, FREE = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM PORTS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO PORTS (NAME, DEVICE_ID, "
            + "FREE) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT p.*, COUNT(*) OVER () AS "
            + ROWS + " FROM ports p ";
    private static final String PRIME_SELECT = "SELECT * FROM PORTS ";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DEVICE = "DEVICE_ID";
    private static final String FREE = "FREE";
    private static final String SEL_FREE = "SELECT ID, DEVICE_ID FROM PORTS "
            + "WHERE FREE = 1";

    @Override
    public void add(Port port) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, port.getName());
            stat.setInt(2, port.getDeviceId());
            stat.setBoolean(3, true);
            stat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(connection);
        }
    }

    @Override
    public void delete(int idPort) {
        super.delete(DELETE, idPort);
    }

    @Override
    public Port findById(int idPort) {
        List<Port> ports = findWhere("WHERE ID = ?", new Object[]{idPort},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (ports.isEmpty()) {
            return null;
        } else {
            return ports.get(0);
        }
    }

    @Override
    public Port getFreePort() {
        Connection con = null;
        PreparedStatement statSel = null;
        PreparedStatement statUpd = null;
        Port port = new Port();
        ResultSet rs = null;
        try {
            con = connectionPool.getConnection();
            con.setAutoCommit(false);
            statSel = con.prepareCall(SEL_FREE);
            rs = statSel.executeQuery();
            List<Port> ports = parseResult(rs);
            if (ports.isEmpty()) {
                return null;
            }
            port.setId(ports.get(0).getId());
            statUpd = con.prepareStatement(UPDATE);
            statUpd.setBoolean(1, false);
            statUpd.setInt(2, port.getId());
            statUpd.executeUpdate();
            con.commit();
            return port;
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            if (statSel != null) {
                try {
                    statSel.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            if (statUpd != null) {
                try {
                    statUpd.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            connectionPool.close(con);
        }
        return port;
    }

    @Override
    protected List<Port> findWhere(String where, Object[] param,
            int pageNumber, int pageSize, String orderParam,
            Direction direction) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize,
                orderParam, direction);
    }

    @Override
    protected List<Port> parseResult(ResultSet rs) {
        List<Port> ports = new ArrayList<Port>();
        try {
            super.rows = 0;
            while (rs.next()) {
                Port port = new Port();
                port.setId(rs.getInt(ID));
                port.setName(rs.getString(NAME));
                port.setDeviceId(rs.getInt(DEVICE));
                port.setFree(rs.getBoolean(FREE));
                super.rows = rs.getInt(ROWS);
                ports.add(port);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return ports;
    }

    @Override
    public void update(Port port) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, port.getName());
            stat.setInt(2, port.getDeviceId());
            stat.setBoolean(3, port.isFree());
            stat.setInt(4, port.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(con);
        }
    }

    @Override
    public List<Port> findByDevice(int deviceId, int pageNumber, int pageSize) {
        return findByDevice(deviceId, pageNumber, pageSize, "",
                Direction.ASC);
    }
    
    @Override
    public List<Port> findByDevice(int deviceId, int pageNumber, int pageSize, 
            String orderParam, Direction direction) {
        return findWhere("WHERE DEVICE_ID = ?",
                new Object[]{deviceId}, pageNumber, pageSize, orderParam,
                direction);
    }

    @Override
    public List<Port> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

    @Override
    public List<Port> findByFree(boolean isFree, int pageNumber, int pageSize) {
        return findWhere("WHERE free = ?", new Object[]{isFree}, pageNumber,
                pageSize);
    }

    @Override
    public Port occupyFreePort() {
        Connection connection = connectionPool.getConnection();
        PreparedStatement psSelect = null;
        PreparedStatement psUpdate = null;
        Port port = null;
        try {
            connection.setAutoCommit(false);
            psSelect = connection.prepareCall(PRIME_SELECT + "WHERE FREE = ?");
            psSelect.setBoolean(1, true);
            ResultSet rs = psSelect.executeQuery();
            List<Port> ports = new ArrayList<Port>();
            while (rs.next()) {
                Port portT = new Port();
                portT.setId(rs.getInt(ID));
                portT.setName(rs.getString(NAME));
                portT.setDeviceId(rs.getInt(DEVICE));
                portT.setFree(rs.getBoolean(FREE));
                ports.add(portT);
            }
            if (ports.isEmpty()) {
                return null;
            }
            port = ports.get(0);
            port.setFree(false);
            psUpdate = connection.prepareStatement(UPDATE);
            psUpdate.setString(1, port.getName());
            psUpdate.setInt(2, port.getDeviceId());
            psUpdate.setBoolean(3, port.isFree());
            psUpdate.setInt(4, port.getId());
            psUpdate.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                LOGGER.error(null, ex1);
            }
            LOGGER.error(null, ex);
        } finally {
            if (psSelect != null) {
                try {
                    psSelect.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return port;
    }

}
