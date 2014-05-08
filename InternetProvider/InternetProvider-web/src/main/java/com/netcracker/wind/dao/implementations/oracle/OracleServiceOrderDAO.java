package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
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
public class OracleServiceOrderDAO extends AbstractOracleDAO
        implements IServiceOrderDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleServiceOrderDAO.class.getName());

    private static final String UPDATE = "UPDATE SERVICE_ORDERS SET "
            + "ENTERDATE = ?, PROCESDATE = ?, COMPLETEDATE = ?, USER_ID = ?, "
            + "SERVICE_ID = ?, PROVIDER_LOCATION_ID = ?,"
            + "SERVICE_LOCATION_ID = ?, STATUS = ?, SCENARIO = ?, "
            + "SERVICE_INSTANCE_ID = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICE_ORDERS WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO SERVICE_ORDERS ("
            + "ENTERDATE, PROCESDATE, COMPLETEDATE, USER_ID, SERVICE_ID, "
            + "PROVIDER_LOCATION_ID, SERVICE_LOCATION_ID, STATUS, SCENARIO"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT so.*, COUNT(*) OVER () AS "
            + ROWS + " FROM service_orders so ";
    private static final String ID = "ID";
    private static final String ENT_D = "ENTERDATE";
    private static final String PROC_D = "PROCESDATE";
    private static final String COMP_D = "COMPLETEDATE";
    private static final String USER = "USER_ID";
    private static final String SERVICE = "SERVICE_ID";
    private static final String PLID = "PROVIDER_LOCATION_ID";
    private static final String SLID = "SERVICE_LOCATION_ID";
    private static final String STATUS = "STATUS";
    private static final String SCENARIO = "SCENARIO";
    private static final String SIID = "SERVICE_INSTANCE_ID";

    @Override
    public void add(ServiceOrder serviceOrder) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setTimestamp(1, serviceOrder.getEnterdate());
            stat.setTimestamp(2, serviceOrder.getProcesdate());
            stat.setTimestamp(3, serviceOrder.getCompletedate());
            stat.setInt(4, serviceOrder.getUser().getId());
            stat.setInt(5, serviceOrder.getService().getId());
            stat.setInt(6, serviceOrder.getProviderLocation().getId());
            stat.setInt(7, serviceOrder.getServiceLocation().getId());
            stat.setString(8, serviceOrder.getStatus().toString());
            stat.setString(9, serviceOrder.getScenario().toString());
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
    public void delete(int idSO) {
        super.delete(DELETE, idSO);
    }

    @Override
    public ServiceOrder findById(int idSo) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE ID = ?", new Object[]{idSo},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders.get(0);
        }
    }

    @Override
    protected List<ServiceOrder> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<ServiceOrder> parseResult(ResultSet rs) {
        List<ServiceOrder> serviceOrders = new ArrayList<ServiceOrder>();
        try {
            while (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder();
                serviceOrder.setId(rs.getInt(ID));
                serviceOrder.setEnterdate(rs.getTimestamp(ENT_D));
                serviceOrder.setProcesdate(rs.getTimestamp(PROC_D));
                serviceOrder.setCompletedate(rs.getTimestamp(COMP_D));
                serviceOrder.setUserId(rs.getInt(USER));
                serviceOrder.setServiceId(rs.getInt(SERVICE));
                serviceOrder.setProviderLocationId(rs.getInt(PLID));
                serviceOrder.setServiceLocationId(rs.getInt(SLID));
                serviceOrder.setServiceInstanceId(rs.getInt(SIID));
                serviceOrder.setScenario(ServiceOrder.Scenario.valueOf(
                        rs.getString(SCENARIO)));
                serviceOrder.setStatus(ServiceOrder.Status.valueOf(rs.getString(STATUS)));
                super.rows = rs.getInt(ROWS);
                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return serviceOrders;
    }

    @Override
    public void update(ServiceOrder serviceOrder) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareCall(UPDATE);
            ps.setTimestamp(1, serviceOrder.getEnterdate());
            ps.setTimestamp(2, serviceOrder.getProcesdate());
            ps.setTimestamp(3, serviceOrder.getCompletedate());
            ps.setInt(4, serviceOrder.getUserId());
            ps.setInt(5, serviceOrder.getServiceId());
            ps.setInt(6, serviceOrder.getProviderLocationId());
            ps.setInt(7, serviceOrder.getServiceLocationId());
            ps.setString(8, serviceOrder.getStatus().toString());
            ps.setString(9, serviceOrder.getScenario().toString());
            if (serviceOrder.getServiceInstanceId() > 0) {
                ps.setInt(10, serviceOrder.getServiceInstanceId());
            } else {
                ps.setNull(10, Types.INTEGER);
            }
            ps.setInt(11, serviceOrder.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            connectionPool.close(connection);
        }
    }

    @Override
    public List<ServiceOrder> findByProvLoc(int plId, int pageNumber,
            int pageSize) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE PROVIDER_LOCATION_ID = ?",
                        new Object[]{plId}, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders;
        }
    }

    @Override
    public List<ServiceOrder> findByService(int idService, int pageNumber,
            int pageSize) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE SERVICE_ID = ?", new Object[]{idService},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders;
        }
    }

    @Override
    public List<ServiceOrder> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

    @Override
    public List<ServiceOrder> findByServiceInstance(int serviceInstanceId,
            int pageNumber, int pageSize) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE SERVICE_INSTANCE_ID = ?",
                        new Object[]{serviceInstanceId},
                        pageNumber, pageSize);
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders;
        }
    }

    @Override
    public List<ServiceOrder> findByUser(int userId, int pageNumber,
            int pageSize) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE USER_ID = ?", new Object[]{userId},
                        pageNumber, pageSize);
        return serviceOrders;
    }

}
