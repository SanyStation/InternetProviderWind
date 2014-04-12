/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IServiceOrderDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.ServiceOrder;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceOrderDAO implements IServiceOrderDAO {

    private ConnectionPool connectionPool;
    private static final String UPDATE = "";
    private static final String DELETE = "DELETE FROM SERVICE_ORDERS WHERE ID=?";
    private static final String INSERT = "INSERT INTO SERVICE_ORDERS (ID,ENTERDATA,PROCESDATE,"
            + "COMPLETEDATA,USER_ID,SERVICE_ID,PROVIDER_LOCATION_ID,SERVICE_LOCATION_ID,STATUS"
            + ",SCENARIO,SERVICE_INSTANCE_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM SERVICE_ORDERS";
    private static final String ID = "ID";
    private static final String ENT_D = "ENTERDATA";
    private static final String PROC_D = "PROCESDATE";
    private static final String COMP_D = "COMPLETEDATA";
    private static final String USER = "USER_ID";
    private static final String SERVICE = "SERVICE_ID";
    private static final String PLID = "PROVIDER_LOCATION_ID";
    private static final String SLID = "SERVICE_LOCATION_ID";
    private static final String STATUS = "STATUS";
    private static final String SCENARIO = "SCENARIO";
    private static final String SIID = "SERVICE_INSTANCE_ID";

    public void add(ServiceOrder serviceOrder) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, serviceOrder.getId());
            stat.setDate(2, (Date) serviceOrder.getEnterdate());
            stat.setDate(3, (Date) serviceOrder.getProcesdate());
            stat.setDate(4, (Date) serviceOrder.getCompletedate());
            stat.setInt(5, serviceOrder.getUsers().getId());
            stat.setInt(6, serviceOrder.getServices().getId());
            stat.setInt(7, serviceOrder.getProviderLocations().getId());
            stat.setInt(8, serviceOrder.getServiceLocations().getId());
            stat.setString(9, serviceOrder.getStatus());
            stat.setString(10, serviceOrder.getScenario());
            //TODO
            //stat.setInt(11,serviceOrder.getServiceInstancesCollection());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    public void delete(int id) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public ServiceOrder findByID(int idSO) {
        List<ServiceOrder> roles = findWhere("WHERE ID=?", new Object[]{idSO});
        if (roles.size() == 0) {
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
    private List<ServiceOrder> findWhere(String where, Object[] param) {
        List<ServiceOrder> roles = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(SELECT + where);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            roles = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(ServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return roles;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    private List<ServiceOrder> parseResult(ResultSet rs) {
        List<ServiceOrder> roles = new ArrayList<ServiceOrder>();
        try {
            while (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder();
                serviceOrder.setId(rs.getInt(ID));
                serviceOrder.setEnterdate(rs.getDate(ENT_D));
                serviceOrder.setProcesdate(rs.getDate(PROC_D));
                serviceOrder.setCompletedate(rs.getDate(COMP_D));
                serviceOrder.setUsers(DAOFactory.createUserDAO().findByID(rs.getInt(USER)));
                serviceOrder.setServices(DAOFactory.createServiceDAO().findByID(rs.getInt(SERVICE)));
                serviceOrder.setProviderLocations(DAOFactory.createProviderLocationDAO().findByID(rs.getInt(PLID)));
                serviceOrder.setServiceLocations(DAOFactory.createServiceLocationDAO().findByID(rs.getInt(SLID)));
                serviceOrder.setStatus(rs.getString(STATUS));
                serviceOrder.setScenario(rs.getString(SCENARIO));
                //TODO
                //serviceOrder.setServiceInstancesCollection(null);

                roles.add(serviceOrder);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(ServiceOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }


public void update(ServiceOrder role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ServiceOrder> findByProvLoc(int pLID) {
List<ServiceOrder> roles = findWhere("WHERE PROVIDER_LOCATION_ID=?", new Object[]{pLID});
        if (roles.size() == 0) {
            return null;
        } else {
            return roles;
        }    }

    public List<ServiceOrder> findByService(int idService) {
       List<ServiceOrder> roles = findWhere("WHERE SERVICE_ID=?", new Object[]{idService});
        if (roles.size() == 0) {
            return null;
        } else {
            return roles;
        }
    }
    
}
