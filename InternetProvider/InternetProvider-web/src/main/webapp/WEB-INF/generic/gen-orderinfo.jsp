<%-- 
    Document   : orderinfo
    Created on : 29.04.2014, 18:58:39
    Author     : oneplayer
--%>

<div class="col-md-5 nopadding">
    <table class="table table-striped">
        <tr>
            <td colspan="2">
                <h4>Order info</h2>
            </td>
        </tr>
        <tr>
            <td>
                Service:
            </td>
            <td>
                ${order.service.name}
                <!--Silver-->
            </td>
        </tr>
        <tr>
            <td>
                Price:
            </td>
            <td>
                ${order.price.price} $
            </td>
        </tr>
        <tr>
            <td>
                Address:
            </td>
            <td>
                ${order.serviceLocation.address}
            </td>
        </tr>
        <tr>
            <td>
                Order status:
            </td>
            <td>
                ${order.status}
            </td>
        </tr>
        <tr>
            <td>
                Service status:
            </td>
            <td>
                ${order.serviceInstance.status}
            </td>
        </tr>
    </table>
</div>
