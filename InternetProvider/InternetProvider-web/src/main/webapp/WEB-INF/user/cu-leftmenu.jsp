<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <li class="nav-header">Sections</li>
        <li class="${param.active eq 'orders' ? ' active' : ''}"><a href="Controller?command=cu_orders"><i class="glyphicon glyphicon-list"></i> Orders</a></li>
        <li class="${param.active eq 'instances' ? ' active' : ''}"><a href="Controller?command=cu_instances"><i class="glyphicon glyphicon-list"></i> Instances</a></li>
    </ul>

    <hr>
</div>
