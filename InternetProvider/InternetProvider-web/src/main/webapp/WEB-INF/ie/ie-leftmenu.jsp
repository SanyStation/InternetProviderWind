<%-- 
    Document   : leftmenu
    Created on : 05.05.2014, 13:31
    Author     : j_mart
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <li class="nav-header"></li>
        <li class="${param.active eq 'tasks' ? ' active' : ''}"><a href="#"><i class="glyphicon glyphicon-briefcase"></i> Tasks</a>
            <ul>
                <li><a href="Controller?command=ie_get_tasks"><i class="glyphicon glyphicon-briefcase"></i>New tasks <span class="badge">42</span></a></li>
                <li><a href="Controller?command=ie_user_active_tasks"><i class="glyphicon glyphicon-briefcase"></i>Active tasks</a></li>
                <li><a href="Controller?command=ie_user_completed_tasks"><i class="glyphicon glyphicon-briefcase"></i>Completed tasks</a></li>
            </ul>
        </li>
        <li class="${param.active eq 'reports' ? ' active' : ''}"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Reports</a>
            <ul>
                <li><a href="Controller?command=ie_get_report_ri_util"><i class="glyphicon glyphicon-list-alt"></i> Routers' utilization</a></li>
                <li><a href="Controller?command=ie_get_report_ri_profit"><i class="glyphicon glyphicon-list-alt"></i> Routers' profitability</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
