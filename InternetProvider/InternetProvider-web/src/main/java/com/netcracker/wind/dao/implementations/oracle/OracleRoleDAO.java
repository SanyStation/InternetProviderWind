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
    private static final String SELECT = "SELECT r.*, COUNT(*) OVER () AS "
            + ROWS + " FROM roles r ";
    private static final String UPDATE = "UPDATE ROLES SET NAME = ? WHERE "
            + "ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";

    @Override
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

    @Override
    public void delete(int idRole) {
        super.delete(DELETE, idRole);
    }
    
    @Override
    public Role findById(int id) {
        List<Role> roles = findWhere("WHERE ID = ?", new Object[]{id},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (roles.isEmpty()) {
            return null;
        } else {
            return roles.get(0);
        }
    }
    
    @Override
    protected List<Role> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<Role> parseResult(ResultSet rs) {
        List<Role> roles = new ArrayList<Role>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(ID));
                role.setName(rs.getString(NAME));
                super.rows = rs.getInt(ROWS);
                roles.add(role);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return roles;
    }

    @Override
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

    @Override
    public List<Role> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }
    
}
