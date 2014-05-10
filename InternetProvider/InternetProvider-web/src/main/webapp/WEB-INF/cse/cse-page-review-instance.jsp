<%-- 
    Document   : cse-page-review-instance
    Created on : May 9, 2014, 3:04:39 PM
    Author     : Anatolii
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true" />

        <jsp:include page="../generic/gen-service-instance-info.jsp">
            <jsp:param name="modify_command" value="cse_modify_instance"/>
            <jsp:param name="disconnect_command" value="cse_disconnect_instance"/>
        </jsp:include>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
