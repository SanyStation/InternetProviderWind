package com.netcracker.wind.commands.implementations.pedashboard;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IDeviceDAO;
import com.netcracker.wind.entities.Device;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Kovriga
 */
public class DeviceReview implements ICommand {
    
    private static final String ID = "id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int deviceId = Integer.parseInt(request.getParameter(ID));
        IDeviceDAO deviceDAO = FactoryCreator.getInstance().getFactory().createDeviceDAO();
        Device device = deviceDAO.findById(deviceId);
        if (device == null) {
            return "";
        }
        request.setAttribute("device", device);
        return "/WEB-INF/pe/pe-page-device-review.jsp";
    }

}
