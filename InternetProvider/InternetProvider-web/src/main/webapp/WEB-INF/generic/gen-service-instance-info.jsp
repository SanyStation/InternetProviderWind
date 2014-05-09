<%-- 
    Document   : gen-service-instance-info
    Created on : May 9, 2014, 2:54:21 PM
    Author     : Anatolii
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
