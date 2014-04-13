/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.servlets;

import com.netcracker.wind.test.Order;
import com.netcracker.wind.test.Router;
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
            RequestDispatcher dispatcher = null;
            String value = request.getParameter("report");
            if (value.equals("util")) {
                List<Router> routers = new ArrayList();
                routers.add(new Router("Cisco 7606", 60, 10));
                routers.add(new Router("Cisco 7606", 60, 45));
                routers.add(new Router("Cisco 7606", 60, 60));
                routers.add(new Router("Cisco 7606", 60, 0));
                routers.add(new Router("Cisco 7606", 60, 15));
                routers.add(new Router("Cisco 7606", 60, 23));
                httpSession.setAttribute("routers", routers);
                dispatcher = request
                        .getRequestDispatcher("RiUtilNCap.jsp");
            } else if (value.equals("new")) {
                List<Order> orders = new ArrayList();
                orders.add(new Order(100, "Silver internet", "01.01.2014", 25));
                orders.add(new Order(101, "Golden internet", "01.01.2014", 40));
                orders.add(new Order(110, "Golden internet", "01.01.2014", 40));
                orders.add(new Order(111, "Golden internet", "09.01.2014", 40));
                orders.add(new Order(112, "Silver internet", "10.01.2014", 25));
                orders.add(new Order(113, "Silver internet", "12.01.2014", 25));
                orders.add(new Order(201, "Silver internet", "13.01.2014", 25));
                orders.add(new Order(242, "Platinum internet", "20.01.2014", 55));
                orders.add(new Order(301, "Platinum internet", "20.01.2014", 55));
                orders.add(new Order(302, "Silver internet", "20.01.2014", 25));
                orders.add(new Order(501, "Silver internet", "29.01.2014", 25));
                httpSession.setAttribute("orders", orders);
                dispatcher = request
                        .getRequestDispatcher("SiNewOrders.jsp");
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
