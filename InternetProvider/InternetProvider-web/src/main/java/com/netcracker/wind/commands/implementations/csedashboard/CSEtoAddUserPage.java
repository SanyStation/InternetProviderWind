package com.netcracker.wind.commands.implementations.csedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.CustomerSupportEngineer)
public class CSEtoAddUserPage implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return manager.getProperty(ConfigurationManager.PAGE_CSE_ADD_CUSTOMER);
    }

}
