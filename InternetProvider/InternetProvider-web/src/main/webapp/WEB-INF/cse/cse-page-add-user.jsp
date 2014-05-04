<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <form role="form">
                <div class="row">

                    <div class="col-md-7">
                        <h3>Adding new customer:</h3>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Firstname</span>
                            <input type="text" class="form-control" placeholder="Write firstname...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Lastname</span>
                            <input type="text" class="form-control" placeholder="Write lastname...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">e-mail</span>
                            <input type="text" class="form-control" placeholder="Write e-mail...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Password</span>
                            <input type="text" class="form-control" placeholder="Choose password...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Password (repeat)</span>
                            <input type="text" class="form-control" placeholder="Repeat your password...">
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-5">
                        <h4>Customer's desired location is:</h4>
                        <input type="text" class="form-control" placeholder="Enter desired location...">
                        <hr>
                        <div id="ratechoose">
                            <input type="radio" name="rate" value="Platinum">
                            Platinum Internet,&nbsp;<span id="platinum">55$ month</span><br>
                            <input type="radio" name="rate" value="Gold">
                            Gold Internet,&nbsp;<span id="platinum">35$ month</span><br>
                            <input type="radio" name="rate" value="Silver" disabled="true">
                            Silver Internet,&nbsp;<span id="platinum">25$ month</span><br>

                            <button type="button" class="btn btn-primary width-100-percent" style="margin-top:135px;">Add customer</button>
                        </div>

                    </div>
                    <jsp:include page="../generic/gen-ordermap.jsp" flush="true"/>

                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../generic/footer.jsp" flush="true"/>