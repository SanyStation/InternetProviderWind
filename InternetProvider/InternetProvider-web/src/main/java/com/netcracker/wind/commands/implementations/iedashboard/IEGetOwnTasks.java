/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class will be deleted after getting tasks unification. Now it's copypaste from CSE
 * @author myshko
 */
public class IEGetOwnTasks implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user =(User)request.getAttribute("user");
        return DashboardsUtilities.getTaskJSON(user.getId());
    }
    
}
