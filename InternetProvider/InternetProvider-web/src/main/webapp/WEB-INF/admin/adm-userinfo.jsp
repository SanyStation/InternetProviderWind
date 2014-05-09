<%-- 
    Author     : Alexander Kovriga
--%>

<div>
    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="block_user"/>
                    <input type="hidden" name="user_id" value="${us.id}"
                    <h4>${us.name}<button type="submit" class="btn btn-default pull-right gobuttontop">
                            <span class="glyphicon glyphicon-cog"></span> Block</button>
                    </h4>
                </form>
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