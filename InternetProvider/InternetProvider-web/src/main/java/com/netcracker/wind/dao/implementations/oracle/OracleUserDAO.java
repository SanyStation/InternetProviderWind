package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Oksana and Anatolii
 */
public class OracleUserDAO extends AbstractOracleDAO implements IUserDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleUserDAO.class.getName());

    private static final String DELETE = "DELETE FROM USERS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO USERS (NAME, EMAIL, "
            + "PASSWORD, BLOCKED, ROLE_ID) VALUES(?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT u.*, COUNT(*) OVER () AS "
            + ROWS + " FROM users u ";
    private static final String UPDATE = "UPDATE USERS SET EMAIL = ?, "
            + "PASSWORD = ?, BLOCKED WHERE ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String BLOCKED = "BLOCKED";
    private static final String ROLE = "ROLE_ID";
    
    @Override
    public void add(User user) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setString(1, user.getName());
            stat.setString(2, user.getEmail());
            stat.setString(3, user.getPassword());
            stat.setBoolean(4, user.getBlocked());
            stat.setInt(5, user.getRole().getId());
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
    public void delete(int id) {
        super.delete(DELETE, id);
    }
    
    @Override
    public User findById(int id) {
        List<User> users = findWhere("WHERE ID = ?", new Object[]{id},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
    
    @Override
    protected List<User> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<User> parseResult(ResultSet rs) {
        List<User> users = new ArrayList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(ID));
                user.setName(rs.getString(NAME));
                user.setEmail(rs.getString(EMAIL));
                user.setPassword(rs.getString(PASSWORD));
                user.setBlocked(rs.getBoolean(BLOCKED));
                user.setRoleId(rs.getInt(ROLE));
                super.rows = rs.getInt(ROWS);
                users.add(user);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return users;
    }

    @Override
    public void update(User user) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setString(1, user.getEmail());
            stat.setString(2, user.getPassword());
            stat.setBoolean(3, user.getBlocked());
            stat.setInt(4, user.getId());

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
    public List<User> findByRole(int roleId, int pageNumber, int pageSize) {
        List<User> users
                = findWhere("WHERE ROLES_ID = ?", new Object[]{roleId},
                        pageNumber, pageSize);
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = findWhere("WHERE email = ?", new Object[]{email},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (users.isEmpty()) {
            return null;
        } else if (users.size() == 1) {
            return users.get(0);
        }
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public boolean hasEmail(String email) {
        List<User> users = findWhere("WHERE email = ?", new Object[]{email},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        return !users.isEmpty();
    }
    
    @Override
    public boolean hasLogin(String login) {
        List<User> users = findWhere("WHERE name = ?", new Object[]{login},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        return !users.isEmpty();
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }
    
}
