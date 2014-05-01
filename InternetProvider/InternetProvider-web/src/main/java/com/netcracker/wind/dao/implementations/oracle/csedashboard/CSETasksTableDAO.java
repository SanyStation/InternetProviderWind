/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.dao.implementations.oracle.csedashboard;

import com.netcracker.wind.dao.implementations.oracle.OracleUserDAO;
import com.netcracker.wind.dao.implementations.oracle.reports.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.entities.csedashboard.TaskRecord;
import com.netcracker.wind.entities.reports.RiMostProfRouter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Anna
 */
public class CSETasksTableDAO extends AbstractOracleDAO implements ICSEDashboardDAO<TaskRecord> {

    public static final String ROWS_COUNT = "rows_count";
    private static final String ROWS_COUNT_QUERY = "SELECT count(*) AS" + ROWS_COUNT + " FROM";
    private static final String SELECT_PAGING = "SELECT * FROM (SELECT ROWNUM rownumber, sub.*"
            + "  FROM (SELECT * FROM tasks WHERE role_id = ? ORDER BY ID) sub "
            + "WHERE ROWNUM <= ?) WHERE rownumber > ?";

    private final Logger LOGGER
            = Logger.getLogger(CSETasksTableDAO.class.getName());

    @Override
    protected List parseResult(ResultSet rs) {
        List rows_count = new ArrayList();
        try {
            while (rs.next()) {
                long r_count = rs.getLong(ROWS_COUNT);
                rows_count.add(r_count);
            }
        } catch (SQLException ex) {
            LOGGER.error(null, ex);
        }
        return rows_count;
    }

    public long getElementCount() {
        List rows_count;
        long r_count[];
        super.findWhere(ROWS_COUNT_QUERY, new ArrayList<String>());
        rows_count = super.findWhere(ROWS_COUNT_QUERY, new ArrayList<String>());
        r_count = (long[]) rows_count.toArray()[0];
        return r_count[0];
    }

    public List<TaskRecord> getElementsFromOffset(long count, long offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
