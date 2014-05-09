package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IServiceInstanceDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.entities.ServiceInstance;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.mail.FormatedMail;
import com.netcracker.wind.mail.MailSendler;
import com.netcracker.wind.workflow.Workflow;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anatolii
 */
public class ConfirmOrder implements ICommand {

    private static final String ORDER = "order_id";
    private static final String ORDER_MESSAGE = "Boreas order information";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sOrderId = request.getParameter(ORDER);
        int orderId = Integer.parseInt(sOrderId);

        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO serviceOrderDAO = factoryDAO.createServiceOrderDAO();
        IServiceInstanceDAO serviceInstanceDAO = factoryDAO.createServiceInstanceDAO();
        ServiceOrder order = serviceOrderDAO.findById(orderId);
        if (order == null || !order.getStatus().equals(ServiceOrder.Status.ENTERING)) {
            //TODO return error page
            return "";
        }

        order.setStatus(ServiceOrder.Status.PROCESSING);
        order.setProcesdate(new Timestamp(System.currentTimeMillis()));
        if (order.getScenario().toString().equals(
                ServiceOrder.Scenario.NEW.toString())) {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setStatus(ServiceInstance.Status.PLANNED);
            serviceInstance.setUser(order.getUser());
            serviceInstance.setServiceOrder(order);
            serviceInstance.setService(order.getService());
            serviceInstanceDAO.add(serviceInstance);
            order.setServiceInstance(serviceInstance);
            Workflow.createTaskForNewScnario(order);
            List<User> users = new ArrayList<User>();
             users.add(order.getUser());
             //new MailSendler().sendEmail(users, ORDER_MESSAGE, new FormatedMail().getNewSOTakeMassage(order, order.getService(), order.getUser()));
        } else if (order.getScenario().toString().equals(
                ServiceOrder.Scenario.MODIFY.toString())) {
            ServiceInstance serviceInstance = order.getServiceInstance();
            serviceInstance.setStatus(ServiceInstance.Status.ACTIVE_MODIFY);
            serviceInstanceDAO.update(serviceInstance);
            Workflow.createTaskForModifyScenario(order);
            List<User> users = new ArrayList<User>();
             users.add(order.getUser());
            //new MailSendler().sendEmail(users, ORDER_MESSAGE, new FormatedMail().getModifySOTakeMassage(order, order.getService(), order.getUser()));
        } else if (order.getScenario().toString().equals(
                ServiceOrder.Scenario.DISCONNECT.toString())) {
            ServiceInstance serviceInstance = order.getServiceInstance();
            serviceInstance.setStatus(ServiceInstance.Status.PRE_DISCONNECTED);
            serviceInstanceDAO.update(serviceInstance);
            Workflow.createTaskForDisconnectScenario(order);
             List<User> users = new ArrayList<User>();
             users.add(order.getUser());
            //new MailSendler().sendEmail(users, ORDER_MESSAGE, new FormatedMail().getDiscSOTakeMassage(order, order.getService(), order.getUser()));
        } else {
            //TODO return error page
            return "";
        }
        serviceOrderDAO.update(order);
        //TODO redirect to next page
        return "/WEB-INF/user/cu-review-order.jsp";
    }

}
