/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands;

import com.netcracker.wind.commands.impl.NoCommand;
import com.netcracker.wind.commands.impl.TestAjax;
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
    private static final String TEST_AJAX = "test_ajax";

    private Map<String, ICommand> commands;

    private CommandHelper() {
        commands = new HashMap<String, ICommand>();
        commands.put(NO_COMMAND, new NoCommand());
        commands.put(TEST_AJAX, new TestAjax());
    }

    public static CommandHelper getInstance() {
        if (commandHelper == null) {
            commandHelper = new CommandHelper();
        }
        return commandHelper;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = null;
        String key = request.getParameter("command");
        if (commands.containsKey(key)) {
            command = commands.get(key);
        } else {
            command = commands.get(NO_COMMAND);
        }
        return command;
    }

}
