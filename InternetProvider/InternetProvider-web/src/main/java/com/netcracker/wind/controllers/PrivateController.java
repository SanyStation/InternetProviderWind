package com.netcracker.wind.controllers;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author oneplayer
 */
public class PrivateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger LOGGER
            = Logger.getLogger(PrivateController.class.getName());
    private static final String NAME = "name";
    private static final String USER = "user";
    private static final String WEB_INF = "WEB-INF/";
    private static final String INDEX = "/index.jsp";
    private static final String LOGOUT = "/logout";
    private static final String PROFILE = "/profile";
    private String roles[];
    private String roledirs[];

    /**
     * 
     * processRequest method check user's role and redirect it to dashboard according to his role.
     * This method also redirect user to 'user blocked' page if he is blocked.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    //static final Logger logger = LogManager.getRootLogger();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        if (LOGOUT.equals(request.getServletPath())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
        } else {
            if (PROFILE.equals(request.getServletPath())) {
                //getting nextUrl
                boolean founded = false;
                int i = 0;
                while ((i < roles.length) && (!founded)) {
                    if (founded = request.isUserInRole(roles[i])) {
                        request.setAttribute(NAME, request.getUserPrincipal().getName());
                        HttpSession session = request.getSession(false);
                        IUserDAO userDAO = FactoryCreator.getInstance().
                                getFactory().createUserDAO();
                        User user = userDAO.findByEmail(request.getUserPrincipal().getName());
                        String pageRedirect = WEB_INF + roledirs[i] + INDEX;
                        if (session != null) {
                            if (user != null) {
                                session.setAttribute(USER, user);
                                if (user.isBlocked()) {
                                    pageRedirect = "/WEB-INF/profile/blocked.jsp";
                                }
                            }
                        }
                        
                        request.getSession().setAttribute(NAME, request.getUserPrincipal().getName());
                        request.getRequestDispatcher(pageRedirect).
                                forward(request, response);
                    }
                    i++;
                }
            } else {
                request.setAttribute(NAME, request.getUserPrincipal().getName());
                request.getRequestDispatcher("WEB-INF" + request.getServletPath() + INDEX).
                        forward(request, response);
            }
        }
    }

    /**
     * 
     * Overloaded init method.
     * Added roles parsing and logging.
     * 
     * @throws ServletException 
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Properties props = new Properties();
            //loading properites from properties file
            props.load(getServletContext().getResourceAsStream("/WEB-INF/classes/roles.properties"));
            //reading proeprty and split to roles
            roles = props.getProperty("roles").split(",");
            roledirs = props.getProperty("roledirs").split(",");
        } catch (IOException ex) {
            LOGGER.error(null, ex);
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
