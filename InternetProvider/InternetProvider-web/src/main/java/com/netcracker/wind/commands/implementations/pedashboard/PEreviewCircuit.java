package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.ICircuitDAO;
import com.netcracker.wind.entities.Circuit;
import com.netcracker.wind.entities.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class PEreviewCircuit implements ICommand {
    
    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));
        ICircuitDAO circuitDAO
                = FactoryCreator.getInstance().getFactory().createCircuitDAO();
        Circuit circuit = circuitDAO.findById(id);
        if (circuit == null) {
            return "";
        }
        request.setAttribute("circuit", circuit);
        return "/WEB-INF/pe/pe-page-review-circuit.jsp";
    }

}
