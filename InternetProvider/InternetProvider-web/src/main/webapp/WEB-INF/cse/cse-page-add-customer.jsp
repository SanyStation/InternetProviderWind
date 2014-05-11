<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="add-user" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <form role="form" action="Controller?command=cse_add_customer" method="POST">
                <div class="row">

                    <div class="col-md-7">
                        <h3>Adding new customer:</h3>
                        <div class="input-group ${errorname} paddingtop"  onmouseover="$(this).removeClass('has-error')">
                            <span class="input-group-addon">Name</span>
                            <input type="text" class="form-control" name="name" placeholder="Enter full name" value="${param.name}">
                        </div>
                        <div class="alert-danger alert badge  nomargin margintop" onmouseover="$(this).slideUp(100)">${messagename}</div>
                        <div class="input-group ${erroremail} paddingtop" onmouseover="$(this).removeClass('has-error')"> 
                            <span class="input-group-addon">E-mail</span>
                            <input type="text" class="form-control" name="email" placeholder="Enter e-mail" value="${param.email}">
                            <label for="email">${message}</label>
                        </div>
                        <div class="alert-danger alert badge  nomargin margintop" onmouseover="$(this).slideUp(100)">${messageemail}</div>
                    </div>
                </div>
                        <button type="submit" id="Sender" class="btn btn-primary btn-lg margintop">Add customer</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>