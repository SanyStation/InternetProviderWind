/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class will be deleted after getting tasks unification. Now it's copypaste from CSE
 * @author myshko
 */
public class IEGetGroupTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
       int number = Integer.parseInt(request.getParameter("size"));
       int from = Integer.parseInt(request.getParameter("from"));
       return DashboardsUtilities.getGroupTaskJSON(Role.IE_GROUP_ID, from, number);
    }
}
