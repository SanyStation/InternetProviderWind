package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.entities.Role;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEgroupTasks extends GetGroupTasks implements ICommand {

    public CSEgroupTasks(int groupId, String pageForReturn) {
        super(groupId, pageForReturn);
    }

}
