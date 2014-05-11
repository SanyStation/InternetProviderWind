<%-- 
    Author     : Alexander Kovriga
--%>

<div class="col-md-3 leftmenu">

    <ul id="myTab" class="list-group">
        <li class="list-group-item">
            <ul class="nav nav-pills nav-stacked">
                <li class="${param.command eq 'adm_get_users' ? ' active' : ''}"><a href="Controller?command=adm_get_users" ><i class="glyphicon glyphicon-list"></i> Users</a></li>
                <li class="${param.command eq 'adm_add_user_page' ? ' active' : ''}"><a href="Controller?command=adm_add_user_page"><i class="glyphicon glyphicon-plus"></i> Add user</a></li>
            </ul>
    </ul>
    <hr>
</div>
