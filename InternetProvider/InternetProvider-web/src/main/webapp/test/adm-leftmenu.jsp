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
        <li class="active"><a href="../test/adm-page-users-list.jsp" data-toggle="pill"><i class="glyphicon glyphicon-list"></i> Users</a></li>
        <li><a href="../test/adm-page-add-user.jsp" data-toggle="tab"><i class="glyphicon glyphicon-plus"></i> Add user</a></li>
    </ul>

    <hr>
</div>
