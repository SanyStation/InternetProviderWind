package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.commands.implementations.dashboards.GetTasksByPerformerStatus;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEtasksByPerformerStatus extends GetTasksByPerformerStatus
        implements ICommand {

    public CSEtasksByPerformerStatus(Task.Status status, String pageForReturn) {
        super(status, pageForReturn);
    }

}
