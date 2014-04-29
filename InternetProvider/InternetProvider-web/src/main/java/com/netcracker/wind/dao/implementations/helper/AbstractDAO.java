/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.implementations.helper;

import com.netcracker.wind.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Oksana
 */
public abstract class AbstractDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List findWhere(String query, Object[] param) {
        List circuits = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(query);
            if (param != null) {
                for (int i = 0; i < param.length; ++i) {
                    stat.setObject(i + 1, param[i]);
                }
            }
            rs = stat.executeQuery();
            circuits = parseResult(rs);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                //TODO
                java.util.logging.Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return circuits;
    }

    public void delete(String deleteQuary, int id) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(deleteQuary);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    protected abstract List parseResult(ResultSet rs);
}
