package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.entities.Role;

/**
 * This class-command allows PE to get list of new task for PE group.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PEGroupTasks extends GetGroupTasks implements ICommand {

    /**
     * Constructor for creating exemplar of this class.
     *
     * @param pageForReturn page where will be redirect after executing command.
     */
    public PEGroupTasks(String pageForReturn) {
        super(Role.PE_GROUP_ID, pageForReturn);
    }

}
