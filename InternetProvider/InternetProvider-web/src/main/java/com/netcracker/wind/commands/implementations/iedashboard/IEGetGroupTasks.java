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
 *
 * @author myshko
 */
public class IEGetGroupTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return DashboardsUtilities.getTaskJSON(Role.IE_GROUP_ID);
    }
    
}
