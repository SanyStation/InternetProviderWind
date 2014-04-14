/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IRoleDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Role;
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
public class RoleDAO implements IRoleDAO {

    private ConnectionPool connectionPool;
    private static final String DELETE = "DELETE FROM ROLES WHERE ID=?";
    private static final String INSERT = "INSERT INTO ROLES (ID,NAME) VALUES(?,?)";
    private static final String SELECT = "SELECT * FROM ROLES ";
    private static final String UPDATE = "UPDATE ROLES SET NAME=? WHERE ID=?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";

    /**
     *
     * @param role object for adding to database
     */
    public void add(Role role) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, role.getId());
            stat.setString(2, role.getName());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param id primary key by which object will be deleted
     */
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

    /**
     *
     * @param id id by which we will search role
     * @return Role with defined id if that role exists in database
     */
    public Role findByID(int id) {
        List<Role> roles = findWhere("WHERE ID=?", new Object[]{id});
        if (roles.isEmpty()) {
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
    private List<Role> findWhere(String where, Object[] param) {
        List<Role> roles = null;
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
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    private List<Role> parseResult(ResultSet rs) {
        List<Role> roles = new ArrayList<Role>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(ID));
                role.setName(rs.getString(NAME));
                role.setUsersList(DAOFactory.createUserDAO().findByRole(role.getId()));
                roles.add(role);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }

    public void update(Role role) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setString(1, role.getName());
            stat.setInt(2, role.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionPool.close(con);
        }

    }
}
