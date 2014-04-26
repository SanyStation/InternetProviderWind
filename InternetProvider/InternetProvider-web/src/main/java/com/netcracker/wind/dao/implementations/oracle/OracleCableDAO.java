package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import com.netcracker.wind.dao.implementations.helper.AbstractDAO;
import com.netcracker.wind.dao.interfaces.ICableDAO;
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
public class OracleCableDAO extends AbstractDAO 
implements ICableDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String DELETE = "DELETE FROM CABLES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO CABLES (ID, PORT_ID, "
            + "SERVICE_LOCATION_ID) VALUES(?, ?, ?)";
    private static final String SELECT = "SELECT * FROM CABLES ";
    //TODO check this with bd
    private static final String UPDATE = "UPDATE CABLES SET PORT_ID = ?,"
            + "SERVICE_INSTANCE_ID = ? WHERE ID = ?";
    private static final String ID = "ID";
    private static final String PORT = "PORT_ID";
    private static final String SIID = "SERVICE_INSTANCE_ID";

    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();

    public void add(Cable cable) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, cable.getId());
            stat.setInt(2, cable.getPort().getId());
            // do we need to add to the tables
            stat.setInt(3, cable.getServiceLocation().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OracleCableDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleCableDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idCable) {
        super.delete(DELETE, idCable);
    }

    /**
     *
     * @param idCable the id of cable by which search will be done
     * @return entities with idCable if it exists in database, and null -
     * otherwise
     */
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
     * @return list of found cables
     */
    @Override
    protected List<Cable> findWhere(String where, Object[] param) {
      return super.findWhere(where, param);
    }

    /**
     *
     *
     * @param rs result return from database
     * @return list of founded cables
     *
     */
    protected List<Cable> parseResult(ResultSet rs) {
        List<Cable> cables = new ArrayList<Cable>();
        try {
            while (rs.next()) {
                Cable cable = new Cable();
                cable.setId(rs.getInt(ID));
                cable.setPort(
                        factoryDAO.createPortDAO().findByID(rs.getInt(PORT))
                );
                cable.setServiceLocation(
                        factoryDAO.createServiceLocationDAO().findByID(
                                rs.getInt(SIID)
                        )
                );

                cables.add(cable);
            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OracleCableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cables;
    }

    public void update(Cable cable) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, cable.getPort().getId());
            stat.setInt(2, cable.getServiceLocation().getId());
            stat.setInt(3, cable.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OracleCableDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleCableDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }

    }

    public List<Cable> findByPort(int idPort) {
        List<Cable> cables
                = findWhere("WHERE PORT_ID = ?", new Object[]{idPort});
        if (cables.isEmpty()) {
            return null;
        } else {
            return cables;
        }
    }

    public List<Cable> findByServInst(int idSI) {
        List<Cable> cables
                = findWhere("WHERE SERVICE_INSTANCE_ID = ?", new Object[]{idSI});
        if (cables.isEmpty()) {
            return null;
        } else {
            return cables;
        }
    }

    public List<Cable> findAll() {
        return findWhere("", new Object[]{});
    }
}
