/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Сашко
 */
public class GetActiveIETasks implements ICommand {

    private final String USER_ID = "user_id";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return DashboardsUtilities.getTaskUserStatus(1003, "ACTIVE");
    }
    
    
}
