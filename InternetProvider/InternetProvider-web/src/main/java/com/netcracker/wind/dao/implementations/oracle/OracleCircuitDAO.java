package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.entities.Circuit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana
 */
public class OracleCircuitDAO extends AbstractOracleDAO implements ICircuitDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleCircuitDAO.class.getName());
    
    private static final String DELETE = "DELETE FROM CIRCUITS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO CIRCUITS (NAME, "
            + "SERVICE_INSTANCE_ID, PORT_ID) VALUES(?, ?, ?)";
    private static final String SELECT = "SELECT * FROM CIRCUITS ";
    private static final String UPDATE = "UPDATE CIRCUITS SET NAME = ?, "
            + "SERVICE_INSTANCE_ID = ?, PORT_ID = ? WHERE ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String SIID = "SERVICE_INSTANCE_ID";
    private static final String PORT = "PORT_ID";

    /**
     *
     * @param circuit
     */
    public void add(Circuit circuit) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, circuit.getName());
            stat.setInt(2, circuit.getServiceInstanceId());
            stat.setInt(3, circuit.getPortId());
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

    /**
     *
     * @param idCircuit
     */
    public void delete(int idCircuit) {
        super.delete(DELETE, idCircuit);
    }

    /**
     *
     * @param idCircuit
     * @return
     */
    public Circuit findByID(int idCircuit) {
        List<Circuit> circuits
                = findWhere("WHERE ID = ?", new Object[]{idCircuit});
        if (circuits.isEmpty()) {
            return null;
        } else {
            return circuits.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found circuits
     */
    @Override
    protected List<Circuit> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded circuits
     *
     */
    @Override
    protected List<Circuit> parseResult(ResultSet rs) {
        List<Circuit> circuits = new ArrayList<Circuit>();
        try {
            while (rs.next()) {
                Circuit circuit = new Circuit();
                circuit.setId(rs.getInt(ID));
                circuit.setName(rs.getString(NAME));
                circuit.setServiceInstanceId(rs.getInt(SIID));
                circuit.setPortId(rs.getInt(PORT));
                circuits.add(circuit);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return circuits;
    }

    /**
     *
     * @param circuit
     */
    public void update(Circuit circuit) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, circuit.getName());
            stat.setInt(2, circuit.getServiceInstanceId());
            stat.setInt(3, circuit.getPortId());
            stat.setInt(4, circuit.getId());
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

    /**
     *
     * @param idPort
     * @return
     */
    public Circuit findByPort(int idPort) {
        List<Circuit> circuits
                = findWhere("WHERE PORT_ID = ?", new Object[]{idPort});
        if (circuits.isEmpty()) {
            return null;
        } else {
            return circuits.get(0);
        }
    }

    /**
     *
     * @param idSi
     * @return
     */
    public Circuit findByServInst(int idSi) {
        List<Circuit> circuits
                = findWhere("WHERE SERVICE_INSTANCE_ID = ?",
                        new Object[]{idSi});
        if (circuits.size() == 1) {
            return circuits.get(0);
        } else {
            return null;
        }
    }

    public List<Circuit> findAll() {
        return findWhere("", new Object[]{});
    }
}
