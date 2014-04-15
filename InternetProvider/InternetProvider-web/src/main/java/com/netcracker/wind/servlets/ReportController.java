package com.netcracker.wind.servlets;

import com.netcracker.wind.dao.factory.DAOFactory;
import com.netcracker.wind.entities.Device;
import com.netcracker.wind.entities.ServiceOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander Kovriga
 */
public class ReportController extends HttpServlet {

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
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession httpSession = request.getSession();
            RequestDispatcher dispatcher;
            String value = request.getParameter("report");
            if (value == null) {
                dispatcher = request
                        .getRequestDispatcher("report.jsp");
            } else if (value.equals("riUtilNCap")) {
                List<Device> devices = DAOFactory.createDeviceDAO().findAll();
                httpSession.setAttribute("routers", devices);
                dispatcher = request
                        .getRequestDispatcher("RiUtilNCap.jsp");
            } else if (value.equals("siNewOrders")) {
                List<ServiceOrder> serviceOrders = new ArrayList();
                
                httpSession.setAttribute("orders", serviceOrders);
                dispatcher = request
                        .getRequestDispatcher("SiNewOrders.jsp");
            } else {
                dispatcher = request
                        .getRequestDispatcher("report.jsp");
            }

            if (dispatcher != null) {
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
