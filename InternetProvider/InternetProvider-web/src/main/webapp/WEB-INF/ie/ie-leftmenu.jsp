<%-- 
    Document   : leftmenu
    Created on : 05.05.2014, 13:31
    Author     : j_mart
--%>

<div class="col-md-3 leftmenu">
    <!-- Left column -->

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <script src="js/bootstrap-tab.js"></script>
<!--        <script type="text/javascript">
            $('#myTab a').click(function(e) {
                e.preventDefault();
                $(this).tab('show');
            });
        </script>-->
        <li class="nav-header"></li>
        <!--"../test/cse-page-users-list.jsp"--> 
        <li class="${param.active eq 'tasks' ? ' active' : ''}"><a href="Controller?command=ie_get_tasks"><i class="glyphicon glyphicon-briefcase"></i> Tasks<span class="badge">42</span></a></li>
    </ul>
    <hr>
</div>
