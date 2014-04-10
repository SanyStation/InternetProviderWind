/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.IUserDAO;
import com.netcracker.wind.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oksana
 */
public class UserDAO implements IUserDAO {

    private static final String DELETE = "";

    private static final String INSERT = "INSERT INTO USERS (ID,NAME,EMAIL,PASSWORD,BLOCKED,ROLES) VALUES(?,?,?,?,?,?)";
    private static final String SELECT = "";
    private static final String UPDATE = "";
    private ConnectionPool connectionPool;

    @Override
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

}
