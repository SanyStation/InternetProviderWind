<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered table-striped">
        <tr>
            <td colspan="2">
                <h4>${circuit.name}</h4>
            </td>
        </tr>
        <tr>
            <td>
                Circuit
            </td>
            <td>
                ${circuit.id} (${circuit.name})
            </td>
        </tr>
        <tr>
            <td>
                Service instance
            </td>
            <td>
                ${circuit.serviceInstanceId} (${circuit.serviceInstance.name})
            </td>
        </tr>
        <tr>
            <td>
                Port
            </td>
            <td>
                ${circuit.portId} (${circuit.port.name})
            </td>
        </tr>
    </table>
</div>