<%-- 
    Document   : customerinfo
    Created on : 29.04.2014, 18:53:54
    Author     : oneplayer
--%>

<div>
    <table class="table table-bordered">
        <tr>
            <td colspan="2">
                <h4>${customer.name}<button type="button" class="btn btn-default pull-right gobuttontop"><span class="glyphicon glyphicon-cog"></span> Modify</button></h2>
            </td>
        </tr>
        <tr>
            <td>
                ID
            </td>
            <td>
                ${customer.id}
            </td>
        </tr>
        <tr>
            <td>
                E-mail
            </td>
            <td>
                ${customer.email}
            </td>
        </tr>
                <tr>
            <td>
                Status
            </td>
            <td>
                ${customer.status}
            </td>
        </tr>
    </table>
</div>