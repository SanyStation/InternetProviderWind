package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class OrderDisconnectSI implements ICommand {

    private static final String SERVICE_INSTANCE_ID = "service_instance_id";
    private static final String USER = "user";
    private static final String ORDER = "order";
    private final String page;

    public OrderDisconnectSI(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int serviceInstanceId = Integer.parseInt(request.getParameter(SERVICE_INSTANCE_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();

        ServiceInstance serviceInstance = serviceInstanceDAO.findById(serviceInstanceId);
        User user = (User) request.getSession(false).getAttribute(USER);
        if (serviceInstance == null
                || !serviceInstance.getStatus().equals(ServiceInstance.Status.ACTIVE)
                || (user.getId() != serviceInstance.getUserId() && user.getRoleId() != Role.CSE_GROUP_ID)) {
            //TODO return error page
            return "";
        }
        ServiceOrder oldOrder = serviceInstance.getServiceOrder();

        user = serviceInstance.getUser();

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Timestamp(System.currentTimeMillis()));
        order.setScenario(ServiceOrder.Scenario.DISCONNECT);
        order.setUser(user);
        order.setServiceInstance(serviceInstance);
        order.setStatus(ServiceOrder.Status.ENTERING);
        order.setProviderLocation(oldOrder.getProviderLocation());
        order.setServiceLocation(oldOrder.getServiceLocation());
        serviceOrderDAO.add(order);
        request.setAttribute(ORDER, order);
        return page;
    }

}
