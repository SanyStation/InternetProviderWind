<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="list-group">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'tasks' ? ' bg-info' : ''}"><a href="#"><i class="glyphicon glyphicon-briefcase"></i> Tasks</a>
                <li class="${param.command eq 'pe_get_tasks' ? ' active' : ''}"><a href="Controller?command=pe_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> New tasks</a></li>
                <li class="${param.command eq 'pe_user_active_tasks' ? ' active' : ''}"><a href="Controller?command=pe_user_active_tasks"><i class="glyphicon glyphicon-briefcase"></i> Active tasks</a></li>
                <li class="${param.command eq 'pe_user_completed_tasks' ? ' active' : ''}"><a href="Controller?command=pe_user_completed_tasks"><i class="glyphicon glyphicon-briefcase"></i> Completed tasks</a></li>
            </ul>
        </li>

        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.active eq 'reports' ? ' bg-info' : ''}"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Reports</a>
                <li class="${param.command eq 'pe_get_report_cia_ipt' ? ' active' : ''}"><a href="Controller?command=pe_get_report_cia_ipt"><i class="glyphicon glyphicon-list-alt"></i> Impact Propagation Tree</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
