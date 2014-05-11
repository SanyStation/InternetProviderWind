package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.entities.ServiceLocation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana
 */
public class OracleServiceLocationDAO extends AbstractOracleDAO
        implements IServiceLocationDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleServiceLocationDAO.class.getName());

    private static final String UPDATE = "UPDATE SERVICE_LOCATIONS SET "
            + "POS_X = ?, POS_Y = ?, ADDRESS = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICE_LOCATIONS WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO SERVICE_LOCATIONS "
            + "(POS_X, POS_Y, ADDRESS) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT sl.*, COUNT(*) OVER () AS "
            + ROWS + " FROM service_locations sl ";
    private static final String ID = "ID";
    private static final String X = "POS_X";
    private static final String Y = "POS_Y";
    private static final String ADDRESS = "ADDRESS";

    @Override
    public void add(ServiceLocation serviceLocation) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            stat.setDouble(1, serviceLocation.getPosX());
            stat.setDouble(2, serviceLocation.getPosY());
            stat.setString(3, serviceLocation.getAddress());
            stat.executeUpdate();
            ResultSet insertedResultSet = stat.getGeneratedKeys();
            if (insertedResultSet != null && insertedResultSet.next()) {
                String s = insertedResultSet.getString(1);
                PreparedStatement ps = connection.prepareStatement("SELECT * "
                        + "FROM root.service_locations WHERE rowid = ?");
                ps.setObject(1, s);
                ResultSet rs = ps.executeQuery();
                rs.next();
                serviceLocation.setId(rs.getInt(ID));
            }
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
    public void delete(int idSL) {
        super.delete(DELETE, idSL);
    }

    @Override
    public ServiceLocation findById(int id) {
        List<ServiceLocation> serviceLocations
                = findWhere("WHERE ID = ?", new Object[]{id},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (serviceLocations.isEmpty()) {
            return null;
        } else {
            return serviceLocations.get(0);
        }
    }
    
    @Override
    protected List<ServiceLocation> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<ServiceLocation> parseResult(ResultSet rs) {
        List<ServiceLocation> provLocs = new ArrayList<ServiceLocation>();
        try {
            super.rows = 0;
            while (rs.next()) {
                ServiceLocation provLoc = new ServiceLocation();
                int id = rs.getInt(ID);
                provLoc.setId(id);
                provLoc.setPosX(rs.getInt(X));
                provLoc.setPosY(rs.getInt(Y));
                provLoc.setAddress(rs.getString(ADDRESS));
                super.rows = rs.getInt(ROWS);
                provLocs.add(provLoc);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return provLocs;
    }

    @Override
    public void update(ServiceLocation serviceLocation) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setDouble(1, serviceLocation.getPosX());
            stat.setDouble(2, serviceLocation.getPosY());
            stat.setString(3, serviceLocation.getAddress());
            stat.setInt(4, serviceLocation.getId());
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
    public List<ServiceLocation> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

}
