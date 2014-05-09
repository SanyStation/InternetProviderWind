package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.NoCommand;
import com.netcracker.wind.commands.implementations.ProcessTask;
import com.netcracker.wind.commands.implementations.SentMail;
import com.netcracker.wind.commands.implementations.ToPage;
import com.netcracker.wind.commands.implementations.UnassignTask;
import com.netcracker.wind.commands.implementations.admindashboard.ADMaddUser;
import com.netcracker.wind.commands.implementations.admindashboard.ADMgetUsersList;
import com.netcracker.wind.commands.implementations.admindashboard.ADMreviewUser;
import com.netcracker.wind.commands.implementations.admindashboard.BlockUser;
import com.netcracker.wind.commands.implementations.csedashboard.*;
import com.netcracker.wind.commands.implementations.dashboards.CUGetServiceInstanceForUser;
import com.netcracker.wind.commands.implementations.dashboards.ChangePassword;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.commands.implementations.dashboards.GetTasksByPerformerStatus;
import com.netcracker.wind.commands.implementations.dashboards.InstanceReview;
import com.netcracker.wind.commands.implementations.iedashboard.*;
import com.netcracker.wind.commands.implementations.order.*;
import com.netcracker.wind.commands.implementations.pedashboard.*;
import com.netcracker.wind.commands.implementations.registration.Registration;
import com.netcracker.wind.commands.implementations.registration.Validation;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.Task;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author Anatolii
 */
public class CommandHelper {

    private static CommandHelper commandHelper;

    private static final String NO_COMMAND = "no_command";
    private static final String PROCEED_TO_ORDER = "proceed_to_order";
    private static final String REFRESH_SERVICE = "refresh_service";
    private static final String SENT_MAIL = "sent_mail";
    private static final String CUSTOMERS_LIST = "customers_list";
    private static final String PROVIDER_LOCATION_LIST = "provider_location_list";
    private static final String NEW_DEVICE = "new_device";
    private static final String NEW_CABLE = "new_cable";
    private static final String DEL_CABLE = "delete_cable";
    private static final String VALIDATION = "validation";
    private static final String REGISTRATION = "registration";
    private static final String TO_PAGE = "to_page";
    private static final String PROCESS_TASK = "process_task";
    private static final String UNASSIGN_TASK = "unassign_task";
    private static final String SEND_BILL = "send_bill";
    private static final String SETUP_CIRCUIT = "setup_circuit";
    private static final String MODIFY_CIRCUIT = "modify_circuit";
    private static final String DELETE_CIRCUIT = "delete_circuit";
    private static final String CHANGE_PASSWORD = "change_password";
    
    private static final String ADM_ADD_USER = "adm_add_user";
    private static final String ADM_GET_USERS = "adm_get_users";
    private static final String ADM_REVIEW_USER = "adm_review_user";
    private static final String ADM_BLOCK_USER = "block_user";

    private static final String CU_INSTANCES = "cu_instances";
    private static final String CU_REVIEW_INSTANCE = "review_instance";
    private static final String CU_MODIFY_INSTANCE = "modify_instance";
    private static final String CU_DISCONNECT_INSTANCE = "disconnect_instance";
    private static final String CU_ORDERS = "cu_orders";
    private static final String CU_REVIEW_ORDER = "review_order";
    private static final String CU_CONFIRM_ORDER = "confirm_order";
    private static final String CU_CANCEL_ORDER = "cancel_order";

//    private static final String CSE_GET_ELEMENTS_COUNT = "cse_get_elements_count";
//    private static final String CSE_GET_ELEMENTS_FROM_OFFSET = "cse_get_elements_from_offset";
    private static final String CSE_USER_ACTIVE_TASKS = "cse_user_active_tasks";
    private static final String CSE_USER_COMPLETED_TASKS = "cse_user_completed_tasks";
    private static final String CSE_GET_TASKS = "cse_get_tasks";
//    private static final String CSE_GET_COMPLETED_TASKS = "cse_get_completed_tasks";
//    private static final String CSE_GET_UNCOMPLETED_TASKS = "cse_get_uncompleted_tasks";
//    private static final String CSE_GROUP_TASKS = "cse_group_tasks";
    private static final String CSE_CUSTOMER_REVIEW = "customer_review";
    private static final String CSE_GET_REPORT_SI_NEW = "cse_get_report_si_new";
    private static final String CSE_GET_REPORT_SI_DISC = "cse_get_report_si_disc";
    private static final String CSE_GET_REPORT_SI_PROFIT = "cse_get_report_si_profit";
    private static final String CSE_ADD_CUSTOMER = "cse_add_customer";

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
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(REFRESH_SERVICE, new RefreshService());
        commands.put(SENT_MAIL, new SentMail());
        commands.put(PROCEED_TO_ORDER, new ProceedToOrder());
        commands.put(CUSTOMERS_LIST, new CSEgetCustomersList());
        commands.put(PROVIDER_LOCATION_LIST, new GetProviderLocation());
        commands.put(NEW_DEVICE, new CreateDevice());
        commands.put(NEW_CABLE, new CreateCable());
        commands.put(DEL_CABLE, new DeleteCable());
        commands.put(VALIDATION, new Validation());
        commands.put(REGISTRATION, new Registration());
        commands.put(TO_PAGE, new ToPage());
        commands.put(PROCESS_TASK, new ProcessTask());
        commands.put(CHANGE_PASSWORD, new ChangePassword());
        commands.put(UNASSIGN_TASK, new UnassignTask());
        commands.put(SEND_BILL, new SentBill());
        commands.put(SETUP_CIRCUIT, new SetupCircuit());
        commands.put(MODIFY_CIRCUIT, new ModifyServiceInstance());
        commands.put(DELETE_CIRCUIT, new DeleteCircuit());

        commands.put(ADM_ADD_USER, new ADMaddUser());
        commands.put(ADM_GET_USERS, new ADMgetUsersList());
        commands.put(ADM_REVIEW_USER, new ADMreviewUser());
        commands.put(ADM_BLOCK_USER, new BlockUser());
        
        commands.put(CU_INSTANCES, new CUGetServiceInstanceForUser());
        commands.put(CU_REVIEW_INSTANCE, new InstanceReview());
        commands.put(CU_MODIFY_INSTANCE, new OrderModifySI());
        commands.put(CU_DISCONNECT_INSTANCE, new OrderDisconnectSI());
        commands.put(CU_ORDERS, new ListOrders());
        commands.put(CU_REVIEW_ORDER, new ReviewOrder());
        commands.put(CU_CONFIRM_ORDER, new ConfirmOrder());
        commands.put(CU_CANCEL_ORDER, new CancelOrder());

        commands.put(CSE_USER_ACTIVE_TASKS, new GetTasksByPerformerStatus(Task.Status.ACTIVE, "/WEB-INF/cse/cse-page-tasks-list.jsp"));
        commands.put(CSE_USER_COMPLETED_TASKS, new GetTasksByPerformerStatus(Task.Status.COMPLETED, "/WEB-INF/cse/cse-page-tasks-list.jsp"));
        commands.put(CSE_GET_TASKS, new GetGroupTasks(Role.CSE_GROUP_ID, "/WEB-INF/cse/cse-page-tasks-list.jsp"));
//        commands.put(CSE_GET_COMPLETED_TASKS, new CSEgetOwnCompletedTasks());
//        commands.put(CSE_GET_UNCOMPLETED_TASKS, new CSEgetOwnUncompletedTasks());
//        commands.put(CSE_GET_ELEMENTS_COUNT, new CSEgetElementsCount());
//        commands.put(CSE_GET_ELEMENTS_FROM_OFFSET, new CSEGetElementsFromOffset());
//        commands.put(CSE_GROUP_TASKS, new CSEGetGroupTasks());
        commands.put(CSE_CUSTOMER_REVIEW, new CSEreviewCustomer());
        commands.put(CSE_GET_REPORT_SI_NEW, new CSEgetReportSiNew());
        commands.put(CSE_GET_REPORT_SI_DISC, new CSEgetReportSiDisc());
        commands.put(CSE_GET_REPORT_SI_PROFIT, new CSEgetReportSiProfit());
        commands.put(CSE_ADD_CUSTOMER, new CSEaddCustomer());

        commands.put(IE_USER_ACTIVE_TASKS, new GetTasksByPerformerStatus(Task.Status.ACTIVE, "/WEB-INF/ie/ie-page-tasks-list.jsp"));
        commands.put(IE_USER_COMPLETED_TASKS, new GetTasksByPerformerStatus(Task.Status.COMPLETED, "/WEB-INF/ie/ie-page-tasks-list.jsp"));
        commands.put(IE_GET_TASKS, new GetGroupTasks(Role.IE_GROUP_ID, "/WEB-INF/ie/ie-page-tasks-list.jsp"));
        commands.put(IE_GET_REPORT_RI_UTIL, new IEgetReportRiUtil());
        commands.put(IE_GET_REPORT_RI_PROFIT, new IEgetReportRiProfit());

        commands.put(PE_USER_ACTIVE_TASKS, new GetTasksByPerformerStatus(Task.Status.ACTIVE, "/WEB-INF/pe/pe-page-tasks-list.jsp"));
        commands.put(PE_USER_COMPLETED_TASKS, new GetTasksByPerformerStatus(Task.Status.COMPLETED, "/WEB-INF/pe/pe-page-tasks-list.jsp"));
        commands.put(PE_GET_TASKS, new GetGroupTasks(Role.PE_GROUP_ID, "/WEB-INF/pe/pe-page-tasks-list.jsp"));
        commands.put(PE_GET_REPORT_CIA_IPT, new PEgetReportCiaIpt());
        commands.put(PE_REVIEW_DEVICE, new PEreviewDevice());
        commands.put(PE_REVIEW_PORT, new PEreviewPort());
    }

    public static CommandHelper getInstance() {
        if (commandHelper == null) {
            commandHelper = new CommandHelper();
        }
        return commandHelper;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command;
        String key = request.getParameter("command");
        if (commands.containsKey(key)) {
            command = commands.get(key);
        } else {
            command = commands.get(NO_COMMAND);
        }
        return command;
    }

}
