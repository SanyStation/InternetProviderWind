package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IPriceDAO;
import com.netcracker.wind.entities.Price;
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
public class OraclePriceDAO extends AbstractOracleDAO implements IPriceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OraclePriceDAO.class.getName());
    
    private static final String UPDATE = "UPDATE PRICES SET PRICE WHERE ID = ?";
    private static final String DELETE = "DELETE FROM PRICES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO PRICES (ID, "
            + "PROVIDER_LOCATION_ID, SERVICE_ID, PRICE) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PRICES ";
    private static final String ID = "ID";
    private static final String PLID = "PROVIDER_LOCATION_ID";
    private static final String SERVICE = "SERVICE_ID";
    private static final String PRICE = "PRICE";

    /**
     *
     * @param price
     */
    public void add(Price price) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, price.getId());
            stat.setInt(2, price.getProviderLocationId());
            stat.setInt(3, price.getServiceId());
            stat.setInt(4, price.getPrice());
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
     * @param idPrice
     */
    public void delete(int idPrice) {
         super.delete(DELETE, idPrice);
    }

    /**
     *
     * @param idPrice
     * @return
     */
    public Price findByID(int idPrice) {
        List<Price> roles = findWhere("WHERE ID = ?", new Object[]{idPrice},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (roles.isEmpty()) {
            return null;
        } else {
            return roles.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found roles
     */
    protected List<Price> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    protected List<Price> parseResult(ResultSet rs) {
        List<Price> prices = new ArrayList<Price>();
        try {
            while (rs.next()) {
                Price price = new Price();
                price.setId(rs.getInt(ID));
                price.setProviderLocationId(rs.getInt(PLID));
                price.setServiceId(rs.getInt(SERVICE));
                price.setPrice(rs.getInt(PRICE));
                prices.add(price);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return prices;
    }

    /**
     *
     * @param price
     */
    public void update(Price price) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, price.getPrice());
            stat.setInt(2, price.getId());
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
     * @param idPLoc
     * @return
     */
    public List<Price> findByProviderLoc(int idPLoc) {
        List<Price> prices = findWhere("WHERE PROVIDER_LOCATION_ID = ?",
                new Object[]{idPLoc}, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (prices.isEmpty()) {
            return null;
        } else {
            return prices;
        }
    }

    /**
     *
     * @param idService
     * @return
     */
    public List<Price> findByService(int idService) {
        List<Price> prices =
                findWhere("WHERE SERVICE_ID = ?", new Object[]{idService},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (prices.isEmpty()) {
            return null;
        } else {
            return prices;
        }
    }

    public List<Price> findAll() {
        return findWhere("", new Object[]{}, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
    }

}
