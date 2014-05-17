package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetTasksByPerformerStatus;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;

/**
 * This class-command allows CSE to get list of tasks with status.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PETasksByPerformerStatus extends GetTasksByPerformerStatus implements ICommand {

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param status status of needed list of task
     * @param pageForReturn page where will be redirect after executing command.
     */
    public PETasksByPerformerStatus(Task.Status status, String pageForReturn) {
        super(status, pageForReturn);
    }

}
