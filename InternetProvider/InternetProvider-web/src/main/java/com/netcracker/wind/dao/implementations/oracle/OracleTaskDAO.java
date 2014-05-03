package com.netcracker.wind.dao.implementations.oracle;

import com.netcracker.wind.connection.ConnectionPool;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.ITaskDAO;
import com.netcracker.wind.entities.Task;
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
 * @author Anatolii
 */
public class OracleTaskDAO extends AbstractOracleDAO implements ITaskDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER
            = Logger.getLogger(OracleTaskDAO.class.getName());

    private static final String DELETE = "DELETE FROM TASKS WHERE ID = ?";
    private static final String INSERT = "INSERT INTO TASKS (USER_ID, TYPE, "
            + " STATUS, ROLE_ID, SERVICE_ORDERS_ID) VALUES(?, ?, ?, ?, ?)";
//    private static final String SELECT = "SELECT * FROM TASKS ";
    private static final String SELECT = "SELECT t.*, COUNT(*) OVER () AS "
            + ROWS + " FROM tasks t ";
    private static final String SELECT_PAGING = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE role_id = ? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";
    private static final String SELECT_PAGING_USER = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE user_id = ? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";
    private static final String SELECT_PAGING_USER_STATUS = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
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

    @Override
    public void add(Task task) {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            connection = connectionPool.getConnection();
            stat = connection.prepareStatement(INSERT);
            stat.setInt(1, task.getUserId());
            stat.setString(2, task.getType().toString());
            stat.setString(3, task.getStatus().toString());
            stat.setInt(4, task.getRoleId());
            stat.setInt(5, task.getServiceOrderId());
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
    public void delete(int idTask) {
        super.delete(DELETE, idTask);
    }

    @Override
    public Task findById(int id) {
        List<Task> tasks = findWhere("WHERE ID = ?", new Object[]{id},
                DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        if (tasks.isEmpty()) {
            return null;
        } else {
            return tasks.get(0);
        }
    }

    @Override
    public void update(Task task) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = connectionPool.getConnection();
            stat = con.prepareStatement(UPDATE);
            stat.setInt(1, task.getUser().getId());
            stat.setString(2, task.getStatus().toString());
            stat.setInt(3, task.getId());
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
    protected List<Task> findWhere(String where, Object[] param,
            int pageNumber, int pageSize) {
        return super.findWhere(SELECT + where, param, pageNumber, pageSize);
    }

    @Override
    protected List<Task> parseResult(ResultSet rs) {
        List<Task> tasks = new ArrayList<Task>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(ID));
                task.setUserId(rs.getInt(USER));
                task.setType(Task.Type.valueOf(rs.getString(TYPE)));
                task.setStatus(Task.Status.valueOf(rs.getString(STATUS)));
                task.setRoleId(rs.getInt(ROLE));
                task.setServiceOrderId(rs.getInt(SO));
                super.rows = rs.getInt(ROWS);
                tasks.add(task);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }

        return tasks;
    }

    @Override
    public List<Task> findAll(int pageNumber, int pageSize) {
        return findWhere("", new Object[]{}, pageNumber, pageSize);
    }
    
    @Override
    public List<Task> findByGroup(int idGroup) {
        return findByGroup(idGroup, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
    }

    @Override
    public List<Task> findByGroup(int idGroup, int pageNumber, int pageSize) {
        List<Task> tasks = findWhere("WHERE ROLE_ID = ?",
                new Object[]{idGroup}, pageNumber, pageSize);
        return tasks;
    }

    @Override
    public List<Task> findByPerformer(int idPerformer, int pageNumber,
            int pageSize) {
        List<Task> tasks = findWhere("WHERE USER_ID = ?",
                new Object[]{idPerformer}, DEFAULT_PAGE_NUMBER, ALL_RECORDS);
        return tasks;
    }

    @Override
    public List<Task> findByPerformerStatus(int idPerformer, String status,
            int pageNumber, int pageSize) {
        List<Task> tasks = findWhere("WHERE USER_ID = ? AND STATUS= ?",
                new Object[]{idPerformer, status}, pageNumber, pageSize);
        return tasks;
    }

    @Override
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
            if (!task.getStatus().equals(Task.Status.NEW)) {
                return null;
            }
            task.setStatus(Task.Status.ACTIVE);
            psUpdate = connection.prepareStatement(UPDATE);
            psUpdate.setInt(1, userId);
            psUpdate.setString(2, task.getStatus().toString());
            psUpdate.setInt(3, task.getId());
            psUpdate.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                LOGGER.error(null, ex1);
            }
            LOGGER.error(null, ex);
        } finally {
            if (psSelect != null) {
                try {
                    psSelect.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException ex) {
                    LOGGER.error(null, ex);
                }
            }
            connectionPool.close(connection);
        }

        return task;
    }

    @Override
    public List<Task> findByTypeAndStatus(int pageNumber, int pageSize,
            Task.Type type, Task.Status... status) {
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
        return findWhere(sqlWhere.toString(), new Object[]{type, status},
                pageNumber, pageSize);
    }

    @Override
    public List<Task> findByServiceOrder(int serviceOrderId, int pageNumber,
            int pageSize) {
        List<Task> tasks = findWhere("WHERE service_order_id = ?",
                new Object[]{serviceOrderId}, pageNumber, pageSize);
        return tasks;
    }

    @Override
    public List<Task> findByUser(int userId, int pageNumber, int pageSize) {
        List<Task> tasks = findWhere("WHERE USER_ID = ?", new Object[]{userId},
                pageNumber, pageSize);
        return tasks;
    }

}
