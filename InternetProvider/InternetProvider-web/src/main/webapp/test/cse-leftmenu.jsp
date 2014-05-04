<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <script type="text/javascript">
            $('#myTab a').click(function(e) {
                e.preventDefault();
                $(this).tab('show');
            });
        </script>
        <li class="nav-header"></li>
        <li class="active"><a href="../test/cse-page-users-list.jsp" data-toggle="pill"><i class="glyphicon glyphicon-list"></i> Customer users</a></li>
        <li><a href="../test/cse-page-add-user.jsp" data-toggle="tab"><i class="glyphicon glyphicon-plus"></i> Add user customer</a></li>
        <li><a href="../test/cse-page-service-instances.jsp" data-toggle="pill"><i class="glyphicon glyphicon-list"></i> Service instances</a></li>
        <li><a href="../test/cse-page-reports.jsp" data-toggle="pill"><i class="glyphicon glyphicon-list-alt"></i> Reports</a></li>
        <li><a href="../test/cse-page-tasks.jsp" data-toggle="pill"><i class="glyphicon glyphicon-briefcase"></i> Tasks<span class="badge">42</span></a></li>
    </ul>

    <hr>
</div>
