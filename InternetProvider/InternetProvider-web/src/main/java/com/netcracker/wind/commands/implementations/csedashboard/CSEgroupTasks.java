package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.entities.Role;

/**
 * This class-command allows CSE to get list of new task for CSE group.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEgroupTasks extends GetGroupTasks implements ICommand {

    /**
     * Constructor for creating exemplar of this class.
     *
     * @param pageForReturn page where will be redirect after executing command.
     */
    public CSEgroupTasks(String pageForReturn) {
        super(Role.CSE_GROUP_ID, pageForReturn);
    }

}
