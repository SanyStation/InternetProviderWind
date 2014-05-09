<%-- 
    Document   : cse-page-selected-task
    Created on : May 5, 2014, 1:37:11 PM
    Author     : Anatolii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Installation Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Installation Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="ie-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-taskinfo.jsp" flush="true"/>

            <c:if test="${task.status=='ACTIVE'}">
                <div id="error" style="color: red">
                    ${error}
                </div>
                <form action="Controller" method="POST">
                    <input type="hidden" name="task_id" value="${task.id}"/>
                    <c:if test="${task.type=='NEW_CABLE'}">
                        <input type="hidden" name="command" value="new_cable"/>
                        <input type="text" name="name" value=""/>
                        <input type="submit" value="Create cable"/>
                    </c:if>
                    <c:if test="${task.type=='DELETE_CABLE'}">
                        <input type="hidden" name="command" value="delete_cable"/>
                        <input type="submit" value="Delete cable"/>
                    </c:if>
                    <c:if test="${task.type=='NEW_DEVICE'}">
                        <input type="hidden" name="command" value="new_device"/>
                        <input type="text" name="name" value=""/>
                        <input type="submit" value="Create device"/>
                    </c:if>
                </form>
                <form action="Controller" method="POST">
                    <input type="hidden" name="task_id" value="${task.id}"/>
                    <input type="hidden" name="command" value="unassign_task"/>
                    <input type="submit" value="Unassign task"/>
                </form>
            </c:if>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
