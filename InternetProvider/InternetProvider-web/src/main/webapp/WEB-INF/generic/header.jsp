<%-- 
    Document   : header
    Created on : 29.04.2014, 18:10:58
    Author     : oneplayer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>${param.titleText}</title>
        <script type="text/javascript">
            if (window.parent.map) {
                window.parent.register();
            }
        </script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery.1.7.2.min.js"></script>
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>-->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>-->
        <script src="js/jquery-ui.min.js"></script>
        <!-- Bootstrap -->
        <!--<script src="//code.jquery.com/jquery.js"></script>-->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/wind.css" rel="stylesheet" media="screen">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <!--DatePicker-->
        <!--<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.css">-->
        <link rel="stylesheet" href="css/jquery-ui.css">
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.ui.datepicker.validation.min.js"></script>
        <!--Date validation-->
        <script src="js/date.validation.js"></script>
        <script src="js/wind.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
          <script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="${param.headerClass}">
        <div class="body-wrapper">
            <div class="navbar navbar-default navbar-static-top headercolor boxshadow" role="navigation">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3 header-item borderleft text-center nopadding">
                            <a href="index.jsp" class="display-block largefont28">
                                <span class="fa fa-home paddingtop">&nbsp;</span>
                                Wind
                            </a>
                        </div>
                        <div class="col-sm-3 header-item borderleft text-center nopadding">
                            <c:if test="${user == null}">  
                                <a href="profile" data-toggle="modal"  data-remote="false" data-target="#login-modal" class="display-block largefont28">
                                    <span class="fa fa-user paddingtop">&nbsp;</span>
                                    Sign In
                                </a>
                            </c:if>
                            <c:if test="${user != null}">  
                                <a href="profile" class="display-block largefont28">
                                    <span class="fa fa-user paddingtop">&nbsp;</span>
                                    Profile
                                </a>
                            </c:if>
                        </div>
                        <div class="col-sm-3 header-item borderleft text-center nopadding">
                            <a href="/help" class="display-block largefont28">
                                <span class="fa fa-question-circle paddingtop">&nbsp;</span>
                                Help
                            </a>
                        </div>
                        <div class="col-sm-3 header-item borderleft borderright text-center nopadding menu-phone-item header-item whitetext">
                            <div class="display-block largefont28">
                                <span class="fa fa-phone-square paddingtop hidden-sm">&nbsp;</span><div class="paddingtop" style="line-height: 32px; display: inline-block;">044 388 02 02</div></div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${user == null}">  

                <!-- Modal -->
                <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Authentication window</h4>
                            </div>
                            <div class="modal-body">
                                <iframe id="login-frame" data-src="profile" style="width:100%" onload="autoResize(this)"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
