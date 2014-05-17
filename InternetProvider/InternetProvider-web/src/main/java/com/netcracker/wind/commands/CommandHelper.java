package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.dashboards.ServiceOrderReview;
import com.netcracker.wind.commands.implementations.NoCommand;
import com.netcracker.wind.commands.implementations.ProcessTask;
import com.netcracker.wind.commands.implementations.admindashboard.ToAddUserPage;
import com.netcracker.wind.commands.implementations.UnassignTask;
import com.netcracker.wind.commands.implementations.admindashboard.ADMaddUser;
import com.netcracker.wind.commands.implementations.admindashboard.ADMgetUsersList;
import com.netcracker.wind.commands.implementations.admindashboard.ADMreviewUser;
import com.netcracker.wind.commands.implementations.admindashboard.ADMsetBlockUser;
import com.netcracker.wind.commands.implementations.csedashboard.*;
import com.netcracker.wind.commands.implementations.dashboards.CUGetServiceInstanceForUser;
import com.netcracker.wind.commands.implementations.dashboards.ChangePassword;
import com.netcracker.wind.commands.implementations.dashboards.ServiceInstanceReview;
import com.netcracker.wind.commands.implementations.iedashboard.*;
import com.netcracker.wind.commands.implementations.order.*;
import com.netcracker.wind.commands.implementations.pedashboard.*;
import com.netcracker.wind.commands.implementations.registration.Registration;
import com.netcracker.wind.commands.implementations.registration.Validation;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import com.netcracker.wind.manager.ConfigurationManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * This class can be used for help main controller get right command. Class is
 * designed as singleton.
 *
 * @author Anatolii
 */
public class CommandHelper {

    private static CommandHelper commandHelper;

    private static final String COMMAND = "command";

    private static final String NO_COMMAND = "no_command";
    private static final String PROCEED_TO_ORDER = "proceed_to_order";
    private static final String REFRESH_SERVICE = "refresh_service";
    private static final String NEW_DEVICE = "new_device";
    private static final String NEW_CABLE = "new_cable";
    private static final String DEL_CABLE = "delete_cable";
    private static final String VALIDATION = "validation";
    private static final String REGISTRATION = "registration";
    private static final String ADM_ADD_USER_PAGE = "adm_add_user_page";
    private static final String PROCESS_TASK = "process_task";
    private static final String UNASSIGN_TASK = "unassign_task";

    private static final String SETUP_CIRCUIT = "setup_circuit";
    private static final String MODIFY_CIRCUIT = "modify_circuit";
    private static final String DELETE_CIRCUIT = "delete_circuit";
    private static final String CHANGE_PASSWORD = "change_password";

    private static final String ADM_ADD_USER = "adm_add_user";
    private static final String ADM_GET_USERS = "adm_get_users";
    private static final String ADM_REVIEW_USER = "adm_review_user";
    private static final String ADM_SET_BLOCK_USER = "adm_set_block_user";

    private static final String CU_INSTANCES = "cu_instances";
    private static final String CU_REVIEW_INSTANCE = "cu_review_instance";
    private static final String CU_MODIFY_INSTANCE = "modify_instance";
    private static final String CU_DISCONNECT_INSTANCE = "disconnect_instance";
    private static final String CU_ORDERS = "cu_orders";
    private static final String CU_REVIEW_ORDER = "review_order";
    private static final String CU_CONFIRM_ORDER = "confirm_order";
    private static final String CU_CANCEL_ORDER = "cancel_order";

    private static final String CSE_SEND_BILL = "send_bill";
    private static final String CSE_CUSTOMERS_LIST = "customers_list";
    private static final String CSE_USER_ACTIVE_TASKS = "cse_user_active_tasks";
    private static final String CSE_USER_COMPLETED_TASKS = "cse_user_completed_tasks";
    private static final String CSE_GET_TASKS = "cse_get_tasks";
    private static final String CSE_GET_SI = "cse_get_si";
    private static final String CSE_GET_SO = "cse_get_so";
    private static final String CSE_CUSTOMER_REVIEW = "customer_review";
    private static final String CSE_GET_REPORT_SI_NEW = "cse_get_report_si_new";
    private static final String CSE_GET_REPORT_SI_DISC = "cse_get_report_si_disc";
    private static final String CSE_GET_REPORT_SI_PROFIT = "cse_get_report_si_profit";
    private static final String CSE_ADD_CUSTOMER = "cse_add_customer";
    private static final String CSE_ADD_CUSTOMER_PAGE = "cse_add_customer_page";
    private static final String CSE_REVIEW_INSTANCE = "cse_review_instance";
    private static final String CSE_MODIFY_INSTANCE = "cse_modify_instance";
    private static final String CSE_DISCONNECT_INSTANCE = "cse_disconnect_instance";
    private static final String CSE_REVIEW_ORDER = "cse_review_order";
    private static final String CSE_CONFIRM_ORDER = "cse_confirm_order";
    private static final String CSE_CANCEL_ORDER = "cse_cancel_order";

    private static final String IE_USER_ACTIVE_TASKS = "ie_user_active_tasks";
    private static final String IE_USER_COMPLETED_TASKS = "ie_user_completed_tasks";
    private static final String IE_GET_TASKS = "ie_get_tasks";
    private static final String IE_GET_REPORT_RI_UTIL = "ie_get_report_ri_util";
    private static final String IE_GET_REPORT_RI_PROFIT = "ie_get_report_ri_profit";

    private static final String PE_USER_ACTIVE_TASKS = "pe_user_active_tasks";
    private static final String PE_USER_COMPLETED_TASKS = "pe_user_completed_tasks";
    private static final String PE_GET_TASKS = "pe_get_tasks";
    private static final String PE_GET_REPORT_CIA_IPT = "pe_get_report_cia_ipt";
    private static final String PE_REVIEW_DEVICE = "pe_review_device";
    private static final String PE_REVIEW_PORT = "pe_review_port";
    private static final String PE_REVIEW_CIRCUIT = "pe_review_circuit";
    private static final String PE_REVIEW_SERVICE_INSTANCE = "pe_review_service_instance";

    private final Map<String, ICommand> commands;

    private CommandHelper() {
        ConfigurationManager manager = ConfigurationManager.getInstance();
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(REFRESH_SERVICE, new RefreshService());
        commands.put(PROCEED_TO_ORDER, new ProceedToOrder());
        commands.put(NEW_DEVICE, new CreateDevice());
        commands.put(NEW_CABLE, new CreateCable());
        commands.put(DEL_CABLE, new DeleteCable());
        commands.put(VALIDATION, new Validation());
        commands.put(REGISTRATION, new Registration());
        commands.put(PROCESS_TASK, new ProcessTask());
        commands.put(CHANGE_PASSWORD, new ChangePassword());
        commands.put(UNASSIGN_TASK, new UnassignTask());
        commands.put(SETUP_CIRCUIT, new PESettingCircuit());
        commands.put(MODIFY_CIRCUIT, new PEModifyServiceInstance());
        commands.put(DELETE_CIRCUIT, new PEDeleteCircuit());

        commands.put(ADM_ADD_USER_PAGE, new ToAddUserPage());
        commands.put(ADM_ADD_USER, new ADMaddUser());
        commands.put(ADM_GET_USERS, new ADMgetUsersList());
        commands.put(ADM_REVIEW_USER, new ADMreviewUser());
        commands.put(ADM_SET_BLOCK_USER, new ADMsetBlockUser());

        String reviewOrderPage = manager.
                getProperty(ConfigurationManager.PAGE_CU_PAGE_REVIEW_ORDER);
        commands.put(CU_INSTANCES, new CUGetServiceInstanceForUser());
        commands.put(CU_REVIEW_INSTANCE, new ServiceInstanceReview(manager.
                getProperty(ConfigurationManager.PAGE_CU_PAGE_REVIEW_INSTANCE)));
        commands.put(CU_MODIFY_INSTANCE, new OrderModifySI(reviewOrderPage));
        commands.put(CU_DISCONNECT_INSTANCE, new OrderDisconnectSI(reviewOrderPage));
        commands.put(CU_ORDERS, new ListOrders());
        commands.put(CU_REVIEW_ORDER, new ServiceOrderReview(reviewOrderPage));
        commands.put(CU_CONFIRM_ORDER, new ConfirmOrder(reviewOrderPage));
        commands.put(CU_CANCEL_ORDER, new CancelOrder(reviewOrderPage));

        commands.put(CSE_SEND_BILL, new CSEsentBill());
        commands.put(CSE_CUSTOMERS_LIST, new CSEgetCustomersList());
        commands.put(CSE_USER_ACTIVE_TASKS,
                new CSEtasksByPerformerStatus(Task.Status.ACTIVE,
                        manager.getProperty(ConfigurationManager.PAGE_CSE_TASKS_LIST)));
        commands.put(CSE_USER_COMPLETED_TASKS,
                new CSEtasksByPerformerStatus(Task.Status.COMPLETED,
                        manager.getProperty(ConfigurationManager.PAGE_CSE_TASKS_LIST)));
        commands.put(CSE_GET_TASKS,
                new CSEgroupTasks(
                        manager.getProperty(ConfigurationManager.PAGE_CSE_TASKS_LIST)));
        commands.put(CSE_CUSTOMER_REVIEW, new CSEreviewCustomer());
        commands.put(CSE_GET_REPORT_SI_NEW, new CSEgetReportSiNew());
        commands.put(CSE_GET_REPORT_SI_DISC, new CSEgetReportSiDisc());
        commands.put(CSE_GET_REPORT_SI_PROFIT, new CSEgetReportSiProfit());
        commands.put(CSE_ADD_CUSTOMER_PAGE, new CSEtoAddUserPage());
        commands.put(CSE_ADD_CUSTOMER, new CSEaddCustomer());
        commands.put(CSE_GET_SI, new CSEgetServiceInstanceForUser());
        commands.put(CSE_GET_SO, new CSEgetOrdersForUser());
        commands.put(CSE_REVIEW_INSTANCE, new ServiceInstanceReview(manager.
                getProperty(ConfigurationManager.PAGE_CSE_REVIEW_INSTANCE)));
        reviewOrderPage = manager.
                getProperty(ConfigurationManager.PAGE_CSE_REVIEW_ORDER);
        commands.put(CSE_REVIEW_ORDER, new ServiceOrderReview(reviewOrderPage));
        commands.put(CSE_MODIFY_INSTANCE, new OrderModifySI(reviewOrderPage));
        commands.put(CSE_DISCONNECT_INSTANCE, new OrderDisconnectSI(reviewOrderPage));
        commands.put(CSE_CONFIRM_ORDER, new ConfirmOrder(reviewOrderPage));
        commands.put(CSE_CANCEL_ORDER, new CancelOrder(reviewOrderPage));

        String tasksListPage = manager.getProperty(ConfigurationManager.PAGE_IE_TASKS_LIST);
        commands.put(IE_USER_ACTIVE_TASKS,
                new IETasksByPerformerStatus(Task.Status.ACTIVE, tasksListPage));
        commands.put(IE_USER_COMPLETED_TASKS,
                new IETasksByPerformerStatus(Task.Status.COMPLETED, tasksListPage));
        commands.put(IE_GET_TASKS,
                new IEGroupTasks(tasksListPage));
        commands.put(IE_GET_REPORT_RI_UTIL, new IEgetReportRiUtil());
        commands.put(IE_GET_REPORT_RI_PROFIT, new IEgetReportRiProfit());

        tasksListPage = manager.getProperty(ConfigurationManager.PAGE_PE_TASKS_LIST);
        commands.put(PE_USER_ACTIVE_TASKS,
                new PETasksByPerformerStatus(Task.Status.ACTIVE, tasksListPage));
        commands.put(PE_USER_COMPLETED_TASKS,
                new PETasksByPerformerStatus(Task.Status.COMPLETED, tasksListPage));
        commands.put(PE_GET_TASKS,
                new PEGroupTasks(tasksListPage));
        commands.put(PE_GET_REPORT_CIA_IPT, new PEgetReportCiaIpt());
        commands.put(PE_REVIEW_DEVICE, new PEreviewDevice());
        commands.put(PE_REVIEW_PORT, new PEreviewPort());
        commands.put(PE_REVIEW_CIRCUIT, new PEreviewCircuit());
        commands.put(PE_REVIEW_SERVICE_INSTANCE, new PEreviewSI());
    }

    /**
     * Method allow to get exemplar CommandHelper class.
     *
     * @return exemplar CommandHelper
     */
    public static CommandHelper getInstance() {
        if (commandHelper == null) {
            commandHelper = new CommandHelper();
        }
        return commandHelper;
    }

    /**
     * Method allow get right command based on request.
     *
     * @param request servlet request
     * @return exemplar classes of command if isn't right command will be
     * returned NoCommand
     */
    public ICommand getCommand(HttpServletRequest request) {
        ICommand command;
        String key = request.getParameter(COMMAND);
        if (commands.containsKey(key)) {
            command = commands.get(key);
        } else {
            command = commands.get(NO_COMMAND);
        }
        return command;
    }

}
