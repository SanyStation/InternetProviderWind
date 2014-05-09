<%-- 
    Document   : userinfo
    Created on : 05.05.2014, 07:36:41
    Author     : Anatolii
--%>

<div>
    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <h4>${user.name}</h4>
            </td>
        </tr>
        <tr>
            <td>
                User's id
            </td>
            <td>
                ${user.id}
            </td>
        </tr>
        <tr>
            <td>
                e-mail
            </td>
            <td>
                ${user.email}
            </td>
        </tr>
    </table>
</div>