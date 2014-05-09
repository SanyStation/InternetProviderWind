<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered">
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
                ${task.user.name}
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
                Port ID
            </td>
            <td>
                ${task.serviceOrder.serviceInstance.circuit.port.id}
            </td>
        </tr>
        <tr>
            <td>
                Device ID
            </td>
            <td>
                ${task.serviceOrder.serviceInstance.circuit.port.device.id}
            </td>
        </tr>
        <tr>
            <td>
                Device
            </td>
            <td>
                ${task.serviceOrder.serviceInstance.circuit.port.device.name}
            </td>
        </tr>
    </table>
</div>