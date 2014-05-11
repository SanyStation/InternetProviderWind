<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="list-group">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.command eq 'customers_list' ? ' active' : ''}">
                    <a href="Controller?command=customers_list" ><i class="glyphicon glyphicon-list"></i> Customers</a></li>
                <li class="${param.command eq 'cse_add_customer_page' ? ' active' : ''}">
                    <a href="Controller?command=cse_add_customer_page"><i class="glyphicon glyphicon-plus"></i> Add customer</a></li>
            </ul>
        </li>

        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'tasks' ? ' bg-info' : ''}">
                    <a href="#"><i class="glyphicon glyphicon-briefcase"></i> Tasks</a></li>        
                <li class="${param.command eq 'cse_get_tasks' ? ' active' : ''}">
                    <a href="Controller?command=cse_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> New tasks</a></li>
                <li class="${param.command eq 'cse_user_active_tasks' ? ' active' : ''}">
                    <a href="Controller?command=cse_user_active_tasks"><i class="glyphicon glyphicon-briefcase"></i> Active tasks</a></li>
                <li class="${param.command eq 'cse_user_completed_tasks' ? ' active' : ''}">
                    <a href="Controller?command=cse_user_completed_tasks"><i class="glyphicon glyphicon-briefcase"></i> Completed tasks</a></li>
            </ul>
        </li>


        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'reports' ? ' bg-info' : ''}"><a href="#">
                        <i class="glyphicon glyphicon-list-alt"></i> Reports</a></li>
                <li class="${param.command eq 'cse_get_report_si_new' ? ' active' : ''}">
                    <a href="Controller?command=cse_get_report_si_new"><i class="glyphicon glyphicon-list-alt"></i> New orders</a>
                </li>
                <li class="${param.command eq 'cse_get_report_si_disc' ? ' active' : ''}">
                    <a href="Controller?command=cse_get_report_si_disc"><i class="glyphicon glyphicon-list-alt"></i> Disconnect orders</a>
                </li>
                <li class="${param.command eq 'cse_get_report_si_profit' ? ' active' : ''}">
                    <a href="Controller?command=cse_get_report_si_profit"><i class="glyphicon glyphicon-list-alt"></i> Profit</a>
                </li>
            </ul>
        </li>


    </ul>
    <hr>

    <c:if test="${param.report eq 'orders'}">
        <form id="validation" action="Controller" method="POST">
            <div class="input-group paddingtop">
                <span class="input-group-addon">Date from</span>
                <input type="text" id="vdFrom" class="form-control" name="vdFrom" value="${dateFrom}" placeholder="Enter date">
                <label for="vdFrom" class="error" style="display: none;"></label>
            </div>
            <div class="input-group paddingtop">
                <span class="input-group-addon">Date to</span>
                <input type="text" id="vdTo" class="form-control" name="vdTo" value="${dateTo}" placeholder="Enter date">
                <label for="vdTo" class="error" style="display: none;"></label>
            </div>
            <hr>
            <div>
                <button type="submit" name="command" value="${command}" class="btn btn-primary paddingtop width-100-percent"><span class="glyphicon glyphicon-refresh"></span> Generate</button>
            </div>
        </form>
    </c:if>
    <c:if test="${param.report eq 'profit'}">
        <form id="validation" action="Controller" method="POST">
            <div class="input-group paddingtop">
                <span class="input-group-addon">Month</span>
                <input type="text" id="vdByMonth" class="form-control" name="vdByMonth" value="${date}" placeholder="Enter a month">
            </div>
            <hr>
            <div>
                <button type="submit" name="command" value="cse_get_report_si_profit" class="btn btn-primary paddingtop width-100-percent"><span class="glyphicon glyphicon-refresh"></span> Generate</button>
            </div>
        </form>
    </c:if>
</div>
