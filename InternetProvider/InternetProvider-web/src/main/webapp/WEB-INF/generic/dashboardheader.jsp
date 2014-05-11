<%-- 
    Document   : dashboardheader
    Created on : 29.04.2014, 18:56:38
    Author     : oneplayer
--%>

<div class="container">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>${param.dashboardText}
                    <a type="submit" class="btn btn-default pull-right gobuttontop" href="logout"><span class="glyphicon glyphicon-off"></span> Logout</a>
                </h4>
            </div>
            <div class="panel-body" id="pop-cont">
                Logged in as <b>${user.name}</b> (${user.email})
                <!-- Standard button -->
                <button type="button" class="btn btn-default" id="password-changer-init"  data-toggle="tooltip" data-placement="right" title="Change password"><span class="glyphicon glyphicon-cog"></span></button>
                <jsp:include page="gen-modify-password.jsp">
                    <jsp:param name="userid" value="${user.id}"/>
                </jsp:include>
            </div><!--/panel-body-->
        </div>
    </div>
</div>
