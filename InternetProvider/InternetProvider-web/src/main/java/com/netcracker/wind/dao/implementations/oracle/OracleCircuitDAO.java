package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.entities.Circuit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
    private static final String SELECT = "SELECT c.*, COUNT(*) OVER () AS "
            + ROWS + " FROM circuits c ";
    private static final String UPDATE = "UPDATE CIRCUITS SET NAME = ?, "
            + "SERVICE_INSTANCE_ID = ?, PORT_ID = ? WHERE ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String SIID = "SERVICE_INSTANCE_ID";
    private static final String PORT = "PORT_ID";

    @Override
    public void add(Circuit circuit) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, circuit.getName());
            stat.setInt(2, circuit.getServiceInstanceId());
            if (circuit.getPortId() > 0) {
                stat.setInt(3, circuit.getPortId());
            } else {
                stat.setNull(3, Types.INTEGER);
            }
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
    public void delete(int idCircuit) {
        super.delete(DELETE, idCircuit);
    }

    @Override
    public Circuit findById(int idCircuit) {
        List<Circuit> circuits
                = findWhere("WHERE ID = ?", new Object[]{idCircuit},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (circuits.isEmpty()) {
            return null;
        } else {
            return circuits.get(0);
        }
    }

    @Override
    protected List<Circuit> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

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
                super.rows = rs.getInt(ROWS);
                circuits.add(circuit);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return circuits;
    }

    @Override
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

    @Override
    public Circuit findByPort(int idPort) {
        List<Circuit> circuits
                = findWhere("WHERE PORT_ID = ?", new Object[]{idPort},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (circuits.isEmpty()) {
            return null;
        } else {
            return circuits.get(0);
        }
    }

    @Override
    public Circuit findByServInst(int idSi) {
        List<Circuit> circuits = findWhere("WHERE SERVICE_INSTANCE_ID = ?",
                new Object[]{idSi}, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (circuits.size() == 1) {
            return circuits.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Circuit> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

}
