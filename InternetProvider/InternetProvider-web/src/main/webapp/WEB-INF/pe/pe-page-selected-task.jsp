<%-- 
    Document   : cse-page-selected-task
    Created on : May 5, 2014, 1:37:11 PM
    Author     : Anatolii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="pe-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-taskinfo.jsp" flush="true"/>

            <c:if test="${task.status=='ACTIVE'}">
                <form action="Controller" method="POST">
                    <input type="hidden" name="task_id" value="${task.id}"/>
                    <c:if test="${task.type=='CREATE_CIRCUIT'}">
                        <button type="submit" name="command" value="setup_circuit" class="btn btn-success">Setup circuit</button>
                    </c:if>
                    <c:if test="${task.type=='MODIFY_CIRCUIT'}">
                        <button type="submit" name="command" value="modify_circuit" class="btn btn-info">Modify circuit</button>
                    </c:if>
                    <c:if test="${task.type=='DELETE_CIRCUIT'}">
                        <button type="submit" name="command" value="delete_circuit" class="btn btn-danger">Delete circuit</button>
                    </c:if>
                    <button type="submit" name="command" value="unassign_task" class="btn btn-danger pull-right">Unassign task</button>
                </form>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
