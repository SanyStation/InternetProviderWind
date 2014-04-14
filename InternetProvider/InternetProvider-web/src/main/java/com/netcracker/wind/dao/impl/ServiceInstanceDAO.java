/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IServiceInstanceDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
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
public class ServiceInstanceDAO implements IServiceInstanceDAO {

    private ConnectionPool connectionPool;
    private static final String UPDATE = "UPDATE SERVICE_INSTANCES SET STATUS=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM SERVICE_INSTANCES WHERE ID=?";
    private static final String INSERT = "INSERT INTO SERVICE_INSTANCES (ID,USER_ID,SERVICE_ORDER_ID,STATUS,SERVICE_ID) VALUES(?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM SERVICE_INSTANCES ";
    private static final String ID = "ID";
    private static final String USER = "USER_ID";
    private static final String SO = "SERVICE_ORDER_ID";
    private static final String STATUS = "STATUS";
    private static final String SERVICE = "SERVICE_ID";

    public void add(ServiceInstance serviceInstance) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, serviceInstance.getId());
            stat.setInt(2, serviceInstance.getUsers().getId());
            stat.setInt(3, serviceInstance.getServiceOrders().getId());
            stat.setString(4, serviceInstance.getStatus());
            stat.setInt(5, serviceInstance.getServices().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(ServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    public void delete(int idSI) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, idSI);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public ServiceInstance findByID(int idSI) {
        List<ServiceInstance> servInsts = findWhere("WHERE ID=?", new Object[]{idSI});
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
     * @return list of found servInsts
     */
    private List<ServiceInstance> findWhere(String where, Object[] param) {
        List<ServiceInstance> servInsts = null;
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
            servInsts = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(ServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return servInsts;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded servInsts
     *
     */
    private List<ServiceInstance> parseResult(ResultSet rs) {
        List<ServiceInstance> servInsts = new ArrayList<ServiceInstance>();
        try {
            while (rs.next()) {
                ServiceInstance servInst = new ServiceInstance();
                int id = rs.getInt(ID);
                servInst.setId(id);
                servInst.setUsers(DAOFactory.createUserDAO().findByID(rs.getInt(USER)));
                servInst.setServiceOrders(DAOFactory.createServiceOrderDAO().findByID(rs.getInt(SO)));
                servInst.setStatus(rs.getString(STATUS));
                servInst.setServices(DAOFactory.createServiceDAO().findByID(rs.getInt(SERVICE)));
                //Need refactor
                //servInst.setServiceOrders(DAOFactory.createCableDAO().findByServInst(id));
                //TODO get(0) - ???
                servInst.setCircuit(DAOFactory.createCircuitDAO().findByServInst(id).get(0));
                servInsts.add(servInst);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(ServiceInstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return servInsts;
    }

    public void update(ServiceInstance serviceInstance) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setString(1, serviceInstance.getStatus());
            stat.setInt(2, serviceInstance.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionPool.close(con);
        }

    }

    public List<ServiceInstance> findByService(int idService) {
        List<ServiceInstance> servInsts = findWhere("WHERE SERVICE_ID=?", new Object[]{idService});
        if (servInsts.isEmpty()) {
            return null;
        } else {
            return servInsts;
        }
    }

}
