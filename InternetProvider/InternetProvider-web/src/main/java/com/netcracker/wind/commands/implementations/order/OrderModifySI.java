package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class-command allows to create Service Order in order to modify
 * particular Service Instance. Customer user can create such order for own
 * 'ACTIVE' Service Instance. CSE can create such Service Order for 'ACTIVE'
 * Service Instance for any customer user.
 *
 * @author Anatolii
 */
@RolesAllowed(roles = {Role.Roles.CustomerSupportEngineer, Role.Roles.CustomerUser})
public class OrderModifySI implements ICommand {

    private static final String SERVICE_INSTANCE_ID = "service_instance_id";
    private static final String SERVICE_ID = "service_id";
    private static final String USER = "user";
    private static final String ORDER = "order";
    private final String page;

    /**
     * Constructor for creating exemplar of this command.
     *
     * @param page page where will be redirect after executing command.
     */
    public OrderModifySI(String page) {
        this.page = page;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int serviceInstanceId = Integer.parseInt(request.getParameter(SERVICE_INSTANCE_ID));;
        int serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        IServiceDAO serviceDAO = factoryDAO.createServiceDAO();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();

        ServiceInstance serviceInstance = serviceInstanceDAO.findById(serviceInstanceId);
        User user = (User) request.getSession(false).getAttribute(USER);
        if (serviceInstance == null
                || !serviceInstance.getStatus().equals(ServiceInstance.Status.ACTIVE)
                || (user.getId() != serviceInstance.getUserId() && user.getRoleId() != Role.CSE_GROUP_ID)) {
            return manager.getProperty(ConfigurationManager.PAGE_ERROR);
        }
        Service service = serviceDAO.findById(serviceId);
        user = serviceInstance.getUser();

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Timestamp(System.currentTimeMillis()));
        order.setScenario(ServiceOrder.Scenario.MODIFY);
        order.setUser(user);
        order.setServiceInstance(serviceInstance);
        order.setStatus(ServiceOrder.Status.ENTERING);
        order.setServiceLocation(serviceInstance.getServiceOrder().getServiceLocation());
        order.setProviderLocation(serviceInstance.getServiceOrder().getProviderLocation());
        order.setService(service);
        serviceOrderDAO.add(order);
        request.setAttribute(ORDER, order);
        return page;
    }

}
