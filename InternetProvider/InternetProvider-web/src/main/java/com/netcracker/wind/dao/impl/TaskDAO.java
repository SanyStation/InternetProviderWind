/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.ITaskDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Task;
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
public class TaskDAO implements ITaskDAO{
    private static final String DELETE = "DELETE FROM TASKS WHERE ID=?";
    private static final String INSERT = "INSERT INTO TASKS (ID,USER_ID,TYPE,STATUS,ROLE_ID,SERVICE_ORDERS_ID) VALUES(?,?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM TASKS ";
    private static final String UPDATE = "UPDATE TASKS SET USER_ID=? ,STATUS=? WHERE ID=?";
    private static final String ID = "ID";
    private static final String USER = "USER_ID";
    private static final String STATUS = "STATUS";
    private static final String ROLE = "ROLE_ID";
    private static final String TYPE = "TYPE";
    private static final String SO = "SERVICE_ORDERS_ID";
    

    private ConnectionPool connectionPool;

    public void add(Task task) {
          Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement stat = connection.prepareStatement(INSERT);
            stat.setInt(1, task.getId());
            stat.setInt(2, task.getUsers().getId());
            stat.setString(3, task.getType());
            stat.setString(4, task.getStatus());
            stat.setInt(5, task.getRoles().getId());
            stat.setInt(6, task.getServiceOrders().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(connection);
        }}

    public void delete(int id) {
         Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(DELETE);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.close(con);
        }
    }

    public Task findByID(int id) {
     List<Task> tasks = findWhere("WHERE ID=?", new Object[]{id});
        if (tasks.isEmpty()) {
            return null;
        } else {
            return tasks.get(0);
        }}

    /**
     * allows to update task's user and task's status
     * @param task 
     */
    public void update(Task task) {
         Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement stat = con.prepareStatement(UPDATE);
            stat.setInt(1, task.getUsers().getId());
            stat.setString(2, task.getStatus());
            stat.setInt(3, task.getId());

            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connectionPool.close(con);
        }}
    
    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found tasks
     */
    private List<Task> findWhere(String where, Object[] param) {
        List<Task> tasks = null;
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
            tasks = parseResult(rs);
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

        return tasks;
    }

    /**
     *
     * @param rs result return from database
     * @return list of founded tasks
     */
    private List<Task> parseResult(ResultSet rs) {
        List<Task> tasks = new ArrayList<Task>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(ID));
                task.setUsers(DAOFactory.createUserDAO().findByID(rs.getInt(USER)));
                task.setType(rs.getString(TYPE));
                task.setStatus(rs.getString(STATUS));
                task.setRoles(DAOFactory.createRoleDAO().findByID(rs.getInt(ROLE)));
                //TODO 
                //task.setServiceOrders(DAOFactory.createSODAO().findByID());
               
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tasks;
    }
    
}
