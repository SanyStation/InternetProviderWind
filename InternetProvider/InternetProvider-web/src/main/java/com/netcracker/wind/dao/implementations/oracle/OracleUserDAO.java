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
    private static final String INSERT = "INSERT INTO USERS (ID, NAME, EMAIL, "
            + "PASSWORD, BLOCKED, ROLE_ID) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM USERS ";
    private static final String UPDATE = "UPDATE USERS SET EMAIL = ?, "
            + "PASSWORD = ?, BLOCKED WHERE ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String BLOCKED = "BLOCKED";
    private static final String ROLE = "ROLE_ID";

    /**
     *
     * @param user object for adding to database
     */
    public void add(User user) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, user.getId());
            stat.setString(2, user.getName());
            stat.setString(3, user.getEmail());
            stat.setString(4, user.getPassword());
            stat.setBoolean(5, user.getBlocked());
            stat.setInt(6, user.getRole().getId());
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
     * @param id primary key of User for deleting
     */
    public void delete(int id) {
        super.delete(DELETE, id);
    }

    /**
     *
     * @param id User's id for deleting
     * @return User with defined id when user exists in database; null if
     * object wasn't found
     */
    public User findByID(int id) {
        List<User> users = findWhere("WHERE ID = ?", new Object[]{id});
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found users
     */
    @Override
    protected List<User> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     * @param rs result return from database
     * @return list of founded users
     */
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
                users.add(user);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return users;
    }

    /**
     * updates users data such as email, password, and status(blocked/unblocked)
     *
     * @param user
     */
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

    public List<User> findByRole(int roleID) {
        List<User> users
                = findWhere("WHERE ROLES_ID = ?", new Object[]{roleID});
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = findWhere(
                "WHERE email = ?",
                new Object[]{email}
        );
        if (users.isEmpty()) {
            return null;
        } else if (users.size() == 1) {
            return users.get(0);
        }
        throw new UnsupportedOperationException("Not supported yet");
    }

    public List<User> findAll() {
        return findWhere("", new Object[]{});
    }
}
