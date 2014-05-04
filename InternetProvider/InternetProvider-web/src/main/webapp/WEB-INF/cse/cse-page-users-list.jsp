<%-- 
    Document   : cse-users-list
    Created on : 30.04.2014, 20:36:41
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="users-list" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <jsp:include page="cse-table-users.jsp" flush="true"/>
        </div>
        <div class="col-md-offset-3">
            <button type="button" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-remove-circle"></span> Add new customer</button>
        </div>
    </div>
</div>
        
<jsp:include page="generic/footer.jsp" flush="true"/>