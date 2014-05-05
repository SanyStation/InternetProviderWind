<%-- 
    Document   : cse-page-selected-task
    Created on : May 5, 2014, 1:37:11 PM
    Author     : Anatolii
--%>


<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <br/>ID:${task.id}
            <br/>Status:${task.status}
            <br/>Type:${task.type}
            <br/>Performer:${task.user.name}
            <form action="Controller" method="POST">
                <input type="hidden" name="command" value="send_bill"/>
                <input type="hidden" name="task_id" value="${task.id}"/>
                <input type="submit" value="Sent Bill"/>
            </form>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>
        
<jsp:include page="../generic/footer.jsp" flush="true"/>
