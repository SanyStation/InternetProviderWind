<%-- 
    Document   : something-wrong
    Created on : 30.04.2014, 20:36:41
    Author     : oneplayer
--%>

<jsp:include page="WEB-INF/generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Internet provider Wind"/>
    <jsp:param name="headerClass" value="background"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-6 topic boxshadow nopadding text-center text-danger">
            <h2>Error 404 - such page does not exist</h2>
            <img src="images/404.jpg" alt=""/>
        </div>
        <div class="col-md-3 col-md-offset-3 nopadding" id="right-info">
            <jsp:include page="WEB-INF/generic/rightmenu.jsp" flush="true"/>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" flush="true"/>