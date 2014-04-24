/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.DashboardsUtilities;
import com.netcracker.wind.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ProvisioningEngineerTasks implements ICommand {

    private static final int PROVISIONING_ENGINEER_TASKS = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return DashboardsUtilities.getTaskJSON(PROVISIONING_ENGINEER_TASKS);
    }

}
