<%-- 
    Document   : cu-review-order
    Created on : May 5, 2014, 10:40:29 AM
    Author     : Anatolii
--%>
<%@page import="com.netcracker.wind.entities.Price"%>
<%@page import="java.util.List"%>
<%@page import="com.netcracker.wind.entities.ServiceInstance"%>
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
            <table class="table table-striped">
                <tr>
                    <td colspan="2">
                        <h4>Service info</h2>
                    </td>
                </tr>
                <tr>
                    <td>
                        ID:
                    </td>
                    <td>
                        ${instance.id}
                        <!--Silver-->
                    </td>
                </tr>
                <tr>
                    <td>
                        Status:
                    </td>
                    <td>
                        ${instance.status}
                    </td>
                </tr>
                <tr>
                    <td>
                        Service:
                    </td>
                    <td>
                        ${instance.service.name}
                    </td>
                </tr>
                <tr>
                    <td>
                        Price
                    </td>
                    <td>
                        ${instance.serviceOrder.price.price} $
                    </td>
                </tr>
            </table>
            <c:if test="${instance.status == 'ACTIVE'}">
                <form action="Controller" method="POST">
                    <input type="hidden" name="service_instance_id" value="${instance.id}"/>
                    <select name="service_id">
                        <c:forEach var="i" items="${instance.serviceOrder.providerLocation.pricesList}">
                            <option value="${i.serviceId}">${i.service.name} - ${i.price} $</option>
                        </c:forEach>
                    </select>
                    <button type="submit" name="command" value="modify_instance">Modify instance</button>
                    <button type="submit" name="command" value="disconnect_instance">Disconnect instance</button>
                </form>
            </c:if>
        </div>
        <div class="col-md-offset-3">
            <!--<button type="button" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-remove-circle"></span> Add new customer</button>-->
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
