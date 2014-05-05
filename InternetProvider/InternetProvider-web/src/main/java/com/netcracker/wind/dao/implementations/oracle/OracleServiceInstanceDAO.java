package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.ServiceInstance;
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
public class OracleServiceInstanceDAO extends AbstractOracleDAO
        implements IServiceInstanceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleServiceInstanceDAO.class.getName());

    private static final String UPDATE = "UPDATE SERVICE_INSTANCES SET "
            + "USER_ID = ?, SERVICE_ORDER_ID = ?, STATUS = ?, SERVICE_ID = ? "
            + "WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICE_INSTANCES WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO SERVICE_INSTANCES ("
            + "USER_ID, SERVICE_ORDER_ID, STATUS, SERVICE_ID) "
            + "VALUES(?, ?, ?, ?)";
    private static final String SELECT = "SELECT si.*, COUNT(*) OVER () AS "
            + ROWS + " FROM service_instances si ";
    private static final String ID = "id";
    private static final String STATUS = "status";
    private static final String USER_ID = "user_id";
    private static final String SERVICE_ORDER_ID = "service_order_id";
    private static final String SERVICE_ID = "service_id";

    private static final String SELECT_FOR_GET_GENERATED_ID = "SELECT * FROM "
            + "root.service_instances WHERE rowid = ?";

    @Override
    public void add(ServiceInstance serviceInstance) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stat.setInt(1, serviceInstance.getUserId());
            stat.setInt(2, serviceInstance.getServiceOrderId());
            stat.setString(3, serviceInstance.getStatus().toString());
            stat.setInt(4, serviceInstance.getServiceId());
            stat.executeUpdate();
            ResultSet insertedResultSet = stat.getGeneratedKeys();
            if (insertedResultSet != null && insertedResultSet.next()) {
                String s = insertedResultSet.getString(1);
                PreparedStatement ps = connection.prepareStatement(
                        SELECT_FOR_GET_GENERATED_ID);
                ps.setObject(1, s);
                ResultSet rs = ps.executeQuery();
                rs.next();
                serviceInstance.setId(rs.getInt(ID));
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
    public void delete(int idSi) {
        super.delete(DELETE, idSi);
    }

    @Override
    public ServiceInstance findById(int id) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE ID = ?", new Object[]{id},
                        DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts.get(0);
        }
    }

    @Override
    protected List<ServiceInstance> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<ServiceInstance> parseResult(ResultSet rs) {
        List<ServiceInstance> servInsts = new ArrayList<ServiceInstance>();
        try {
            while (rs.next()) {
                ServiceInstance servInst = new ServiceInstance();
                servInst.setId(rs.getInt(ID));
                servInst.setUserId(rs.getInt(USER_ID));
                servInst.setStatus(ServiceInstance.Status.valueOf(
                        rs.getString(STATUS)));
                servInst.setServiceOrderId(rs.getInt(SERVICE_ORDER_ID));
                servInst.setServiceId(rs.getInt(SERVICE_ID));
                super.rows = rs.getInt(ROWS);
                servInsts.add(servInst);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return servInsts;
    }

    @Override
    public void update(ServiceInstance serviceInstance) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, serviceInstance.getUserId());
            stat.setInt(2, serviceInstance.getServiceOrderId());
            stat.setString(3, serviceInstance.getStatus().toString());
            stat.setInt(4, serviceInstance.getServiceId());
            stat.setInt(5, serviceInstance.getId());
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
    public List<ServiceInstance> findByService(int idService, int pageNumber,
            int pageSize) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE SERVICE_ID = ?", new Object[]{idService},
                        pageNumber, pageSize);
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts;
        }
    }

    @Override
    public List<ServiceInstance> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }

    @Override
    public ServiceInstance findByServiceOrder(int idOrder) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE SERVICE_ORDER_ID = ?",
                        new Object[]{idOrder}, DEFAULT_PAGE_NUMBER,
                        DEFAULT_PAGE_SIZE);
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts.get(0);
        }
    }

    @Override
    public List<ServiceInstance> findByUser(int userId, int pageNumber,
            int pageSize) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE USER_ID = ?", new Object[]{userId},
                        pageNumber, pageSize);
        return servInsts;
    }

}
