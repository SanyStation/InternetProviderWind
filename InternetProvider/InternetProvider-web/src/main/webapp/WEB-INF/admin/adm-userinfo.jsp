<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <h4>${us.name}<button type="button" class="btn btn-default pull-right gobuttontop"><span class="glyphicon glyphicon-cog"></span> Modify</button></h2>
            </td>
        </tr>
        <tr>
            <td>
                ID
            </td>
            <td>
                ${us.id}
            </td>
        </tr>
        <tr>
            <td>
                Name
            </td>
            <td>
                ${us.name}
            </td>
        </tr>
        <tr>
            <td>
                E-mail
            </td>
            <td>
                ${us.email}
            </td>
        </tr>
        <tr>
            <td>
                Status
            </td>
            <td>
                ${us.status}
            </td>
        </tr>
        <tr>
            <td>
                Group
            </td>
            <td>
                ${us.role.name}
            </td>
        </tr>
    </table>
</div>