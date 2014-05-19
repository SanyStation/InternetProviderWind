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
 * implementation of IUserDAO  for Oracle
 * @author Oksana
 * @author Anatolii
 */
public class OracleUserDAO extends AbstractOracleDAO implements IUserDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleUserDAO.class.getName());

    private static final String DELETE = "DELETE FROM USERS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO USERS (NAME, EMAIL, "
            + "PASSWORD, BLOCKED, ROLE_ID) VALUES(?, ?, md5(?), ?, ?)";
    private static final String SELECT = "SELECT u.*, COUNT(*) OVER () AS "
            + ROWS + " FROM users u ";
    private static final String UPDATE = "UPDATE USERS SET EMAIL = ?, "
            + "BLOCKED = ? WHERE ID = ?";
    private static final String UPDATE_PASS
            = "UPDATE USERS SET PASSWORD = md5(?) WHERE ID = ?";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String BLOCKED = "BLOCKED";
    private static final String ROLE = "ROLE_ID";

    @Override
    public int add(User user) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stat.setString(1, user.getName());
            stat.setString(2, user.getEmail());
            stat.setString(3, user.getPassword());
            stat.setBoolean(4, user.isBlocked());
            stat.setInt(5, user.getRole().getId());
            stat.executeUpdate();
            ResultSet insertedResultSet = stat.getGeneratedKeys();
            if (insertedResultSet != null && insertedResultSet.next()) {
                String s = insertedResultSet.getString(1);
                PreparedStatement ps = connection.prepareStatement("SELECT * "
                        + "FROM users WHERE rowid = ?");
                ps.setString(1, s);
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(ID);
            }
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
        return -1;
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
            int pageNumber, int pageSize, String orderParam, 
            Direction direction) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize,
                orderParam, direction);
    }

    @Override
    protected List<User> parseResult(ResultSet rs) {
        List<User> users = new ArrayList<User>();
        try {
            super.rows = 0;
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
            stat.setBoolean(2, user.isBlocked());
            stat.setInt(3, user.getId());

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
        return this.findByRole(roleId, pageNumber, pageSize, "", Direction.ASC);
    }
    
    @Override
    public List<User> findByRole(int roleId, int pageNumber, int pageSize,
            String orderParam, Direction direction) {
        List<User> users
                = findWhere("WHERE ROLE_ID = ?", new Object[]{roleId},
                        pageNumber, pageSize, orderParam, direction);
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
        return this.findAll(pageNumber, pageSize, "", Direction.ASC);
    }
    
    @Override
    public List<User> findAll(int pageNumber, int pageSize, String orderParam,
            Direction direction) {
        return findWhere("", new Object[]{}, pageNumber, pageSize, orderParam,
                direction);
    }

    public List<User> findByRole(int roleID) {
        return this.findByRole(roleID, "", Direction.ASC);
    }
    
    public List<User> findByRole(int roleID, String orderPara,
            Direction direction) {
        List<User> users
                = findWhere("WHERE ROLE_ID = ?", new Object[]{roleID},
                        DEFAULT_PAGE_NUMBER, ALL_RECORDS, orderPara, direction);
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    public int updatePass(User user) {
        Connection con = null;
        PreparedStatement stat = null;
        int result = -1;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE_PASS);
            stat.setString(1, user.getPassword());
            stat.setInt(2, user.getId());
            result = stat.executeUpdate();
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
        return result;
    }

}
