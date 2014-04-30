package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceOrder;
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
public class OracleServiceOrderDAO extends AbstractOracleDAO implements IServiceOrderDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();
    private static final String UPDATE = "UPDATE SERVICE_ORDERS SET ENTERDATE = ?,"
            + "PROCESDATE = ?, COMPLETEDATE = ?, USER_ID = ?, SERVICE_ID = ?,"
            + "PROVIDER_LOCATION_ID = ?, SERVICE_LOCATION_ID = ?, STATUS = ?,"
            + "SCENARIO = ?, SERVICE_INSTANCE_ID = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICE_ORDERS WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO SERVICE_ORDERS ("
            + "ENTERDATE, PROCESDATE, COMPLETEDATE, USER_ID, SERVICE_ID, "
            + "PROVIDER_LOCATION_ID, SERVICE_LOCATION_ID, STATUS, SCENARIO"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM SERVICE_ORDERS ";
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
            stat.setString(8, serviceOrder.getStatus());
            stat.setString(9, serviceOrder.getScenario());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OracleServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    @Override
    public void delete(int idSO) {
        super.delete(DELETE, idSO);
    }

    @Override
    public ServiceOrder findByID(int idSo) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE ID = ?", new Object[]{idSo});
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found serviceOrders
     */
    @Override
    protected List<ServiceOrder> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /*
     *
     * @param rs result return from database
     * @return list of founded serviceOrders
     *
     */
    protected List<ServiceOrder> parseResult(ResultSet rs) {
        List<ServiceOrder> serviceOrders = new ArrayList<ServiceOrder>();
        try {
            while (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder();
                serviceOrder.setId(rs.getInt(ID));
                serviceOrder.setEnterdate(rs.getTimestamp(ENT_D));
                serviceOrder.setProcesdate(rs.getTimestamp(PROC_D));
                serviceOrder.setCompletedate(rs.getTimestamp(COMP_D));
                serviceOrder.setUser(
                        factoryDAO.createUserDAO().findByID(rs.getInt(USER)));
                serviceOrder.setService(
                        factoryDAO.createServiceDAO().findByID(
                                rs.getInt(SERVICE)));
                serviceOrder.setProviderLocation(factoryDAO.
                        createProviderLocationDAO().findByID(rs.getInt(PLID)));
                serviceOrder.setServiceLocation(factoryDAO.
                        createServiceLocationDAO().findByID(rs.getInt(SLID)));
                serviceOrder.setServiceInstance(factoryDAO.
                        createServiceInstanceDAO().findByID(rs.getInt(SIID)));
                serviceOrder.setScenario(rs.getString(SCENARIO));
                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OracleServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            ps.setInt(4, serviceOrder.getUser().getId());
            ps.setInt(5, serviceOrder.getService().getId());
            ps.setInt(6, serviceOrder.getProviderLocation().getId());
            ps.setInt(7, serviceOrder.getServiceLocation().getId());
            ps.setString(8, serviceOrder.getStatus());
            ps.setString(9, serviceOrder.getScenario());
            ps.setInt(10, serviceOrder.getServiceInstance().getId());
            ps.setInt(11, serviceOrder.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OracleServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connectionPool.close(connection);
        }
    }

    @Override
    public List<ServiceOrder> findByProvLoc(int plId) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE PROVIDER_LOCATION_ID = ?", new Object[]{plId});
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders;
        }
    }

    @Override
    public List<ServiceOrder> findByService(int idService) {
        List<ServiceOrder> serviceOrders
                = findWhere("WHERE SERVICE_ID = ?", new Object[]{idService});
        if (serviceOrders.isEmpty()) {
            return null;
        } else {
            return serviceOrders;
        }
    }

    @Override
    public List<ServiceOrder> findAll() {
        return findWhere("", new Object[]{});
    }

}
