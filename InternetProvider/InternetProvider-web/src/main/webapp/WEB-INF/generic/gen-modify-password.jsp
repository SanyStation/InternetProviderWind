<%-- 
    Document   : cse-add-user
    Created on : 29.04.2014, 22:36:24
    Author     : oneplayer
--%>


<div style="display: none;">
    <form  id="password-changer" role="form" action="Controller" method="POST">
        <div class="row">

            <div class="col-md-12">
                <h3>Change password:</h3>
                <div class="input-group paddingtop">
                    <span class="input-group-addon">Password</span>
                    <input type="hidden" name="command" value="change_password"/>
                    
                    <input type="hidden" name="user_id" value="${param.userid}"/>
                    <input type="password" name="password" class="form-control" placeholder="Choose password...">
                </div>
                <div class="input-group paddingtop">
                    <span class="input-group-addon">Password (repeat)</span>
                    <input type="password" name="conf_password" class="form-control" placeholder="Repeat your password...">
                </div>
                <button type="submit" name="command=" value="change_password" class="btn btn-success margintop"><span class="glyphicon glyphicon-ok"></span> Change</button>
            </div>
        </div>
    </form>
</div>