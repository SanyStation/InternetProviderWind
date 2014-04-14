/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IPortDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Port;
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
public class PortDAO implements IPortDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String UPDATE = "UPDATE PORTS SET FREE=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM PORTS WHERE ID=?";
    private static final String INSERT = "INSERT INTO PORTS (ID,DEVICE_ID,FREE) VALUES (?,?)";
    private static final String SELECT = "SELECT * FROM PORTS ";
    private static final String ID = "ID";
    private static final String DEVICE = "DEVICE_ID";
    private static final String FREE = "FREE";

    public void add(Port port) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, port.getId());
            stat.setInt(2, port.getDevices().getId());
            stat.setBoolean(3, port.getFree());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idPort) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(DELETE);
            stat.setInt(1, idPort);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    public Port findByID(int idPort) {

        List<Port> ports = findWhere("WHERE ID=?", new Object[]{idPort});
        if (ports.isEmpty()) {
            return null;
        } else {
            return ports.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found roles
     */
    private List<Port> findWhere(String where, Object[] param) {
        List<Port> ports = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(SELECT + where);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            ports = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return ports;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    private List<Port> parseResult(ResultSet rs) {
        List<Port> ports = new ArrayList<Port>();
        try {
            while (rs.next()) {
                Port port = new Port();
                int id = rs.getInt(ID);
                port.setId(id);
                port.setDevices(DAOFactory.createDeviceDAO().findByID(rs.getInt(DEVICE)));
                port.setFree(rs.getBoolean(FREE));
                port.setCircuits(DAOFactory.createCircuitDAO().findByPort(id));
                //TODO get(0) - ???
                port.setCable(DAOFactory.createCableDAO().findByPort(id).get(0));
                ports.add(port);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ports;
    }

    public void update(Port port) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setBoolean(1, port.getFree());
            stat.setInt(2, port.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PortDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    public List<Port> findByDevice(int idDevice) {

        List<Port> ports = findWhere("WHERE DEVICE_ID=?", new Object[]{idDevice});
        if (ports.isEmpty()) {
            return null;
        } else {
            return ports;
        }
    }

}
