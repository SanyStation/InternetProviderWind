<%-- 
    Document   : cu-review-order
    Created on : May 5, 2014, 10:40:29 AM
    Author     : Anatolii
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer user's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cu-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="users-list" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-orderinfo.jsp" flush="true"/>
            <c:if test="${order.status == 'ENTERING'}">
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="confirm_order"/>
                    <input type="hidden" name="order_id" value="${order.id}"/>
                    <input type="submit" value="Confirm order"/>
                </form>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="cancel_order"/>
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
