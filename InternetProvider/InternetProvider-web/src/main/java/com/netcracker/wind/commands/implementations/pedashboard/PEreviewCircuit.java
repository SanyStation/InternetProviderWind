package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows PE to review the circuit that assigned on 
 * defined device.
 * 
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PEreviewCircuit implements ICommand {

    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));
        ICircuitDAO circuitDAO
                = FactoryCreator.getInstance().getFactory().createCircuitDAO();
        Circuit circuit = circuitDAO.findById(id);
        if (circuit == null) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        request.setAttribute("circuit", circuit);
        return manager.getProperty(ConfigurationManager.PAGE_PE_REVIEW_CIRCUIT);
    }

}
