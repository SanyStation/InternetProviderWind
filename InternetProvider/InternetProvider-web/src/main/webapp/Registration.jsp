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
            $(document).ready(function(){
                $("#register").click(function(){
                    //data normalization
                    var login = $(#login).val();
                    var pass = $(#pass).val();
                    var confpass = $(#confpass).val();
                    var e-mail = $(#e-mail).val();
                    $.trim(login);
                    $.trim(pass);
                    $.trim(confpass]);
                    $.trim(e-mail); 
                    //validation
                    $.ajax({
                        type: 'POST',
                        url: 'Controller',
                        dataType : "text", 
                        data: {
                            'command': validation,
                            'login': login,
                            'e-mail': e-mail,
                            'pass': pass,
                            'confpass': confpass                            
                        },
                        success: function (data) {  
                            if (data=="")
                                $.ajax({
                                    type: 'POST',
                                    url: 'Controller',
                                    dataType : "text", 
                                    data: {
                                        'command': registration,
                                        'login': login,
                                        'e-mail': e-mail,
                                        'pass': pass,
                                    });
                            else 
                            $("#msgfield").append("data");
                        }
                    });
                }); 
                });
        
        
        
        
        
                    
                }); 
            });    
        </script>
                <div id="div1">
                    <b>
                        Registration panel:
                    </b>
                    <br /><br />
                    <div id="msgfield">
                        Please, input your registration information
                    </div>
                    <br />
                        Login:
                    <br />
                        <input type="text" name="login" id="login" size="40" />
                    <br />
                        Password:
                    <br />
                        <input type="password" name="pass" id="pass" size="40">
                    <br />
                        Confirm Password:
                    <br />
                        <input type="password" name="confpass" id="confpass" size="40">
                    <br />
                        E-mail:
                    <br />
                        <input type="text" name="e-mail" id="email" size="40">
                    <br />
                        <input type="button" id="register" value="Register new user">
                </div>
        
        
        
        
    </body>
</html>
