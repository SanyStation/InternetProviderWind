<%-- 
    Document   : login
    Created on : 01.04.2014, 16:33:05
    Author     : oneplayer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <style>
            body {
                padding-bottom: 0!important;
            }
        </style>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <!-- Bootstrap -->
        <!--<script src="//code.jquery.com/jquery.js"></script>-->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/wind.css" rel="stylesheet" media="screen">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <!--DatePicker-->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.css">
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.ui.datepicker.validation.min.js"></script>
        <!--Date validation-->
        <script src="js/date.validation.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                function toporleft(context, source) {
                    var position = $(source).position();
                    if (position.left > 100) {
                        return "left";
                    }


                    return "top";
                }
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
                        dataType: 'json',
                        data: {
                            'command': 'validation',
                            'login': login,
                            'email': email,
                            'pass': pass,
                            'confpass': confpass
                        },
                        success: function(data) {
                            if (data.login) {
                                $('#login').parent().tooltip('destroy').tooltip({container: 'body', title: data.login, placement: toporleft}).tooltip('show').parent().removeClass('has-success').addClass('has-error')
                            } else {
                                $('#login').parent().tooltip('destroy').parent().removeClass('has-error').addClass('has-success')
                            }
                            if (data.pass) {
                                $('#pass').parent().tooltip('destroy').tooltip({container: 'body', title: data.pass, placement: toporleft}).tooltip('show').parent().removeClass('has-success').addClass('has-error')
                                $('#confpass').parent().removeClass('has-success').addClass('has-error');
                            } else {
                                $('#pass').parent().tooltip('destroy').parent().removeClass('has-error').addClass('has-success')
                                $('#confpass').parent().removeClass('has-error').addClass('has-success');
                            }
                            if (data.email) {
                                $('#email').parent().tooltip('destroy').tooltip({container: 'body', title: data.email, placement: toporleft}).tooltip('show').parent().removeClass('has-success').addClass('has-error')
                            } else {
                                $('#email').parent().tooltip('destroy').parent().removeClass('has-error').addClass('has-success')
                            }
                            if (data.registered) {
//                                if (window.parent.map) {
//                                    window.parent.register();
//                                } else {
                                window.location.href = 'j_security_check?j_username=' + email + '&j_password=' + pass;
//                                }
                            }
                        }
                    });
                    return false;
                });
                $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
                    try {
                        window.parent.$('iframe')[0].onload();
                    }
                    catch (e) {
                    }
                })
            });
        </script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
          <script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <jsp:include page="../generic/header.jsp" />
        <div class="container">
            <div class="row">
                <div class=" col-sm-4 col-sm-offset-4 paddingtop ">
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active"><a href="#signin" data-toggle="tab" class="btn btn-default">Log in</a></li>
                        <li class=""><a href="#registration" data-toggle="tab" class="">Register</a></li>
                    </ul>
                </div><div class="tab-content">
                    <div class="col-sm-12 tab-pane active" id="signin">
                        <br>
                        <form class="form-horizontal" role="form" action="j_security_check" method="POST">
                            <c:if test="${param.error == 'true'}" >
                                <div class="form-group">
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-4">
                                        <div class="alert alert-danger alert-dismissable nomargin">
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                            <strong>${param.message}</strong> ${param.message2}</div>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Email</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="j_username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-4 control-label">Password</label>
                                <div class="col-sm-4">
                                    <input type="password" class="form-control" id="inputPassword3" name="j_password" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="submit" class="btn btn-primary">Sign in</button>
                                </div>
                            </div>
                        </form>       
                    </div>
                    <div class="col-sm-12 tab-pane" id="registration">

                        <br> 
                        <form class="form-horizontal" role="form" action="Controller">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control"  placeholder="Name" name="login"  id="login" pattern="[a-zA-Z]{1}[a-zA-Z0-9 ]{2,49}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-4 control-label">Email</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control"  placeholder="Email" name="email" id="email" pattern="^[_A-Za-z0-9-]{2,}(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]{1,}(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-4 control-label">Password</label>
                                <div class="col-sm-4">
                                    <input type="password" class="form-control"  placeholder="Password" name="pass" id="pass" pattern=".{6,50}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-4 control-label">Password (confirm)</label>
                                <div class="col-sm-4">
                                    <input type="password" class="form-control"  placeholder="Password (confirm)" name="confpass" id="confpass" pattern=".{6,50}">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-4">
                                    <button type="submit" name="command" value="validation" class="btn btn-primary" id="register">Sign up</button>
                                </div>
                            </div>
                        </form>    
                    </div>


                </div>
            </div>
        </div>
        <jsp:include page="../generic/footer.jsp" flush="true" />
    </body>
</html>
