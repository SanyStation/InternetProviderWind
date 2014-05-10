<%-- 
    Document   : gen-table-service_instance
    Created on : May 9, 2014, 2:54:21 PM
    Author     : Anatolii
--%>
<div class="col-md-12 nopadding">
    <table class="table table-striped table-bordered">
        <tr><td colspan="2">
                <h4>Service info</h4></td></tr><tr>
            <td>ID:</td>
            <td>${instance.id}</td></tr><tr>
            <td>Status:</td>
            <td>${instance.status}</td></tr><tr>
            <td>Service:</td>
            <td>${instance.service.name}</td></tr><tr>
            <td>Price</td>
            <td>${instance.serviceOrder.price.price} $</td></tr><tr>
            <td>Location</td>
            <td>${instance.serviceOrder.serviceLocation.address}</td></tr><tr>
            <td>Provider Location</td>
            <td>${instance.serviceOrder.providerLocation.address} (${instance.serviceOrder.providerLocation.name})</td></tr>
    </table>
</div>
