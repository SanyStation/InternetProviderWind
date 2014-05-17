package com.netcracker.wind.commands.implementations.csedashboard;

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
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEtasksByPerformerStatus extends GetTasksByPerformerStatus
        implements ICommand {

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param status status of needed list of task
     * @param pageForReturn page where will be redirect after executing command.
     */
    public CSEtasksByPerformerStatus(Task.Status status, String pageForReturn) {
        super(status, pageForReturn);
    }

}
