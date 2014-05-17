package com.netcracker.wind.controllers;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.annotations.RolesForbidden;
import com.netcracker.wind.commands.CommandHelper;
import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Main Controller class.
 *
 * @author Bed Anatolii
 */
public class Controller extends HttpServlet {

    private static final String AJAX_REQUEST_HEADER = "XMLHttpRequest";
    private static final String HEADER = "X-Requested-With";
    private static final String USER = "user";

    private final CommandHelper helper = CommandHelper.getInstance();

    private static final Logger LOGGER
            = Logger.getLogger(Controller.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Method get command in request via {@link CommandHelper}, check
     * permission and invoke it if exist permissions.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see CommandHelper
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String page = ConfigurationManager.getInstance().
                    getProperty(ConfigurationManager.PAGE_ERROR);
            ICommand command = helper.getCommand(request);
            if (isInRole(request, command)) {
                page = command.execute(request, response);
            } else {
                LOGGER.info("attempt illegal access");
            }
            if (AJAX_REQUEST_HEADER.equals(request.getHeader(HEADER))) {
                response.getWriter().write(page);
            } else {
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (ServletException se) {
            LOGGER.error(null, se);
        } catch (IOException ioe) {
            LOGGER.error(null, ioe);
        }
    }

    private boolean isInRole(HttpServletRequest request, ICommand command) {

        RolesAllowed allowed = command.getClass().getAnnotation(RolesAllowed.class);
        RolesForbidden forbidden = command.getClass().getAnnotation(RolesForbidden.class);

        if (forbidden != null) {
            for (Role.Roles s : forbidden.roles()) {
                if (request.isUserInRole(s.toString())) {
                    return false;
                }
            }
        }

        if (allowed == null) {
            return true;
        } else {
            HttpSession session = request.getSession(false);
            if (session == null) {
                return false;
            } else {
                Object logegUser = session.getAttribute(USER);
                if (logegUser == null || !(logegUser instanceof User)) {
                    return false;
                }
            }
        }
        for (Role.Roles s : allowed.roles()) {
            if (request.isUserInRole(s.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
