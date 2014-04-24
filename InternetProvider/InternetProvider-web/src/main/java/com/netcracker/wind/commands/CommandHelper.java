package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.NameGenerator;
import com.netcracker.wind.commands.implementations.NoCommand;
import com.netcracker.wind.commands.implementations.SentMail;
<<<<<<< HEAD
import com.netcracker.wind.commands.implementations.csedashboard.CSEGetGroupTasks;
import com.netcracker.wind.commands.implementations.csedashboard.CustomersList;
=======
>>>>>>> 1946158fa84c2956a6e6a34a9aaa2ec17ee0747e
import com.netcracker.wind.commands.implementations.order.ProceedToOrder;
import com.netcracker.wind.commands.implementations.order.RefreshService;
import com.netcracker.wind.commands.implementations.reports.RiMostProfRouterReportGenerator;
import com.netcracker.wind.commands.implementations.reports.RiRoutersUtilNCapReportGenerator;
import com.netcracker.wind.commands.implementations.reports.SiDisconnOrdersReportGenerator;
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
    private static final String TEST_AJAX = "name_generator";
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
<<<<<<< HEAD
    private static final String CUSTOMERS_LIST = "customers_list";
    private static final String CSE_GET_ELEMENTS_COUNT = "cse_get_elements_count";
    private static final String CSE_GET_ELEMENTS_FROM_OFFSET = "cse_get_elements_from_offset";
=======
>>>>>>> 1946158fa84c2956a6e6a34a9aaa2ec17ee0747e

    private final Map<String, ICommand> commands;

    private CommandHelper() {
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(REFRESH_SERVICE, new RefreshService());
        commands.put(TEST_AJAX, new NameGenerator());
        commands.put(RI_UTIL_N_CAP, new RiRoutersUtilNCapReportGenerator());
        commands.put(RI_MOST_PROF, new RiMostProfRouterReportGenerator());
        commands.put(SI_NEW_ORDERS, new SiNewOrdersReportGenerator());
        commands.put(SI_DISCONN_ORDERS, new SiDisconnOrdersReportGenerator());
        commands.put(SI_PROF_BY_MONTH, new SiProfitReportGenerator());
        commands.put(SENT_MAIL, new SentMail());
        commands.put(CSE_GROUP_TASK, new CSEGetGroupTasks());
        commands.put(PROCEED_TO_ORDER, new ProceedToOrder());
<<<<<<< HEAD
        commands.put(CUSTOMERS_LIST, (ICommand) new CustomersList());
=======
>>>>>>> 1946158fa84c2956a6e6a34a9aaa2ec17ee0747e
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
