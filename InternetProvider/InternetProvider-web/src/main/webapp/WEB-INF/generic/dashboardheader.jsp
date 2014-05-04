<%-- 
    Document   : dashboardheader
    Created on : 29.04.2014, 18:56:38
    Author     : oneplayer
--%>

<div class="container">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>${param.dashboardText}
                    <form action="logout" method="GET">
                        <button type="submit" class="btn btn-default pull-right gobuttontop"><span class="glyphicon glyphicon-off"></span> Logout</button>
                    </form>
                </h4>
            </div>
            <div class="panel-body">
                Logged in as <b>${user.name}</b> (${user.email})
                <!-- Standard button -->
                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-cog"></span></button>
            </div><!--/panel-body-->
        </div>
    </div>
</div>
