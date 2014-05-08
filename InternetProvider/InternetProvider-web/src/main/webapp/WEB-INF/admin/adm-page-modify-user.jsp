<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer support engineer's dashboard"/>
</jsp:include>

<jsp:include page="generic/dashboardheader.jsp" flush="true"/>


<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <jsp:include page="generic/gen-modify-user.jsp" flush="true"/>
        </div>
    </div>
</div>
<jsp:include page="../generic/footer.jsp" flush="true"/>