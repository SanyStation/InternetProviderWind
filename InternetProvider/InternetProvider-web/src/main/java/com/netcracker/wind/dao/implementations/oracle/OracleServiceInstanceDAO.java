package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import com.netcracker.wind.dao.implementations.helper.AbstractDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.entities.ServiceInstance;
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
public class OracleServiceInstanceDAO extends AbstractDAO implements IServiceInstanceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();
    private static final String UPDATE = "UPDATE SERVICE_INSTANCES SET "
            + "STATUS = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICE_INSTANCES WHERE "
            + "ID = ?";
    private static final String INSERT = "INSERT INTO SERVICE_INSTANCES (ID, "
            + "USER_ID, SERVICE_ORDER_ID, STATUS, SERVICE_ID) "
            + "VALUES(?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM SERVICE_INSTANCES ";
    private static final String ID = "ID";
    private static final String USER = "USER_ID";
    private static final String SO = "SERVICE_ORDER_ID";
    private static final String STATUS = "STATUS";
    private static final String SERVICE = "SERVICE_ID";

    private static final String SELECT_FOR_GET_GENERATED_ID = "SELECT * FROM ROOT.SERVICE_INSTANCES WHERE ROWID = ?";

    public void add(ServiceInstance serviceInstance) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stat.setInt(1, serviceInstance.getId());
            stat.setInt(2, serviceInstance.getUser().getId());
            stat.setInt(3, serviceInstance.getServiceOrder().getId());
            stat.setString(4, serviceInstance.getStatus());
            stat.setInt(5, serviceInstance.getService().getId());
            stat.executeUpdate();
            ResultSet insertedResultSet = stat.getGeneratedKeys();
            if (insertedResultSet != null && insertedResultSet.next()) {
                String s = insertedResultSet.getString(1);
                PreparedStatement ps = connection.prepareStatement(SELECT_FOR_GET_GENERATED_ID);
                ps.setObject(1, s);
                ResultSet rs = ps.executeQuery();
                rs.next();
                serviceInstance.setId(rs.getInt(ID));
            }
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OracleServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idSi) {
        super.delete(DELETE, idSi);
    }

    public ServiceInstance findByID(int idSI) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE ID = ?", new Object[]{idSI});
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found service instances
     */
    @Override
    protected List<ServiceInstance> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded service instances
     *
     */
    protected List<ServiceInstance> parseResult(ResultSet rs) {
        List<ServiceInstance> servInsts = new ArrayList<ServiceInstance>();
        try {
            while (rs.next()) {
                ServiceInstance servInst = new ServiceInstance();
                int id = rs.getInt(ID);
                servInst.setId(id);
                servInst.setUser(
                        factoryDAO.createUserDAO().findByID(rs.getInt(USER))
                );
                servInst.setServiceOrder(
                        factoryDAO.createServiceOrderDAO().findByID(
                                rs.getInt(SO)
                        )
                );
                servInst.setStatus(rs.getString(STATUS));
                servInst.setService(
                        factoryDAO.createServiceDAO().findByID(
                                rs.getInt(SERVICE)
                        )
                );
                //Need refactor
                //servInst.setServiceOrders(DAOFactory.createCableDAO().findByServInst(id));
                //TODO get(0) - ???
                servInst.setCircuit(
                        factoryDAO.createCircuitDAO().findByServInst(id).get(0)
                );
                servInsts.add(servInst);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OracleServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return servInsts;
    }

    public void update(ServiceInstance serviceInstance) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, serviceInstance.getStatus());
            stat.setInt(2, serviceInstance.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OracleServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }

    }

    public List<ServiceInstance> findByService(int idService) {
        List<ServiceInstance> servInsts
                = findWhere("WHERE SERVICE_ID = ?", new Object[]{idService});
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts;
        }
    }

    public List<ServiceInstance> findAll() {
        return findWhere("", new Object[]{});
    }

    public ServiceInstance findByServiceOrderId(int idOrder) {
        List<ServiceInstance> servInsts = findWhere("WHERE SERVICE_ORDER_ID=?", new Object[]{idOrder});
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts.get(0);
        }
    }

}
