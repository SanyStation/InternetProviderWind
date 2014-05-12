<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : leftmenu
    Created on : 05.05.2014, 13:31
    Author     : j_mart
--%>

<jsp:include page="../generic/gen-tasks-scriplet.jsp" flush="false" />

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="list-group">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'tasks' ? ' bg-info' : ''}"><a href="#"><i class="glyphicon glyphicon-briefcase"></i> Tasks</a>

                <li class="${param.command eq 'ie_get_tasks' ? ' active' : ''}  ${newTasks > 0 ? 'alert-success' : ''}">
                    <a href="Controller?command=ie_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> New tasks 
                        <c:if test="${newTasks > 0}">
                            <span class="badge pull-right alert-danger">${newTasks}</span>
                        </c:if>
                    </a>
                </li>
                <li class="${param.command eq 'ie_user_active_tasks' ? ' active' : ''}">
                    <a href="Controller?command=ie_user_active_tasks"><i class="glyphicon glyphicon-briefcase"></i> Active tasks 
                        <c:if test="${activeTasks > 0}">
                            <span class="badge pull-right">${activeTasks}</span>
                        </c:if>
                    </a>
                </li>
                <li class="${param.command eq 'ie_user_completed_tasks' ? ' active' : ''}">
                    <a href="Controller?command=ie_user_completed_tasks"><i class="glyphicon glyphicon-briefcase"></i> Completed tasks 
                        <c:if test="${completedTasks > 0}">
                            <span class="badge pull-right">${completedTasks}</span>
                        </c:if>
                    </a>
                </li>
            </ul>
        </li>
        
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'reports' ? ' bg-info' : ''}"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Reports</a>

                <li class="${param.command eq 'ie_get_report_ri_util' ? ' active' : ''}"><a href="Controller?command=ie_get_report_ri_util"><i class="glyphicon glyphicon-list-alt"></i> Routers' utilization</a></li>
                <li class="${param.command eq 'ie_get_report_ri_profit' ? ' active' : ''}"><a href="Controller?command=ie_get_report_ri_profit"><i class="glyphicon glyphicon-list-alt"></i> Routers' profitability</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
