package com.netcracker.wind.controllers;

import com.netcracker.wind.commands.CommandHelper;
import com.netcracker.wind.commands.ICommand;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bed Anatolii
 */
public class Controller extends HttpServlet {

    private static final String AJAX_REQUEST_HEADER = "XMLHttpRequest";
    private static final String HEADER = "X-Requested-With";

    private final CommandHelper helper = CommandHelper.getInstance();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String page;
            ICommand command = helper.getCommand(request);
            page = command.execute(request, response);
            if (AJAX_REQUEST_HEADER.equals(request.getHeader(HEADER))) {
                response.getWriter().write(page);
            } else {
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (ServletException se) {
            //TODO Log4j ServletException
        } catch (IOException ioe) {
            //TODO Log4j IOException
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    }// </editor-fold>

}
