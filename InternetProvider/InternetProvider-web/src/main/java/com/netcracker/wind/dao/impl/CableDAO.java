/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.*;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Cable;
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
public class CableDAO implements ICableDAO {

    private ConnectionPool connectionPool;
    private static final String DELETE = "DELETE FROM CABLES WHERE ID=?";
    private static final String INSERT = "INSERT INTO CABLES (ID,PORT_ID,SERVICE_LOCATION_ID) VALUES(?,?,?)";
    private static final String SELECT = "SELECT * FROM CABLES ";
    //TODO check this with bd
    private static final String UPDATE = "UPDATE CABLES SET PORT_ID=?,SERVICE_INSTANCE_ID=? WHERE ID=?";
    private static final String ID = "ID";
    private static final String PORT = "PORT_ID";
    private static final String SIID = "SERVICE_INSTANCE_ID";

    public void add(Cable cable) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, cable.getId());
            stat.setInt(2, cable.getPorts().getId());
            // do we need to add to to tables
            stat.setInt(3, cable.getServiceLocation().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    public void delete(int idCable) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, idCable);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public Cable findByID(int idCable) {
        List<Cable> cables = findWhere("WHERE ID=?", new Object[]{idCable});
        if (cables.isEmpty()) {
            return null;
        } else {
            return cables.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found roles
     */
    private List<Cable> findWhere(String where, Object[] param) {
        List<Cable> roles = null;
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
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(CableDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    private List<Cable> parseResult(ResultSet rs) {
        List<Cable> cables = new ArrayList<Cable>();
        try {
            while (rs.next()) {
                Cable cable = new Cable();
                cable.setId(rs.getInt(ID));
                cable.setPorts(DAOFactory.createPortDAO().findByID(rs.getInt(PORT)));
                cable.setServiceLocation(DAOFactory.createServiceLocationDAO().findByID(rs.getInt(SIID)));

                cables.add(cable);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(CableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cables;
    }

    public void update(Cable cable) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setInt(1, cable.getPorts().getId());
            stat.setInt(2, cable.getServiceLocation().getId());
            stat.setInt(3, cable.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CableDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }

    }

    public List<Cable> findByPort(int idPort) {
        List<Cable> cables = findWhere("WHERE PORT_ID=?", new Object[]{idPort});
        if (cables.isEmpty()) {
            return null;
        } else {
            return cables;
        }
    }

    public List<Cable> findByServInst(int idSI) {
        List<Cable> cables = findWhere("WHERE SERVICE_INSTANCE_ID=?", new Object[]{idSI});
        if (cables.isEmpty()) {
            return null;
        } else {
            return cables;
        }
    }
}
