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
                Circuit id
            </td>
            <td>
                ${circuit.id}
            </td>
        </tr>
        <tr>
            <td>
                Circuit name
            </td>
            <td>
                ${circuit.name}
            </td>
        </tr>
        <tr>
            <td>
                Service instance id
            </td>
            <td>
                ${circuit.serviceInstanceId}
            </td>
        </tr>
        <tr>
            <td>
                Service instance name
            </td>
            <td>
                ${circuit.serviceInstance.name}
            </td>
        </tr>
        <tr>
            <td>
                Port id
            </td>
            <td>
                ${circuit.portId}
            </td>
        </tr>
        <tr>
            <td>
                Port name
            </td>
            <td>
                ${circuit.port.name}
            </td>
        </tr>
    </table>
</div>