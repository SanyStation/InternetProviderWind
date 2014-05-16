<%-- 
    Document   : orderinfo
    Created on : 29.04.2014, 18:58:39
    Author     : oneplayer
--%>

<div class="col-md-12 nopadding">
    <table class="table table-striped table-bordered"><tr><td colspan="2">
                <h4>Order info</h2></td></tr><tr>
            <td>Service</td>
            <td>${order.service.name}</td></tr><tr>
            <td>Price, $</td>
            <td>${order.price.price}</td></tr><tr>
            <td>Location</td>
            <td>${order.serviceLocation.address}</td></tr><tr>
            <td>Provider Location</td>
            <td>${order.providerLocation.address} (${order.providerLocation.name})</td></tr><tr>
            <td>Entering date</td>
            <td>${order.dateEnter}</td></tr><tr>
            <td>Processing date</td>
            <td>${order.dateProces}</td></tr><tr>
            <td>Complete date</td>
            <td>${order.dateComplete}</td></tr><tr>
            <td>Type</td>
            <td>${order.scenario}</td></tr><tr>
            <td>Order status:</td>
            <td>${order.status}</td></tr><tr>
            <td>Service status:</td>
            <td>${order.serviceInstance.status}</td></tr>
    </table>
</div>
