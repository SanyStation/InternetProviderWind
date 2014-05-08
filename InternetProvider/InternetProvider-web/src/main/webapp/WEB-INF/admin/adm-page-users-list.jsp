<%-- 
    Document   : cse-users-list
    Created on : 30.04.2014, 20:36:41
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Administrator's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Administrator's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="adm-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <jsp:include page="adm-table-users.jsp" flush="true"/>
        </div>
        <div class="col-md-offset-3">
            <button type="button" class="btn btn-default margintop"><span class="glyphicon glyphicon-remove-circle"></span> Add new user</button>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>