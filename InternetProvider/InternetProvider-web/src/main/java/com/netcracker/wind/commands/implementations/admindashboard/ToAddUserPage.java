package com.netcracker.wind.commands.implementations.admindashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command redirects to administrators page "Add User"
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.Administrator)
public class ToAddUserPage implements ICommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        return manager.getProperty(ConfigurationManager.PAGE_ADM_ADD_USER);
    }

}
