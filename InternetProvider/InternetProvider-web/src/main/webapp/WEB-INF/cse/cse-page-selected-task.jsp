<%-- 
    Document   : cse-page-selected-task
    Created on : May 5, 2014, 1:37:11 PM
    Author     : Anatolii
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-taskinfo.jsp" flush="true"/>
            
            <c:if test="${task.status=='ACTIVE'}">
                <form action="Controller" method="POST">
                    <input type="hidden" name="task_id" value="${task.id}"/>
                    <input type="hidden" name="command" value="send_bill"/>
                    <input type="submit" value="Send Bill"/>
                </form>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
