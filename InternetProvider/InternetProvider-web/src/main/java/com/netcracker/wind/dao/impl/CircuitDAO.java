/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.impl;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.ICircuitDAO;
import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Circuit;
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
public class CircuitDAO implements ICircuitDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String DELETE = "DELETE FROM CIRCUITS WHERE ID=?";
    private static final String INSERT = "INSERT INTO CIRCUITS (ID,SERVICE_INSTANCE_ID,PORT_ID) VALUES(?,?,?)";
    private static final String SELECT = "SELECT * FROM CIRCUITS ";
    private static final String UPDATE = "UPDATE CIRCUITS SET SERVICE_INSTANCE_ID=?,PORT_ID=? WHERE ID=?";
    private static final String ID = "ID";
    private static final String SIID = "SERVICE_INSTANCE_ID";
    private static final String PORT = "PORT_ID";

    /**
     *
     * @param circuit
     */
    public void add(Circuit circuit) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, circuit.getId());
            stat.setInt(2, circuit.getServiceInstance().getId());
            stat.setInt(3, circuit.getPorts().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param idCircuit
     */
    public void delete(int idCircuit) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(DELETE);
            stat.setInt(1, idCircuit);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    /**
     *
     * @param idCircuit
     * @return
     */
    public Circuit findByID(int idCircuit) {
        List<Circuit> roles = findWhere("WHERE ID=?", new Object[]{idCircuit});
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
    private List<Circuit> findWhere(String where, Object[] param) {
        List<Circuit> roles = null;
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
                Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    private List<Circuit> parseResult(ResultSet rs) {
        List<Circuit> roles = new ArrayList<Circuit>();
        try {
            while (rs.next()) {
                Circuit circuit = new Circuit();
                circuit.setId(rs.getInt(ID));
                circuit.setServiceInstance(DAOFactory.createServiceInstanceDAO().findByID(rs.getInt(SIID)));
                circuit.setPorts(DAOFactory.createPortDAO().findByID(rs.getInt(PORT)));
                roles.add(circuit);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roles;
    }

    /**
     *
     * @param circuit
     */
    public void update(Circuit circuit) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, circuit.getServiceInstance().getId());
            stat.setInt(2, circuit.getPorts().getId());
            stat.setInt(3, circuit.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CircuitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }

    }

    /**
     *
     * @param idPort
     * @return
     */
    public Circuit findByPort(int idPort) {
        List<Circuit> circuits = findWhere("WHERE PORT_ID=?", new Object[]{idPort});
        if (circuits.isEmpty()) {
            return null;
        } else {
            return circuits.get(0);
        }
    }

    /**
     *
     * @param idSI
     * @return
     */
    public List<Circuit> findByServInst(int idSI) {
        List<Circuit> roles = findWhere("WHERE SERVICE_INSTANCE_ID=?", new Object[]{idSI});
        if (roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }
    }

    public List<Circuit> findAll() {
        return findWhere("", null);
    }
}
