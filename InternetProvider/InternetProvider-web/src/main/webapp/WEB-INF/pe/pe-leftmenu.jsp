<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<jsp:include page="../generic/gen-tasks-scriplet.jsp" flush="false" />

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="list-group">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked panel panel-${param.active eq 'tasks' ? 'primary' : 'default'}  nomargin">
                <li class="panel-heading"><i class="glyphicon glyphicon-briefcase"></i> Tasks</li>

                <li class="${param.command eq 'pe_get_tasks' ? ' active' : ''}  ${newTasks > 0 ? 'alert-success' : ''}">
                    <a href="Controller?command=pe_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> New tasks 
                        <c:if test="${newTasks > 0}">
                            <span class="badge pull-right alert-danger">${newTasks}</span>
                        </c:if>
                    </a>
                </li>
                <li class="${param.command eq 'pe_user_active_tasks' ? ' active' : ''}">
                    <a href="Controller?command=pe_user_active_tasks"><i class="glyphicon glyphicon-briefcase"></i> Active tasks 
                        <c:if test="${activeTasks > 0}">
                            <span class="badge pull-right">${activeTasks}</span>
                        </c:if>
                    </a>
                </li>
                <li class="${param.command eq 'pe_user_completed_tasks' ? ' active' : ''}">
                    <a href="Controller?command=pe_user_completed_tasks"><i class="glyphicon glyphicon-briefcase"></i> Completed tasks 
                        <c:if test="${completedTasks > 0}">
                            <span class="badge pull-right">${completedTasks}</span>
                        </c:if>
                    </a>
                </li>
            </ul>
        </li>

        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked panel panel-${param.active eq 'reports' ? 'primary' : 'default'}  nomargin">
                <li class="panel-heading"><i class="glyphicon glyphicon-list-alt"></i> Reports</li>
                <li class="${param.command eq 'pe_get_report_cia_ipt' ? ' active' : ''}"><a href="Controller?command=pe_get_report_cia_ipt"><i class="glyphicon glyphicon-list-alt"></i> Impact Propagation Tree</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
