package com.netcracker.wind.dao.implementations;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.entities.Service;
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
public class ServiceDAO implements IServiceDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String UPDATE = "UPDATE SERVICES SET NAME = ?,"
            + "DESCRIPTION = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM SERVICES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO SERVICES (ID, NAME, "
            + "DESCRIPTION) VALUES (?, ?, ?)";
    private static final String SELECT = "SELECT * FROM SERVICES ";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DESCR = "DESCRIPTION";

    public void add(Service service) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, service.getId());
            stat.setString(2, service.getName());
            stat.setString(3, service.getDescription());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idService) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(DELETE);
            stat.setInt(1, idService);
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    public Service findByID(int idService) {
        List<Service> services =
                findWhere("WHERE ID = ?", new Object[]{idService});
        if (services.isEmpty()) {
            return null;
        } else {
            return services.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found services
     */
    private List<Service> findWhere(String where, Object[] param) {
        List<Service> services = null;
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
            services = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                rs.close();
            } catch (SQLException ex) {
                //TODO
                Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
        return services;
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded services
     *
     */
    private List<Service> parseResult(ResultSet rs) {
        List<Service> services = new ArrayList<Service>();
        try {
            while (rs.next()) {
                Service service = new Service();
                int id = rs.getInt(ID);
                service.setId(id);
                service.setName(rs.getString(NAME));
                service.setDescription(rs.getString(DESCR));
                //service.setPricesList(DAOFactory.createPriceDAO().findByService(id));
                // service.setServiceInstancesList(DAOFactory.createServiceInstanceDAO().findByService(id));
                // service.setServiceOrdersList(DAOFactory.createServiceOrderDAO().findByService(id));
                services.add(service);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return services;
    }

    public void update(Service service) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, service.getName());
            stat.setString(2, service.getDescription());
            stat.setInt(3, service.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    public List<Service> findAll() {
        return findWhere("", new Object[]{});
    }

}
