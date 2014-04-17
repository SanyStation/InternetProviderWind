package com.netcracker.wind.commands;

import com.netcracker.wind.commands.implementations.RiUtilNCapReportGenerator;
import com.netcracker.wind.commands.implementations.NameGenerator;
import com.netcracker.wind.commands.implementations.NoCommand;

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
    private static final String RI_UTIL_N_CAP = "riUtilNCap";
    private static final String SI_NEW_ORDERS = "siNewOrders";

    private final Map<String, ICommand> commands;

    private CommandHelper() {
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(TEST_AJAX, new NameGenerator());
        commands.put(RI_UTIL_N_CAP, new RiUtilNCapReportGenerator());
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
