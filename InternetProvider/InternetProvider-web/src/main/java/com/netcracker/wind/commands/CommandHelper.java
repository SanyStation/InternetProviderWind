package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.*;

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
    private static final String RI_UTIL_N_CAP = "ri_util_n_cap";
    private static final String RI_MOST_PROF = "ri_most_prof";
    private static final String SI_NEW_ORDERS = "si_new_orders";
    private static final String SI_PROF_BY_MONTH = "si_prof_by_month";
    private static final String SI_DISCONN_ORDERS = "si_disconn_orders";
    private static final String CIA_IPT = "cia_ipt";

    private final Map<String, ICommand> commands;

    private CommandHelper() {
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(TEST_AJAX, new NameGenerator());
        commands.put(RI_UTIL_N_CAP, new RiRoutersUtilNCapReportGenerator());
        commands.put(RI_MOST_PROF, new RiMostProfRouterReportGenerator());
        commands.put(SI_NEW_ORDERS, new SiNewOrdersReportGenerator());
        commands.put(SI_DISCONN_ORDERS, new SiDisconnOrdersReportGenerator());
        commands.put(SI_PROF_BY_MONTH, new SiProfByMonthReportGenerator());
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
