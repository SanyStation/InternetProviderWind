<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <li class="nav-header"></li>
        <li class="${param.active eq 'tasks' ? ' active' : ''}"><a href="Controller?command=pe_get_tasks"><i class="glyphicon glyphicon-list"></i> Tasks</a></li>
        <li class="${param.active eq 'reports' ? ' active' : ''}"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Reports</a>
            <ul>
                <li><a href="Controller?command=pe_get_report_cia_ipt"><i class="glyphicon glyphicon-list-alt"></i> Impact Propagation Tree</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
