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
            <form role="form" action="Controller">
                <div class="row">

                    <div class="col-md-7">
                        <h3>Adding new user:</h3>
                        <div class="input-group ${errorname} paddingtop"  onmouseover="$(this).removeClass('has-error')">
                            <span class="input-group-addon">Name</span>
                            <input type="text" class="form-control" name="name" placeholder="Enter full name" value="${param.name}">
                        </div>
                        <div class="alert-danger alert badge  nomargin margintop" onmouseover="$(this).slideUp(100)">${messagename}</div>
                        <div class="input-group ${erroremail} paddingtop" onmouseover="$(this).removeClass('has-error')"> 
                            <span class="input-group-addon">E-mail</span>
                            <input type="text" class="form-control" name="email" placeholder="Enter e-mail" value="${param.email}">
                        </div>
                        <div class="alert-danger alert badge  nomargin margintop" onmouseover="$(this).slideUp(100)">${messageemail}</div>
                        <!--                         <div class="input-group paddingtop">
                                                    <span class="input-group-addon">Password</span>
                                                    <input type="text" class="form-control" name="password" placeholder="Enter e-mail">
                                                </div>
                                                 <div class="input-group paddingtop">
                                                    <span class="input-group-addon">Password (confirm)</span>
                                                    <input type="text" class="form-control" name="conf_password" placeholder="Enter e-mail">
                                                </div>-->
                        <div class="input-group paddingtop">
                            <span class="input-group-addon">Group</span>
                            <select name="role_id" class="form-control" value="3">
                                <option value="">Select group</option>
                                <option value="5" ${param.role_id eq 5 ? 'selected' : ''}>Customer</option>
                                <option value="2" ${param.role_id eq 2 ? 'selected' : ''}>Provision engineer</option>
                                <option value="3" ${param.role_id eq 3 ? 'selected' : ''}>Installation engineer</option>
                                <option value="4" ${param.role_id eq 4 ? 'selected' : ''}>Customer support engineer</option>
                            </select>
                        </div>                        
                        <div class="alert-danger alert badge  nomargin margintop" onmouseover="$(this).slideUp(100)">${messagebottom}</div>
                        <hr>
                        <div class="row">
                            <div class="col-md-7">
                                <button type="submit" class="btn btn-primary width-100-percent" name="command" value="adm_add_user"><span class="glyphicon glyphicon-plus"></span> Add user</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../generic/footer.jsp" flush="true"/>