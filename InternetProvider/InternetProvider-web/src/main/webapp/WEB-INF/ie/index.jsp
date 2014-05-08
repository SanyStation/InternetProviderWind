<%-- 
    Document   : index ie
    Created on : 05.05.2014, 13:31
    Author     : j_mart
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Installation Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Installation Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="ie-leftmenu.jsp" flush="true"/>      
        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-userinfo.jsp" flush="true"/>
            <div class="row divitem nomargin">
                <jsp:include page="../generic/gen-orderinfo.jsp" flush="true"/>
                <jsp:include page="../generic/gen-ordermap.jsp" flush="true"/>
            </div>
        </div>


    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>