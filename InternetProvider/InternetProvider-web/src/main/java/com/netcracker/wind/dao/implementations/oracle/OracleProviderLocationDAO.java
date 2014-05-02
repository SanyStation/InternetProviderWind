package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.entities.ProviderLocation;
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
public class OracleProviderLocationDAO extends AbstractOracleDAO
        implements IProviderLocationDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleProviderLocationDAO.class.getName());

    private static final String UPDATE = "UPDATE PROVIDER_LOCATIONS SET "
            + "POS_X = ?, POS_Y = ?, ADDRESS = ?, NAME = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM PROVIDER_LOCATIONS WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO PROVIDER_LOCATIONS (ID, "
            + "POS_X, POS_Y, ADDRESS, NAME) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PROVIDER_LOCATIONS ";
    private static final String ID = "ID";
    private static final String X = "POS_X";
    private static final String Y = "POS_Y";
    private static final String ADDRESS = "ADDRESS";
    private static final String NAME = "NAME";

    public void add(ProviderLocation providerLocation) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, providerLocation.getId());
            stat.setDouble(2, providerLocation.getPosX());
            stat.setDouble(3, providerLocation.getPosY());
            stat.setString(4, providerLocation.getAddress());
            stat.setString(5, providerLocation.getName());
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

    public void delete(int idPl) {
        super.delete(DELETE, idPl);
    }

    public ProviderLocation findByID(int idPl) {
        List<ProviderLocation> providerLocations
                = findWhere("WHERE ID = ?", new Object[]{idPl});
        if (providerLocations.isEmpty()) {
            return null;
        } else {
            return providerLocations.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found roles
     */
    @Override
    protected List<ProviderLocation> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    protected List<ProviderLocation> parseResult(ResultSet rs) {
        List<ProviderLocation> provLocs = new ArrayList<ProviderLocation>();
        try {
            while (rs.next()) {
                ProviderLocation provLoc = new ProviderLocation();
                int id = rs.getInt(ID);
                provLoc.setId(id);
                provLoc.setPosX(rs.getDouble(X));
                provLoc.setPosY(rs.getDouble(Y));
                provLoc.setAddress(rs.getString(ADDRESS));
                provLoc.setName(rs.getString(NAME));
                provLocs.add(provLoc);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return provLocs;
    }

    public void update(ProviderLocation providerLocation) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setDouble(1, providerLocation.getPosX());
            stat.setDouble(2, providerLocation.getPosY());
            stat.setString(3, providerLocation.getAddress());
            stat.setString(4, providerLocation.getName());
            stat.setInt(5, providerLocation.getId());
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

    public List<ProviderLocation> findAll() {
        return findWhere("", new Object[]{});
    }
}
