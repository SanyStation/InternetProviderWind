/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;


import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.csedashboard.RequestTableTypes.RequestTableType;
import com.netcracker.wind.dao.implementations.oracle.csedashboard.OracleCSESITableDAO;
import com.netcracker.wind.dao.implementations.oracle.csedashboard.OracleCSESIbyProviderLocationTableDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anna
 */
public class CSEgetElementsCount implements ICommand {

    private final Logger LOGGER
            = Logger.getLogger(CSEgetElementsCount.class.getName());

   

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //String tableName = request.getParameter("table");
        ICSEDashboardDAO<Object> dashboard;
        long rows_count = 0;
        RequestTableType tableName = RequestTableType.getType(request.getParameter("table"));

        switch (tableName) {
            case SI_TABLE: {
                dashboard = (ICSEDashboardDAO) new OracleCSESITableDAO();
                rows_count = dashboard.getElementCount();
                break;
            }
        }
        JSONObject rowsCountJSON = new JSONObject();
        try {
            rowsCountJSON.put("count", rows_count);

        } catch (JSONException ex) {
            LOGGER.error(null, ex);
        }
        return rowsCountJSON.toString();

    }
}
