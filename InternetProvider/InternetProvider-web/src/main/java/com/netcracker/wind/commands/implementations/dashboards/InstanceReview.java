package com.netcracker.wind.commands.implementations.dashboards;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class InstanceReview implements ICommand {

    private static final String ID = "id";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String parameter = request.getParameter(ID);
        if(parameter == null){
            return "";
        }
        int instanceId = Integer.parseInt(parameter);
        IServiceInstanceDAO serviceInstanceDAO = FactoryCreator.getInstance().getFactory().createServiceInstanceDAO();
        ServiceInstance serviceInstance = serviceInstanceDAO.findById(instanceId);
        if (serviceInstance == null) {
            return "";
        }
        request.setAttribute("instance", serviceInstance);
        return "/WEB-INF/user/cu-page-review-instance.jsp";
    }

}
