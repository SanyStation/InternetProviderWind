<%-- 
    Document   : cu-review-order
    Created on : May 5, 2014, 10:40:29 AM
    Author     : Anatolii
--%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer user's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer user's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cu-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="users-list" />
        </jsp:include>
        
        <jsp:include page="../generic/gen-service-instance-info.jsp">
            <jsp:param name="modify_command" value="modify_instance"/>
            <jsp:param name="disconnect_command" value="disconnect_instance"/>
        </jsp:include>

        <div class="col-md-offset-3">
            <!--<button type="button" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-remove-circle"></span> Add new customer</button>-->
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
