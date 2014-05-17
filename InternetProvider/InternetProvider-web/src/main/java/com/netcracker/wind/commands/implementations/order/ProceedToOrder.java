package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.annotations.RolesForbidden;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.dao.interfaces.IServiceLocationDAO;
import com.netcracker.wind.dao.interfaces.IServiceOrderDAO;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.ProviderLocation;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Service;
import com.netcracker.wind.entities.ServiceLocation;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class-command allows create new Service Order with status 'ENTERING'.
 * This command is AJAX-type command and communicate with client side across
 * JSON protocol. Administrator, PE, IE cannot invoke this command.
 *
 * @author Anatolii
 */
@RolesForbidden(roles = {
    Role.Roles.Administrator,
    Role.Roles.InstallationEngineer,
    Role.Roles.ProvisioningEngineer})
public class ProceedToOrder implements ICommand {

    private static final String USER = "user";
    private static final String ERROR = "error";
    private static final String CUSTOMER_ID = "customer_id";

    private static final String X = "x";
    private static final String Y = "y";
    private static final String SERVICE_ID = "serviceId";
    private static final String ADDRESS = "address";
    private static final String AUTH = "auth";
    private static final String ORDER_ID = "order_id";
    private static final String COMMAND = "command";
    private static final String CSE_REVIEW_ORDER = "cse_review_order";
    private static final String REVIEW_ORDER = "review_order";
    private static final String NEXT_PAGE = "nextPage";

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsono = new JSONObject();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return processError(jsono);
        }
        Object paramUser = session.getAttribute(USER);
        if (paramUser == null || !(paramUser instanceof User)) {
            return processError(jsono);
        }
        User user = (User) session.getAttribute(USER);
        AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
        IServiceOrderDAO orderDAO = factoryDAO.createServiceOrderDAO();
        IServiceLocationDAO serviceLocationDAO = factoryDAO.createServiceLocationDAO();
        IProviderLocationDAO providerLocationDAO = factoryDAO.createProviderLocationDAO();
        IUserDAO userDAO = factoryDAO.createUserDAO();
        if (user.getRoleId() == Role.CSE_GROUP_ID) {
            int customerId;
            try {
                customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
            } catch (NumberFormatException exception) {
                try {
                    jsono.put(ERROR, true);
                } catch (JSONException ex) {
                    Logger.getLogger(ProceedToOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
                return jsono.toString();
            }
            user = userDAO.findById(customerId);
        }

        String sX = request.getParameter(X);
        String sY = request.getParameter(Y);
        String sID = request.getParameter(SERVICE_ID);
        String address = request.getParameter(ADDRESS);
        double actualX = Double.parseDouble(sX);
        double actualY = Double.parseDouble(sY);
        int serviceId = Integer.parseInt(sID);

        //Find nearest ProviderLocation
        List<ProviderLocation> providerLocations = providerLocationDAO.findAll(
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                AbstractOracleDAO.ALL_RECORDS);
        ProviderLocation nearestProviderLocation = OrderUtilities.findNearestProviderLocation(
                providerLocations, actualX, actualY);

        ServiceOrder order = new ServiceOrder();
        order.setEnterdate(new Timestamp(System.currentTimeMillis()));
        order.setUser(user);
        order.setService(new Service(serviceId));
        order.setProviderLocation(nearestProviderLocation);
        ServiceLocation serviceLocation = new ServiceLocation();
        serviceLocation.setPosX(actualX);
        serviceLocation.setPosY(actualY);
        serviceLocation.setAddress(address);
        serviceLocationDAO.add(serviceLocation);
        order.setServiceLocation(serviceLocation);
        order.setStatus(ServiceOrder.Status.ENTERING);
        order.setScenario(ServiceOrder.Scenario.NEW);
        orderDAO.add(order);
        try {
            jsono.put(AUTH, true);
            jsono.put(ORDER_ID, order.getId().intValue());
            if (((User) session.getAttribute(USER)).getRoleId() == Role.CSE_GROUP_ID) {
                jsono.put(COMMAND, CSE_REVIEW_ORDER);
            }
            if (((User) session.getAttribute(USER)).getRoleId() == Role.CU_GROUP_ID) {
                jsono.put(COMMAND, REVIEW_ORDER);
            }
        } catch (JSONException ex) {
            Logger.getLogger(ProceedToOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsono.toString();
    }

    private String processError(JSONObject jsono) {
        try {
            jsono.put(AUTH, false);
            jsono.put(NEXT_PAGE, ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_LOGIN));
        } catch (JSONException ex) {
            Logger.getLogger(ProceedToOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsono.toString();
    }

}
