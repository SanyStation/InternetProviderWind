<%-- 
    Document   : ie-page-tasks-list
    Created on : May 5, 2014, 5:13:08 AM
    Author     : j_mart
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="ie-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="tasks" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <jsp:include page="ie-table-tasks.jsp" flush="true"/>
        </div>
        <div class="col-md-offset-3">
            <!--<button type="button" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-remove-circle"></span> Add new customer</button>-->
        </div>
    </div>
</div>