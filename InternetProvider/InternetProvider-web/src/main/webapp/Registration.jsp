<%-- 
    Document   : Registration
    Created on : 28 квіт. 2014, 12:21:48
    Author     : myshko
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Registration page</title>
    </head>
    <body>
        <script type='text/javascript' src='js/jquery.1.7.2.min.js'>
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                function register(log, pas, eml) {
                    $.ajax({
                        type: 'POST',
                        url: 'Controller',
                        dataType: 'text',
                        data: {
                            'command': 'registration',
                            'login': log,
                            'e-mail': eml,
                            'pass': pas
                        }
                    });
                }
                ;
                $("#register").click(function() {
                    //data normalization
                    var login = $("#login").val();
                    var pass = $("#pass").val();
                    var confpass = $("#confpass").val();
                    var email = $("#email").val();
//                    $.trim(login);
//                    $.trim(pass);
//                    $.trim(confpass);
//                    $.trim(email);
                    //validation
                    $.ajax({
                        type: 'POST',
                        url: 'Controller',
                        dataType: 'text',
                        data: {
                            'command': 'validation',
                            'login': login,
                            'e-mail': email,
                            'pass': pass,
                            'confpass': confpass
                        },
                        success: function(data) {
                            if (data === "")
                                register(login, pass, email);
                            else {
                                $("#msgfield").html("");
                                $("#msgfield").append(data);
                            }
                        }
                    });
                });
            });
        </script>
        <div id="div1">




            <form class="form-horizontal" role="form" action="Controller">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Name" name="login"  id="login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email" id="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="pass" id="pass">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password (confirm)</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Password (confirm)" name="confpass" id="confpass">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="command" value="validation" class="btn btn-default" id="register">Sign up</button>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
