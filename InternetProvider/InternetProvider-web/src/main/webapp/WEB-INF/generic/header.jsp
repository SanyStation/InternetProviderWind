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
    <body>
        <div class="body-wrapper">
            <div class="navbar navbar-default navbar-static-top headercolor boxshadow" role="navigation">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 header-item borderleft text-center">
                            <a href="index.jsp">
                                <h2>Wind</h2>
                            </a>
                        </div>
                        <div class="col-md-3 header-item borderleft text-center">
                            <c:if test="${user == null}">  
                                <a href="profile" data-toggle="modal"  data-remote="false" data-target="#login-modal">
                                    <h3><span class="glyphicon glyphicon-user pull-left">&nbsp;</span></h3>
                                    <h3>Sign In</h3>
                                </a>
                            </c:if>
                            <c:if test="${user != null}">  
                                <a href="profile">
                                    <h3><span class="glyphicon glyphicon-user pull-left">&nbsp;</span></h3>
                                    <h3>Profile</h3>
                                </a>
                            </c:if>
                        </div>
                        <div class="col-md-3 header-item borderleft text-center">
                            <a href="/help">
                                <h3><span class="glyphicon glyphicon-question-sign pull-left">&nbsp;</span></h3>
                                <h3>Help</h3>
                            </a>
                        </div>
                        <div class="col-md-3 header-item borderleft borderright text-center">
                            <a href="/callmeback">
                                <h3><span class="glyphicon glyphicon-earphone pull-left">&nbsp;</span></h3>
                                <h3>Call me back</h3>
                            </a>
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
