package com.netcracker.wind.filters;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IUserDAO;
import com.netcracker.wind.entities.User;
import com.netcracker.wind.manager.ConfigurationManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This filter was designed for check blocked users.
 *
 * @author Anatolii
 */
public class BlockedFilter implements Filter {

    private static final String USER = "user";
    private static final String LOGOUT = "/InternetProvider-web/logout";
    private static final IUserDAO USER_DAO
            = FactoryCreator.getInstance().getFactory().createUserDAO();
    private FilterConfig filterConfig = null;

    public BlockedFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        boolean flag = true;
        if (session != null
                && !LOGOUT.equals(httpServletRequest.getRequestURI())) {
            Object loginedUser = session.getAttribute(USER);
            if (loginedUser != null && (loginedUser instanceof User)) {
                int userId = ((User) loginedUser).getId();
                User user = USER_DAO.findById(userId);
                if (user.isBlocked()) {
                    flag = false;
                    RequestDispatcher dispatcher = httpServletRequest.
                            getRequestDispatcher(
                                    ConfigurationManager.getInstance().
                                    getProperty(ConfigurationManager.PAGE_BLOCKED));
                    dispatcher.forward(request, response);
                }
            }
        }
        if (flag) {
            chain.doFilter(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

}
