/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oksana
 */
public class CSEGetGroupTasks implements ICommand {

    private static final int CSE_GROUP_ID = 4;

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return DashboardsUtilities.getTaskJSON(CSE_GROUP_ID);
    }

}
