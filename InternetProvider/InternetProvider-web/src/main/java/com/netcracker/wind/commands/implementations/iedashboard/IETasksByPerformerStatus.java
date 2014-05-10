/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetTasksByPerformerStatus;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class IETasksByPerformerStatus extends GetTasksByPerformerStatus implements ICommand{

    public IETasksByPerformerStatus(Task.Status status, String pageForReturn) {
        super(status, pageForReturn);
    }
    
}