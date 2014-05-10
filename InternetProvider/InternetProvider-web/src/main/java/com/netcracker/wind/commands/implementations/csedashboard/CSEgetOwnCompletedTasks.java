/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;

import com.netcracker.wind.entities.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Oksana
 */
@Deprecated
public class CSEgetOwnCompletedTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // User user =(User)request.getAttribute("user");
        int number = Integer.parseInt(request.getParameter("size"));
        int from = Integer.parseInt(request.getParameter("from"));
        return DashboardsUtilities.getTaskUserStatus(1002,Task.Status.COMPLETED.toString(), number, from);
    }

}
