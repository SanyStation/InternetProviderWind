/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao;

import com.netcracker.wind.commands.connection.ConnectionPool;
import com.netcracker.wind.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oksana
 */
public class UserDAO {

    private static final String DELETE = "";

    private static final String INSERT = "INSERT INTO USERS (ID,NAME,EMAIL,PASSWORD,BLOCKED,ROLES) VALUES(?,?,?,?,?,?)";
    private static final String SELECT = "";
    private static final String UPDATE = "";
    private ConnectionPool connection;

    public void add(Users user) {
        Connection con = null;
        try {
            con = connection.getConnection();
            PreparedStatement stat = con.prepareStatement(INSERT);
            stat.setShort(1, user.getId());
            stat.setString(2, user.getName());
            stat.setString(3, user.getEmail());
            stat.setString(4, user.getPassword());
            //How to put char?
            stat.setString(5, user.getBlocked().toString());
            stat.setInt(6, user.getRoles().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close(con);
        }
    }

}
