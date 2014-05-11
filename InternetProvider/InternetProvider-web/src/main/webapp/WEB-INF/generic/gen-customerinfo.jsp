<%-- 
    Document   : customerinfo
    Created on : 29.04.2014, 18:53:54
    Author     : oneplayer
--%>

<div id="customerinfo">
    <table class="table table-bordered table-striped">
        <tr>
            <td colspan="2">
                <h4>${customer.name}<button type="button" class="btn btn-default pull-right gobuttontop" id="password-changer-init2" data-toggle="tooltip" data-placement="left" title="Change password"><span class="glyphicon glyphicon-cog"></span> Modify</button></h2>
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
        <tr>
            <td>
                Service Instances
            </td>
            <td>
                <a href="Controller?command=cse_get_si&customer_id=${customer.id}">${customer.countServiceInstances}</a>
            </td>
        </tr>
        <tr>
            <td>
                Service Orders
            </td>
            <td>
                <a href="Controller?command=cse_get_so&customer_id=${customer.id}">${customer.countServiceOrders}</a>
            </td>
        </tr>
    </table>
    <jsp:include page="gen-modify-password.jsp">
        <jsp:param name="userid" value="${customer.id}"/>
    </jsp:include>
</div>