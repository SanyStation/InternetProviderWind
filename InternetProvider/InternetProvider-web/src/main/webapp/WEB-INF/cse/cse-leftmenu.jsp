<%-- 
    Document   : leftmenu
    Created on : 29.04.2014, 17:39:08
    Author     : oneplayer
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <script src="js/bootstrap-tab.js"></script>
        <script type="text/javascript">
            $('#myTab a').click(function(e) {
                e.preventDefault();
                $(this).tab('show');
            });
        </script>
        <li class="nav-header"></li>
         <!--"../test/cse-page-users-list.jsp"--> 
        <li class="${param.active eq 'users-list' ? ' active' : ''}"><a href="Controller?command=customers_list" >
               <!--data-toggle="pill"-->
              <i class="glyphicon glyphicon-list"></i> Customer users</a></li>
        <li class="${param.active eq 'add-user' ? ' active' : ''}"><a href="Controller?command=to_page&page=PAGE_ADD_USER"><i class="glyphicon glyphicon-plus"></i> Add user customer</a></li>
        <li class="${param.active eq 'page-si' ? ' active' : ''}"><a href="../test/cse-page-service-instances.jsp" data-toggle="pill"><i class="glyphicon glyphicon-list"></i> Service instances</a></li>
        <li class="${param.active eq 'reports' ? ' active' : ''}"><a href="#" data-toggle="pill"><i class="glyphicon glyphicon-list-alt"></i> Reports</a></li>
        <li class="${param.active eq 'tasks' ? ' active' : ''}"><a href="#" data-toggle="pill"><i class="glyphicon glyphicon-briefcase"></i> Tasks<span class="badge">42</span></a></li>
    </ul>

    <hr>
</div>
