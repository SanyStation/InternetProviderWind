<%-- 
    Document   : cse-page-review-order
    Created on : May 9, 2014, 3:23:57 PM
    Author     : Anatolii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="users-list" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-orderinfo.jsp" flush="true"/>
            <c:if test="${order.status == 'ENTERING'}">
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="cse_confirm_order"/>
                    <input type="hidden" name="order_id" value="${order.id}"/>
                    <input type="submit" value="Confirm order"/>
                </form>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="cse_cancel_order"/>
                    <input type="hidden" name="order_id" value="${order.id}"/>
                    <input type="submit" value="Cancel order"/>
                </form>
            </c:if>
        </div>
        <div class="col-md-offset-3">
            <!--<button type="button" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-remove-circle"></span> Add new customer</button>-->
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
