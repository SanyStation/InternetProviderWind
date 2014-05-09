<%-- 
    Document   : wrong-task-selected
    Created on : 09.05.2014, 10:43:00
    Author     : j_mart
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" flush="true">
    <jsp:param name="titleText" value="Wrong task selected"/>
</jsp:include>

<jsp:include page="dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Wrong task selected"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-3 divitem"></div>



        <div class="col-md-9 divitem">
            <div class="alert alert-error">
                <h4>Wrong task selected!</h4>
                It seems that someone took it right before you...<br/>
                <button type="button" class="btn"  onclick="window.history.back()" >Back</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" flush="true"/>
