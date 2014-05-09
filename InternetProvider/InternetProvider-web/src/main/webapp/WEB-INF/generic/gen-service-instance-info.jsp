<%-- 
    Document   : gen-service-instance-info
    Created on : May 9, 2014, 2:54:21 PM
    Author     : Anatolii
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9">
    <table class="table table-striped table-bordered">
        <tr><td colspan="2">
                <h4>Service info</h4></td></tr><tr>
            <td>ID:</td>
            <td>${instance.id}<!--Silver--></td></tr><tr>
            <td>Status:</td>
            <td>${instance.status}</td></tr><tr>
            <td>Service:</td>
            <td>${instance.service.name}</td></tr><tr>
            <td>Price</td>
            <td>${instance.serviceOrder.price.price} $</td></tr><tr>
            <td>Location</td>
            <td>${instance.serviceOrder.serviceLocation.address}</td></tr><tr>
            <td>Provider Location</td>
            <td>${instance.serviceOrder.providerLocation.address} (${instance.serviceOrder.providerLocation.name})</td></tr>
    </table>
    <c:if test="${instance.status == 'ACTIVE'}">
        <form action="Controller" method="POST">
            <input type="hidden" name="service_instance_id" value="${instance.id}"/>
            <div class="col-md-4"><select name="service_id" class="form-control">
                    <c:forEach var="i" items="${instance.serviceOrder.providerLocation.pricesList}">
                        <option value="${i.serviceId}">${i.service.name} - ${i.price} $</option>
                    </c:forEach>
                </select></div>
            <button type="submit" name="command" value="${param.modify_command}" class="btn btn-info">Modify instance</button>
            <button type="submit" name="command" value="${param.disconnect_command}" class="btn btn-danger">Disconnect instance</button>
        </form>
    </c:if>
</div>
