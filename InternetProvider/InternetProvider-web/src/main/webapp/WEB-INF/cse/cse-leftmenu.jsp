<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <script src="js/bootstrap-tab.js"></script>
        <li class="${param.active eq 'users-list' ? ' active' : ''}"><a href="Controller?command=customers_list" ><i class="glyphicon glyphicon-list"></i> Customers</a></li>
        <li class="${param.active eq 'add-user' ? ' active' : ''}"><a href="Controller?command=to_page&page=PAGE_CSE_ADD_USER"><i class="glyphicon glyphicon-plus"></i> Add customer</a></li>
        <li class="${param.active eq 'page-si' ? ' active' : ''}"><a href="Controller?command=to_page&page=PAGE_CSE_SERVICE_INSTANCES"><i class="glyphicon glyphicon-list"></i> Service instances</a></li>
        <li class="${param.active eq 'tasks' ? ' active' : ''}"><a href="Controller?command=cse_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> Tasks<span class="badge">42</span></a></li>
        <li class="${param.active eq 'reports' ? ' active' : ''}"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Reports</a>
            <ul>
                <li><a href="Controller?command=cse_get_report_si_new"><i class="glyphicon glyphicon-list-alt"></i> New orders</a></li>
                <li><a href="Controller?command=cse_get_report_si_disc"><i class="glyphicon glyphicon-list-alt"></i> Disconnect orders</a></li>
                <li><a href="Controller?command=cse_get_report_si_profit"><i class="glyphicon glyphicon-list-alt"></i> Profit</a></li>
            </ul>
        </li>
    </ul>
    <hr>
</div>
