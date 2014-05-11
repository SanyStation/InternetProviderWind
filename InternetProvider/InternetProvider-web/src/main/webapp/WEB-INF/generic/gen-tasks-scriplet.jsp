<%-- 
    Author     : Alexander Kovriga
--%>

<%@page import="com.netcracker.wind.entities.Task"%>
<%@page import="com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO"%>
<%@page import="com.netcracker.wind.entities.User"%>
<%@page import="com.netcracker.wind.dao.implementations.oracle.OracleTaskDAO"%>
<%@page import="com.netcracker.wind.dao.interfaces.ITaskDAO"%>

<%
    ITaskDAO taskDAO = new OracleTaskDAO();
    User user = (User) request.getSession(false).getAttribute("user");
    int newTasks = 0;
    int activeTasks = 0;
    int completedTasks = 0;
    if (user != null) {
        taskDAO.findByGroupStatus(user.getRoleId(), Task.Status.NEW.toString(),
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER, AbstractOracleDAO.MIN_PAGE_SIZE);
        newTasks = taskDAO.countRows();
        taskDAO.findByPerformerStatus(user.getId(), Task.Status.ACTIVE.toString(),
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER, AbstractOracleDAO.MIN_PAGE_SIZE);
        activeTasks = taskDAO.countRows();
        taskDAO.findByPerformerStatus(user.getId(), Task.Status.COMPLETED.toString(),
                AbstractOracleDAO.DEFAULT_PAGE_NUMBER, AbstractOracleDAO.MIN_PAGE_SIZE);
        completedTasks = taskDAO.countRows();
    }
    System.out.println("### new " + newTasks + " active " + activeTasks + " completed " + completedTasks);
    request.setAttribute("newTasks", newTasks);
    request.setAttribute("activeTasks", activeTasks);
    request.setAttribute("completedTasks", completedTasks);
%>
