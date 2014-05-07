<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <h4>${port.name}<button type="button" class="btn btn-default pull-right gobuttontop"><span class="glyphicon glyphicon-cog"></span> Modify</button></h2>
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
    </table>
</div>