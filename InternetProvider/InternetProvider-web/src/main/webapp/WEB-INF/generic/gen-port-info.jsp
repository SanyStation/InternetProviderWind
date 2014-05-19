<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered table-striped">
        <tr>
            <td colspan="2">
                <h4>${port.name}</h4>
            </td>
        </tr>
        <tr>
            <td>
                Port id
            </td>
            <td>
                ${port.id}
            </td>
        </tr>
        <tr>
            <td>
                Port name
            </td>
            <td>
                ${port.name}
            </td>
        </tr>
        <tr>
            <td>
                Port status
            </td>
            <td>
                ${port.status}
            </td>
        </tr>
        <tr>
            <td>
                Device id
            </td>
            <td>
                ${port.deviceId}
            </td>
        </tr>
        <tr>
            <td>
                Device name
            </td>
            <td>
                ${port.device.name}
            </td>
        </tr>
        <tr>
            <td>
                Circuit id
            </td>
            <td>
                ${port.circuit.id}
            </td>
        </tr>
        <tr>
            <td>
                Circuit name
            </td>
            <td>
                ${port.circuit.name}
            </td>
        </tr>
        <tr>
            <td>
                Cable id
            </td>
            <td>
                ${port.cable.id}
            </td>
        </tr>
        <tr>
            <td>
                Cable name
            </td>
            <td>
                ${port.cable.name}
            </td>
        </tr>
    </table>
</div>