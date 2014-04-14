/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Logger;

/**
 *
 * @author oneplayer
 */
public class private_controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //static final Logger logger = LogManager.getLogger(private_controller.class.getName());
    private static final Logger logger = Logger.getLogger(private_controller.class.getName());
    //static private FileHandler fileTxt;

    //static final Logger logger = LogManager.getRootLogger();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        if ("/logout".equals(request.getServletPath())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/");
        } else {
            if ("/profile".equals(request.getServletPath())) {
                Properties props = new Properties();
                //loading properites from properties file
                props.load(getServletContext().getResourceAsStream("/WEB-INF/classes/roles.properties"));
                //reading proeprty and split to roles
                String roles[] = props.getProperty("roles").split(",");
                String roledirs[] = props.getProperty("roledirs").split(",");
                //getting nextUrl
                boolean founded = false;
                int i = 0;
                while ((i < roles.length) && (!founded)) {
                    if (founded = request.isUserInRole(roles[i])) {
                        request.setAttribute("name", request.getUserPrincipal().getName());
                        request.getRequestDispatcher("WEB-INF/"+roledirs[i]+"/index.jsp").
                                forward(request, response);
                    }
                    i++;
                }
            } else {
                request.setAttribute("name", request.getUserPrincipal().getName());
                request.getRequestDispatcher("WEB-INF" + request.getServletPath() + "/index.jsp").
                        forward(request, response);
            }
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
