<%-- 
    Author     : Alexander Kovriga
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <table class="table table-striped table-bordered">
        <tr>
            <td colspan="2">
                <h4>Task</h4>
            </td>
        </tr>
        <tr>
            <td>
                ID
            </td>
            <td>
                ${task.id}
            </td>
        </tr>
        <tr>
            <td>
                Status
            </td>
            <td>
                ${task.status}
            </td>
        </tr>
        <tr>
            <td>
                Type
            </td>
            <td>
                ${task.type}
            </td>
        </tr>
        <tr>
            <td>
                Performer
            </td>
            <td>
                ${task.user.name} (${task.user.email})
            </td>
        </tr>
        <tr>
            <td>
                Customer location
            </td>
            <td>
                ${task.serviceOrder.serviceLocation.address}
            </td>
        </tr>
        <tr>
            <td>
                Provider location
            </td>
            <td>
                ${task.serviceOrder.providerLocation.address} (${task.serviceOrder.providerLocation.name})
            </td>
        </tr>
        <tr>
            <td>
                Device
            </td>
            <td>
                <c:if test="${task.serviceOrder.serviceInstance.circuit!=null}">
                    ${task.serviceOrder.serviceInstance.circuit.port.device.id} (${task.serviceOrder.serviceInstance.circuit.port.device.name})
                </c:if>
                <c:if test="${task.serviceOrder.serviceInstance.circuit==null}">
                    ${task.serviceOrder.serviceLocation.cable.port.device.id} (${task.serviceOrder.serviceLocation.cable.port.device.name})
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                Port
            </td>
            <td>
                <c:if test="${task.serviceOrder.serviceInstance.circuit!=null}">
                    ${task.serviceOrder.serviceInstance.circuit.port.id} (${task.serviceOrder.serviceInstance.circuit.port.name})
                </c:if>
                <c:if test="${task.serviceOrder.serviceInstance.circuit==null}">
                    ${task.serviceOrder.serviceLocation.cable.port.id} (${task.serviceOrder.serviceLocation.cable.port.name})
                </c:if>
            </td>
        </tr>
    </table>
</div>