/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IDeviceDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Device;
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
public class DeviceDAO implements IDeviceDAO{
      private ConnectionPool connectionPool;
    private static final String DELETE = "DELETE FROM DEVICES WHERE ID=?";
    private static final String INSERT = "INSERT INTO DEVICES (ID) VALUES (?)";
    private static final String SELECT = "SELECT * FROM DEVICES";
    private static final String ID = "ID";
   // private static final String UPDATE = "";
   

    public void add(Device device) {
 Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, device.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(DeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    public void delete(int idDevice) {
     Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, idDevice);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public Device findByID(int idDevice) {
    List<Device> roles = findWhere("WHERE ID=?", new Object[]{idDevice});
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
     * @return list of found devices
     */
    private List<Device> findWhere(String where, Object[] param) {
        List<Device> devices = null;
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
            devices = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(DeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return devices;
    }
/**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    private List<Device> parseResult(ResultSet rs) {
        List<Device> devices = new ArrayList<Device>();
        try {
            while (rs.next()) {
                Device device = new Device();
                int id = rs.getInt(ID);
                device.setId(id);
                device.setPortsCollection(DAOFactory.createPortDAO().findByDevice(id));
                devices.add(device);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return devices;
    }
   
    
}
