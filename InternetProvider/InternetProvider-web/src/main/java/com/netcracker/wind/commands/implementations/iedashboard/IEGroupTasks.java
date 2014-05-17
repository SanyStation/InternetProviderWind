/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.entities.Role;

/**
 * This class-command allows IE to get list of new task for IE group.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class IEGroupTasks extends GetGroupTasks implements ICommand {

    /**
     * Constructor for creating exemplar of this class.
     *
     * @param pageForReturn page where will be redirect after executing command.
     */
    public IEGroupTasks(String pageForReturn) {
        super(Role.IE_GROUP_ID, pageForReturn);
    }

}
