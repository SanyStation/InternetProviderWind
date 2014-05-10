<%-- 
    Document   : cse-users-list
    Created on : 30.04.2014, 20:36:41
    Author     : oneplayer
--%>

<jsp:include page="WEB-INF/generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Internet provider Wind"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-9 divitem nopadding">
            <iframe src="map.jsp" width="100%" height="75%" style="min-height: 460px;"></iframe>
        </div>
        <div class="col-md-3 nopadding">
            <div class="largefont paddingleftright nomargin headercolor whitetext borderleft borderright boxshadow">
                <ul id="contacts" class="nav nav-pills nav-stacked">
                    <li class="nav-header"></li>
                    <li><i class="glyphicon glyphicon-phone-alt"></i> 044 388 02 02<li>
                    <li><i class="glyphicon glyphicon-headphones"></i> Skype: IPWind</li>
                    <li><i class="glyphicon glyphicon-home"></i> Address:<br>Borshahivska str. 33, office 13</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:include page="WEB-INF/generic/footer.jsp" flush="true"/>