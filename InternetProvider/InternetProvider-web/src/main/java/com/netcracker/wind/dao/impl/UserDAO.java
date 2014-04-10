/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IUserDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.User;
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
public class UserDAO implements IUserDAO {

    private static final String DELETE = "DELETE FROM USERS WHERE ID=?";

    private static final String INSERT = "INSERT INTO USERS (ID,NAME,EMAIL,PASSWORD,BLOCKED,ROLES) VALUES(?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM USERS";
    private static final String UPDATE = "UPDATE USERS SET EMAIL=? ,PASSWORD=?,BLOCKED WHERE ID=?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String BLOCKED = "BLOCKED";
    private static final String ROLE = "ROLES";
    private ConnectionPool connectionPool;

    /**
     *
     * @param user object for adding to database
     */
    public void add(User user) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, user.getId());
            stat.setString(2, user.getName());
            stat.setString(3, user.getEmail());
            stat.setString(4, user.getPassword());
            stat.setBoolean(5, user.getBlocked());
            stat.setInt(6, user.getRoles().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param id primary key of User for deleting
     */
    public void delete(int id) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    /**
     *
     * @param id User's id for deleting
     * @return User with defined id when user exists in database; null if object
     * wasn't found
     */
    public User findByID(int id) {
        List<User> users = findWhere("WHERE ID=?", new Object[]{id});
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found users
     */
    private List<User> findWhere(String where, Object[] param) {
        List<User> users = null;
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
            users = parseResult(rs);
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }

        return users;
    }

    /**
     *
     * @param rs result return from database
     * @return list of founded users
     */
    private List<User> parseResult(ResultSet rs) {
        List<User> users = new ArrayList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(ID));
                user.setName(rs.getString(NAME));
                user.setEmail(rs.getString(EMAIL));
                user.setPassword(rs.getString(PASSWORD));
                user.setBlocked(rs.getBoolean(BLOCKED));
                user.setRoles(DAOFactory.createRoleDAO().findByID(rs.getInt(ROLE)));
                users.add(user);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
/**
 * updates users data such as email, password, and status(blocked/unblocked)
 * @param user 
 */
    public void update(User user) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setString(1, user.getEmail());
            stat.setString(2, user.getPassword());
            stat.setBoolean(3, user.getBlocked());
            stat.setInt(4, user.getId());

            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionPool.close(con);
        }
    }
}
