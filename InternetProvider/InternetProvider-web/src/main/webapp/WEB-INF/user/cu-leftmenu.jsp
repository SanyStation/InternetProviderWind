<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked panel panel-${param.active eq 'user' ? 'primary' : 'default'}  nomargin">
                <li class="${param.command eq 'cu_orders' ? ' active' : ''}"><a href="Controller?command=cu_orders"><i class="glyphicon glyphicon-list"></i> Orders</a></li>
                <li class="${param.command eq 'cu_instances' ? ' active' : ''}"><a href="Controller?command=cu_instances"><i class="glyphicon glyphicon-list"></i> Service instances</a></li>
            </ul>
    </ul>

    <hr>
</div>
