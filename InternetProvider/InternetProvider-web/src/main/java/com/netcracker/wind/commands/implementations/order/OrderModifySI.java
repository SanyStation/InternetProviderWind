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
    private static final String ORDER = "order";

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int serviceInstanceId = Integer.parseInt(request.getParameter(SERVICE_INSTANCE_ID));;
        int serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));

        HttpSession session = request.getSession();
        if (session == null) {
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
        order.setServiceLocation(serviceInstance.getServiceOrder().getServiceLocation());
        order.setProviderLocation(serviceInstance.getServiceOrder().getProviderLocation());
        order.setService(service);
        serviceOrderDAO.add(order);
        request.setAttribute(ORDER, order);
        //TODO return next page
        return "/WEB-INF/user/cu-review-order.jsp";
    }

}
