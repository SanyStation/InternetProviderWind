<%-- 
    Document   : gen-service-instance-info
    Created on : May 9, 2014, 2:54:21 PM
    Author     : Anatolii
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-9 nopadding">
    <jsp:include page="gen-table-service-instance.jsp" flush="true" />
        
    <c:if test="${instance.status == 'ACTIVE'}">
        <div class="row">
            <form action="Controller" method="POST">
                <input type="hidden" name="service_instance_id" value="${instance.id}"/>
                <div class="col-md-6">
                    <div class="input-group ">
                        <span class="input-group-addon">Select new service</span>
                        <select name="service_id" class="form-control">
                            <c:forEach var="i" items="${instance.serviceOrder.providerLocation.pricesList}">
                                <c:if test="${i.serviceId != instance.serviceId}">
                                    <option value="${i.serviceId}">${i.service.name} - ${i.price} $</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <button type="submit" name="command" value="${param.modify_command}" class="btn btn-info">Modify instance</button>
                    <button type="submit" name="command" value="${param.disconnect_command}" class="btn btn-danger pull-right">Disconnect instance</button>
                </div>
            </form>
        </div>
    </c:if>
</div>
