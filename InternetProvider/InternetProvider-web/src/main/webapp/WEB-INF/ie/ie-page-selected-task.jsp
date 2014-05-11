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

        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-taskinfo.jsp" flush="true"/>

            <c:if test="${task.status=='ACTIVE'}">
                <div id="error" style="color: red">
                    ${error}
                </div>
                <form action="Controller" method="POST">
                    <input type="hidden" name="task_id" value="${task.id}"/>
                    <c:if test="${task.type=='NEW_CABLE'}">
                        <div class="col-md-5 input-group paddingtop">
                            <span class="input-group-addon">Cable name</span>
                            <input type="text" class="form-control" name="name" value="" placeholder="Enter the cable name">
                        </div>
                        <hr>
                        <button type="submit" name="command" value="new_cable" class="btn btn-success btn-lg">Create cable</button>
                    </c:if>
                    <c:if test="${task.type=='DELETE_CABLE'}">
                        <button type="submit" name="command" value="delete_cable" class="btn btn-danger">Delete cable</button>
                    </c:if>
                    <c:if test="${task.type=='NEW_DEVICE'}">
                        <div class="col-md-5 input-group paddingtop">
                            <span class="input-group-addon">Device name</span>
                            <input type="text" class="form-control" name="name" value="" placeholder="Enter the device name">
                        </div>
                        <hr>
                        <button type="submit" name="command" value="new_device" class="btn btn-success btn-lg">Create device</button>
                    </c:if>
                    <button type="submit" name="command" value="unassign_task" class="btn btn-danger pull-right">Unassign task</button>
                </form>
            </c:if>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
