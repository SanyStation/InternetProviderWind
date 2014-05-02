package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IRoleDAO;
import com.netcracker.wind.entities.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana
 */
public class OracleRoleDAO extends AbstractOracleDAO implements IRoleDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleRoleDAO.class.getName());
    
    private static final String DELETE = "DELETE FROM ROLES WHERE ID = ?";
    private static final String INSERT = "INSERT INTO ROLES (ID, NAME) "
            + "VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM ROLES ";
    private static final String UPDATE = "UPDATE ROLES SET NAME = ? WHERE "
            + "ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";

    /**
     *
     * @param role object for adding to database
     */
    public void add(Role role) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, role.getId());
            stat.setString(2, role.getName());
            stat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(connection);
        }
    }

    /**
     *
     * @param idRole
     * 
     */
    public void delete(int idRole) {
        super.delete(DELETE, idRole);
    }

    /**
     *
     * @param id id by which we will search role
     * @return Role with defined id if that role exists in database
     */
    public Role findByID(int id) {
        List<Role> roles = findWhere("WHERE ID = ?", new Object[]{id});
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
    @Override
    protected List<Role> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     * @param rs result return from database
     * @return list of founded roles
     *
     */
    protected List<Role> parseResult(ResultSet rs) {
        List<Role> roles = new ArrayList<Role>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(ID));
                role.setName(rs.getString(NAME));
                roles.add(role);
            }
        } catch (SQLException ex) {
            //TODO
            LOGGER.error(null, ex);
        }

        return roles;
    }

    public void update(Role role) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, role.getName());
            stat.setInt(2, role.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(null, ex);
            }
            connectionPool.close(con);
        }

    }

    public List<Role> findAll() {
        return findWhere("", new Object[]{});
    }
}
