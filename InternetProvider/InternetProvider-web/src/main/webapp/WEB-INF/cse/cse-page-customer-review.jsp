<%-- 
    Document   : index
    Created on : 29.04.2014, 17:22:20
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true"/>      
        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-customerinfo.jsp" flush="true"/>
            <div class="row divitem nomargin">
                <script type="text/javascript">                    
                    var customer = ${customer.id};
                </script>
                <iframe src="map.jsp" width="100%" height="75%"  style="min-height: 460px;"></iframe>
            </div>
        </div>


    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>