package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.manager.ConfigurationManager;
import com.netcracker.wind.paging.IExtendedPaginatedList;
import com.netcracker.wind.paging.PortPaginatedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
@RolesAllowed(roles = Role.Roles.ProvisioningEngineer)
public class PEreviewDevice implements ICommand {

    private static final String ID = "id";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        int deviceId = Integer.parseInt(request.getParameter(ID));
        IDeviceDAO deviceDAO
                = FactoryCreator.getInstance().getFactory().createDeviceDAO();
        Device device = deviceDAO.findById(deviceId);
        if (device == null) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        IExtendedPaginatedList pl = new PortPaginatedList(
                AbstractOracleDAO.DEFAULT_PAGE_SIZE, deviceId);
        pl.setRequest(request);
        HttpSession hs = request.getSession(false);
        hs.setAttribute("ports", pl);
        hs.setAttribute("device", device);
        return manager.getProperty(ConfigurationManager.PAGE_PE_REVIEW_DEVICE);
    }

}
