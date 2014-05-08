<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="add-user" />
        </jsp:include>
        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-customerinfo.jsp" flush="true"/>
            <hr>
            <jsp:include page="../generic/gen-ordermap.jsp" flush="true"/>
            <div class="row">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary width-100-percent" style="margin-top:135px;">Add customer location</button>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>