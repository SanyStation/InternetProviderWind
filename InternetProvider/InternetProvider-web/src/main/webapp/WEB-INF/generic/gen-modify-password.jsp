<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="display: none;">
    <form  id="password-changer" role="form" action="Controller" method="POST">
        <div class="row">

            <div class="col-md-12">
                <h3>Change password:</h3>
                <c:if test="${param.userid == user.id}">
                    <div class="input-group paddingtop">
                        <span class="input-group-addon">Current password</span>
                        <input type="password" name="old_password" class="form-control" placeholder="Enter your current password...">
                    </div>
                </c:if>
                <div class="input-group paddingtop">
                    <span class="input-group-addon">New password</span>
                    <input type="hidden" name="command" value="change_password"/>
                    
                    <input type="hidden" name="user_id" value="${param.userid}"/>
                    <input type="password" name="password" class="form-control" placeholder="Enter new password...">
                </div>
                <div class="input-group paddingtop">
                    <span class="input-group-addon">Confirm</span>
                    <input type="password" name="conf_password" class="form-control" placeholder="Repeat new password...">
                </div>
                <button type="submit" name="command=" value="change_password" class="btn btn-success margintop"><span class="glyphicon glyphicon-ok"></span> Change</button>
            </div>
        </div>
    </form>
</div>