package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.NoCommand;
import com.netcracker.wind.commands.implementations.SentMail;
import com.netcracker.wind.commands.implementations.csedashboard.*;
import com.netcracker.wind.commands.implementations.iedashboard.CreateCable;
import com.netcracker.wind.commands.implementations.iedashboard.CreateDevice;
import com.netcracker.wind.commands.implementations.iedashboard.DeleteCable;
import com.netcracker.wind.commands.implementations.iedashboard.getActiveTasks;
import com.netcracker.wind.commands.implementations.order.ProceedToOrder;
import com.netcracker.wind.commands.implementations.order.RefreshService;
import com.netcracker.wind.commands.implementations.pedashboard.ProvisioningEngineerTasks;
import com.netcracker.wind.commands.implementations.reports.CiaIptReportGenerator;
import com.netcracker.wind.commands.implementations.reports.RiMostProfRouterReportGenerator;
import com.netcracker.wind.commands.implementations.reports.RiRoutersUtilNCapReportGenerator;
import com.netcracker.wind.commands.implementations.reports.SiDiscOrdersReportGenerator;
import com.netcracker.wind.commands.implementations.reports.SiNewOrdersReportGenerator;
import com.netcracker.wind.commands.implementations.reports.SiProfitReportGenerator;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anatolii
 */
public class CommandHelper {

    private static CommandHelper commandHelper;

    private static final String NO_COMMAND = "no_command";
    private static final String PROCEED_TO_ORDER = "proceed_to_order";
    private static final String RI_UTIL_N_CAP = "ri_util_n_cap";
    private static final String RI_MOST_PROF = "ri_most_prof";
    private static final String SI_NEW_ORDERS = "si_new_orders";
    private static final String SI_PROF_BY_MONTH = "si_prof_by_month";
    private static final String SI_DISCONN_ORDERS = "si_disc_orders";
    private static final String CIA_IPT = "cia_ipt";
    private static final String REFRESH_SERVICE = "refresh_service";
    private static final String SENT_MAIL = "sent_mail";
    private static final String CSE_GROUP_TASK = "cse_group_task";
    private static final String CUSTOMERS_LIST = "customers_list";
    private static final String PE_TASKS = "pe_tasks";
    private static final String CSE_GET_ELEMENTS_COUNT
            = "cse_get_elements_count";
    private static final String CSE_GET_ELEMENTS_FROM_OFFSET
            = "cse_get_elements_from_offset";
    private static final String CSE_GET_TASKS = "cse_get_tasks";
    private static final String CSE_GET_COMPLETED_TASKS = "cse_get_completed_tasks";
    private static final String CSE_GET_UNCOMPLETED_TASKS = "cse_get_uncompleted_tasks";
    private static final String PROVIDER_LOCATION_LIST = "provider_location_list";
    private static final String NEW_DEVICE = "new_device";
    private static final String NEW_CABLE = "new_cable";
    private static final String DEL_CABLE = "del_cable";
    private static final String GET_ACTIVE_IETASKS = "get_active_ietasks";


    private final Map<String, ICommand> commands;

    private CommandHelper() {
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(REFRESH_SERVICE, new RefreshService());
        commands.put(RI_UTIL_N_CAP, new RiRoutersUtilNCapReportGenerator());
        commands.put(RI_MOST_PROF, new RiMostProfRouterReportGenerator());
        commands.put(SI_NEW_ORDERS, new SiNewOrdersReportGenerator());
        commands.put(SI_DISCONN_ORDERS, new SiDiscOrdersReportGenerator());
        commands.put(SI_PROF_BY_MONTH, new SiProfitReportGenerator());
        commands.put(CIA_IPT, new CiaIptReportGenerator());
        commands.put(SENT_MAIL, new SentMail());
        commands.put(CSE_GROUP_TASK, new CSEGetGroupTasks());
        commands.put(PROCEED_TO_ORDER, new ProceedToOrder());
        commands.put(CUSTOMERS_LIST, new CustomersList());
        commands.put(PE_TASKS, new ProvisioningEngineerTasks());
        commands.put(CSE_GET_TASKS, new CSEgetOwnTasks());
        commands.put(CSE_GET_COMPLETED_TASKS, new CSEgetOwnCompletedTasks());
        commands.put(CSE_GET_UNCOMPLETED_TASKS, new CSEgetOwnUncompletedTasks());
        commands.put(PROVIDER_LOCATION_LIST, new GetProviderLocation());
        commands.put(NEW_DEVICE, new CreateDevice());
        commands.put(NEW_CABLE, new CreateCable());
        commands.put(DEL_CABLE, new DeleteCable());
        commands.put(GET_ACTIVE_IETASKS, new getActiveTasks());

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
