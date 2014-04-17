package com.netcracker.wind.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bed Anatolii
 */
public interface ICommand {

    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    );
}
