<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Administrator's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="adm-leftmenu.jsp" flush="true"/>

        <div class="col-md-9 divitem">
            <form role="form">
                <div class="row">

                    <div class="col-md-7">
                        <h3>Adding new user:</h3>
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
                        <!-- Remake to SELECT -->
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Role</span>
                            <input type="text" class="form-control" placeholder="Write role...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Password</span>
                            <input type="text" class="form-control" placeholder="Choose password...">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Password (repeat)</span>
                            <input type="text" class="form-control" placeholder="Repeat your password...">
                        </div>
                        <button type="button" class="btn btn-default margintop"><span class="glyphicon glyphicon-plus"></span> Add user</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../generic/footer.jsp" flush="true"/>