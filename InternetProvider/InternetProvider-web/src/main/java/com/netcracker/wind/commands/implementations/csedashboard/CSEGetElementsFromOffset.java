/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.oracle.csedashboard.OracleCSESITableDAO;
import com.netcracker.wind.dao.interfaces.csedashboard.ICSEDashboardDAO;
import com.netcracker.wind.entities.csedashboard.CSEDashboardEntity;
import com.netcracker.wind.entities.csedashboard.SIRecord;
import static java.lang.Long.parseLong;
import static java.lang.Long.parseLong;
import static java.lang.Long.parseLong;
import static java.lang.Long.parseLong;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anna
 */
public class CSEGetElementsFromOffset {

    private final Logger LOGGER
            = Logger.getLogger(CSEGetElementsFromOffset.class.getName());

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //String tableName = request.getParameter("table");
        ICSEDashboardDAO<Object> dashboard = null;
        CSEDashboardEntity si = null;
        String elemFromOffset;
        long count = parseLong(request.getParameter("count"));
        long offset = parseLong(request.getParameter("offset"));

        RequestTableTypes.RequestTableType tableName = RequestTableTypes.RequestTableType.getType(request.getParameter("table"));

        switch (tableName) {
            case SI_TABLE: {
                dashboard = (ICSEDashboardDAO) new OracleCSESITableDAO();
                dashboard.getElementsFromOffset(count, offset);
                break;
            }
        }
        elemFromOffset = si.parseJSON( dashboard.getElementsFromOffset(count, offset));
        
        return  elemFromOffset;

    }
}
