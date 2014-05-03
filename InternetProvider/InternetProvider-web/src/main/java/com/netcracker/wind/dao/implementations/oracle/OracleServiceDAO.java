package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.entities.Service;
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
public class OracleServiceDAO extends AbstractOracleDAO implements IServiceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleServiceDAO.class.getName());
    
    private static final String UPDATE = "UPDATE SERVICES SET NAME = ?,"
            + "DESCRIPTION = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO SERVICES (ID, NAME, "
            + "DESCRIPTION) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT s.*, COUNT(*) OVER () AS "
            + ROWS + " FROM services s ";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DESCR = "DESCRIPTION";

    @Override
    public void add(Service service) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, service.getId());
            stat.setString(2, service.getName());
            stat.setString(3, service.getDescription());
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
    public void delete(int idService) {
        super.delete(DELETE, idService);
    }

    @Override
    public Service findById(int idService) {
        List<Service> services =
                findWhere("WHERE ID = ?", new Object[]{idService},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (services.isEmpty()) {
            return null;
        } else {
            return services.get(0);
        }
    }
    
    @Override
    protected List<Service> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }
    
    @Override
    protected List<Service> parseResult(ResultSet rs) {
        List<Service> services = new ArrayList<Service>();
        try {
            while (rs.next()) {
                Service service = new Service();
                int id = rs.getInt(ID);
                service.setId(id);
                service.setName(rs.getString(NAME));
                service.setDescription(rs.getString(DESCR));
                super.rows = rs.getInt(ROWS);
                services.add(service);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return services;
    }

    @Override
    public void update(Service service) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, service.getName());
            stat.setString(2, service.getDescription());
            stat.setInt(3, service.getId());
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
    public List<Service> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

}
