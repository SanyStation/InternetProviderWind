<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Administrator's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Administrator's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="adm-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="add-user" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <form role="form">
                <div class="row">

                    <div class="col-md-7">
                        <h3>Adding new user:</h3>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Name</span>
                            <input type="text" class="form-control" placeholder="Enter full name">
                        </div>
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">E-mail</span>
                            <input type="text" class="form-control" placeholder="Enter e-mail">
                        </div>
                        <!-- Remake to SELECT -->
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Role</span>
                            <input type="text" class="form-control" placeholder="Select role">
                        </div>
                        <button type="button" class="btn btn-default margintop"><span class="glyphicon glyphicon-plus"></span> Add user</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../generic/footer.jsp" flush="true"/>