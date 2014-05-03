package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceDAO;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anatolii
 */
public class OrderModifySI implements ICommand {

    private static final String SERVICE_INSTANCE_ID = "service_instance_id";
    private static final String SERVICE_ID = "service_id";
    private static final String USER = "user";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int serviceInstanceId = -1;
        int serviceId = -1;
        try {
            serviceInstanceId = Integer.parseInt(request.getParameter(SERVICE_INSTANCE_ID));
            serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));
        } catch (NumberFormatException exception) {
            //TODO logging
            //TODO return error page
            return "";
        }

        HttpSession session = request.getSession();
        if (session == null || serviceInstanceId == -1 || serviceId == -1) {
            //TODO return error page
            return "";
        }
        Object paramUser = session.getAttribute(USER);
        if (paramUser == null || !(paramUser instanceof User)) {
            //TODO return error page
            return "";
        }
        User user = (User) session.getAttribute(USER);

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        IServiceDAO serviceDAO = factoryDAO.createServiceDAO();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();

        ServiceInstance serviceInstance = serviceInstanceDAO.findById(serviceInstanceId);
        Service service = serviceDAO.findById(serviceId);

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Timestamp(System.currentTimeMillis()));
        order.setScenario(ServiceOrder.Scenario.MODIFY);
        order.setUser(user);
        order.setServiceInstance(serviceInstance);
        order.setStatus(ServiceOrder.Status.ENTERING);
        order.setService(service);
        serviceOrderDAO.add(order);
        //TODO return next page
        return "";
    }

}
