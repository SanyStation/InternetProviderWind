/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IServiceLocationDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.ServiceLocation;
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
public class ServiceLocationDAO implements IServiceLocationDAO {
    //Need add one field cable
    private ConnectionPool connectionPool;
    private static final String UPDATE = "UPDATE SERVICE_LOCATIONS SET POS_X=?,POS_Y=?,ADDRESS=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM SERVICE_LOCATIONS WHERE ID=?";
    private static final String INSERT = "INSERT INTO _LOCATIONS (ID,POS_X,POS_Y,ADDRESS) VALUES (?,?,?,?)";
    private static final String SELECT = "SELECT * FROM ROVIDER_LOCATIONS ";
    private static final String ID = "ID";
    private static final String X = "POS_X";
    private static final String Y = "POS_Y";
    private static final String ADDRESS = "ADDRESS";

    public void add(ServiceLocation serviceLocation) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, serviceLocation.getId());
            stat.setInt(2, serviceLocation.getPosX());
            stat.setInt(3, serviceLocation.getPosY());
            stat.setString(4, serviceLocation.getAddress());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(ProviderLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProviderLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public ServiceLocation findByID(int id) {
        List<ServiceLocation> serviceLocations = findWhere("WHERE ID=?", new Object[]{id});
        if (serviceLocations.isEmpty()) {
            return null;
        } else {
            return serviceLocations.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found roles
     */
    private List<ServiceLocation> findWhere(String where, Object[] param) {
        List<ServiceLocation> provLocs = null;
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
            provLocs = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(ServiceLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return provLocs;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    private List<ServiceLocation> parseResult(ResultSet rs) {
        List<ServiceLocation> provLocs = new ArrayList<ServiceLocation>();
        try {
            while (rs.next()) {
                ServiceLocation provLoc = new ServiceLocation();
                int id = rs.getInt(ID);
                provLoc.setId(id);
                provLoc.setPosX(rs.getInt(X));
                provLoc.setPosY(rs.getInt(Y));
                provLoc.setAddress(rs.getString(ADDRESS));
                provLoc.setServiceOrdersList(DAOFactory.createServiceOrderDAO().findByProvLoc(id));
                provLocs.add(provLoc);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(ServiceLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return provLocs;
    }

    public void update(ServiceLocation serviceLocation) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setInt(1, serviceLocation.getPosX());
            stat.setInt(2, serviceLocation.getPosY());
            stat.setString(3, serviceLocation.getAddress());
            stat.setInt(4, serviceLocation.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionPool.close(con);
        }
    }

}
