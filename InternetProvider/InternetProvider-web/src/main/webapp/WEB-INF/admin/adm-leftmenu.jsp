<%-- 
    Author     : Alexander Kovriga
--%>

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="nav nav-pills nav-stacked">
        <li class="${param.active eq 'users-list' ? ' active' : ''}"><a href="Controller?command=adm_get_users" ><i class="glyphicon glyphicon-list"></i> Users</a></li>
        <li class="${param.active eq 'add-user' ? ' active' : ''}"><a href="Controller?command=adm_add_user_page"><i class="glyphicon glyphicon-plus"></i> Add user</a></li>
    </ul>
    <hr>
</div>
