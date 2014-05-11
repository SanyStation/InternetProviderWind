<%-- 
    Document   : provider-info
    Created on : 11.05.2014, 17:20:59
    Author     : oneplayer
--%>
<div class="col-md-12 nopadding" id="provider-info">
    <div class="layer2 col-md-offset-7 col-md-3">

    </div>
    <img id="header-logo" src="images/header-logo2.png" alt=""/>
    <div class="col-md-8 topic boxshadow margintoplarge">
        <div class="col-md-6">
            <h3><i class="glyphicon glyphicon-ok galka larger-font22"></i> High quality.</h3>
            <h3><i class="glyphicon glyphicon-ok galka larger-font22"></i> High speed.</h3>
            <h3><i class="glyphicon glyphicon-ok galka larger-font22"></i> Stable work.</h3>
            <h3><i class="glyphicon glyphicon-ok galka larger-font22"></i> Low rates.</h3>          
        </div>
        <div class="col-md-6">
            <img src="images/Internet-speedometer.jpg" width="240" height="240"/>
        </div>
        <div class="col-md-12">
            <h4>Order Internet for home or business.</h4>
            <h4>High service quality and uptime.</h4>
            <h4>Price from 25$/month.</h4>
            <button id="btn-get-order" class="btn btn-primary btn-lg" id="btn-get-order">Get order!</button>
        </div>
    </div>
</div>
<script>
    $("#btn-get-order").click(function() {
        $("#provider-info").addClass("hidden");
        $("#map").removeClass("hidden");
    });
</script>
