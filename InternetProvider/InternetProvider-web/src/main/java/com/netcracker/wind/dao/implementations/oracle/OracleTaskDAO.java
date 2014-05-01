package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.factory.implementations.OracleDAOFactory;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
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
 * @author Oksana & Anatolii
 */
public class OracleTaskDAO extends AbstractOracleDAO implements ITaskDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final AbstractFactoryDAO factoryDAO = new OracleDAOFactory();
    private static final String DELETE = "DELETE FROM TASKS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO TASKS (USER_ID, TYPE,"
            + " STATUS, ROLE_ID, SERVICE_ORDERS_ID) VALUES(?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM TASKS ";
    private static final String SELECT_PAGING = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE role_id = ? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";
     private static final String SELECT_PAGING_USER= "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE user_id = ? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";
      private static final String SELECT_PAGING_USER_STATUS= "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE user_id = ? and status=? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";
    private static final String UPDATE = "UPDATE TASKS SET USER_ID = ?, "
            + "STATUS = ? WHERE ID = ?";
    private static final String ID = "ID";
    private static final String USER = "USER_ID";
    private static final String STATUS = "STATUS";
    private static final String ROLE = "ROLE_ID";
    private static final String TYPE = "TYPE";
    private static final String SO = "SERVICE_ORDERS_ID";

    public void add(Task task) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, task.getUser().getId());
            stat.setString(2, task.getType());
            stat.setString(3, task.getStatus());
            stat.setInt(4, task.getRole().getId());
            stat.setInt(5, task.getServiceOrder().getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            //TODO changer logger
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(connection);
        }
    }

    public void delete(int idTask) {
        super.delete(DELETE, idTask);
    }

    public Task findByID(int id) {
        List<Task> tasks = findWhere("WHERE ID = ?", new Object[]{id});
        if (tasks.isEmpty()) {
            return null;
        } else {
            return tasks.get(0);
        }
    }

    /**
     * allows to update task's user and task's status
     *
     * @param task
     */
    public void update(Task task) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, task.getUser().getId());
            stat.setString(2, task.getStatus());
            stat.setInt(3, task.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionPool.close(con);
        }
    }

    /**
     *
     * @param where SQL statement where for searching by different parameters
     * @param param parameters by which search will be formed
     * @return list of found tasks
     */
    @Override
    protected List<Task> findWhere(String where, Object[] param) {
        return super.findWhere(SELECT + where, param);
    }

    /**
     *
     * @param rs result return from database
     * @return list of founded tasks
     */
    protected List<Task> parseResult(ResultSet rs) {
        List<Task> tasks = new ArrayList<Task>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(ID));
                task.setUser(
                        factoryDAO.createUserDAO().findByID(rs.getInt(USER))
                );
                task.setType(rs.getString(TYPE));
                task.setStatus(rs.getString(STATUS));
                task.setRole(
                        factoryDAO.createRoleDAO().findByID(rs.getInt(ROLE)));
                task.setServiceOrder(
                        factoryDAO.createServiceOrderDAO().findByID(rs.getInt(SO)));
                tasks.add(task);

            }
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(OracleUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tasks;
    }

    public List<Task> findAll() {
        return findWhere("", new Object[]{});
    }

    public List<Task> findByGroup(int idGroup, int from, int number) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        List<Task> tasks = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_PAGING);
            ps.setInt(1, idGroup);
            ps.setInt(2, from + number);
            ps.setInt(3, from);
            rs = ps.executeQuery();
            tasks = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return tasks;

    }

    public List<Task> findByGroup(int idGroup) {
        List<Task> tasks = findWhere("WHERE ROLE_ID = ?", new Object[]{idGroup});
        return tasks;

    }

    public List<Task> findByPerformer(int idPerformer) {
        List<Task> tasks = findWhere("WHERE USER_ID = ?", new Object[]{idPerformer});
        return tasks;
    }

    public List<Task> findByPerformerStatus(int idPerformer, String status) {
        List<Task> tasks = findWhere("WHERE USER_ID = ? AND STATUS= ?", new Object[]{idPerformer, status});
        return tasks;
    }

    public Task occupyTaskByID(int taskId, int userId) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement psSelect = null;
        PreparedStatement psUpdate = null;
        Task task = null;
        try {
            connection.setAutoCommit(false);
            psSelect = connection.prepareCall(SELECT + "WHERE ID = ?");
            psSelect.setInt(1, taskId);
            ResultSet rs = psSelect.executeQuery();
            List<Task> tasks = parseResult(rs);
            if (tasks.isEmpty()) {
                return null;
            }
            task = tasks.get(0);
            if (!task.getStatus().equals(Task.TaskStatus.NEW.toString())) {
                return null;
            }
            task.setStatus(Task.TaskStatus.ACTIVE.toString());
            psUpdate = connection.prepareStatement(UPDATE);
            psUpdate.setInt(1, userId);
            psUpdate.setString(2, task.getStatus());
            psUpdate.setInt(3, task.getId());
            psUpdate.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (psSelect != null) {
                try {
                    psSelect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return task;
    }

    public List<Task> findByTypeAndStatus(String type, String... status) {
        StringBuilder sqlWhere = new StringBuilder("WHERE type = ? AND (");
        for (int i = 0; i < status.length - 1; i++) {
            sqlWhere.append("status = ? OR ");
        }
        sqlWhere.append("status = ?)");
        Object[] parameters = new Object[status.length + 1];
        parameters[0] = type;
        for (int i = 1; i < parameters.length; i++) {
            parameters[i] = status[i - 1];
        }
        return findWhere(sqlWhere.toString(), new Object[]{type, status});
    }

    public List<Task> findByPerformer(int idPerformer, int from, int number) {

    Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        List<Task> tasks = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_PAGING_USER);
            ps.setInt(1, idPerformer);
            ps.setInt(2, from + number);
            ps.setInt(3, from);
            rs = ps.executeQuery();
            tasks = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return tasks;

    }

    public List<Task> findByPerformerStatus(int idPerformer, String status, int from, int number) {
       
    Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        List<Task> tasks = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SELECT_PAGING_USER_STATUS);
            ps.setInt(1, idPerformer);
            ps.setString(2,status);
            ps.setInt(3, from + number);
            ps.setInt(4, from);
            
            rs = ps.executeQuery();
            tasks = parseResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OracleTaskDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connectionPool.close(connection);
        }
        return tasks;
    }

   
}
