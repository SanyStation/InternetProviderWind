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
                <li class="${param.command eq 'tasks' ? ' active' : ''}">
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
                <li class="${param.command eq 'reports' ? ' active' : ''}"><a href="#">
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
</div>
