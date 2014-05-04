<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : header
    Created on : 29.04.2014, 18:10:58
    Author     : oneplayer
--%>

<html>
    <head>
        <title>${param.titleText}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="../css/wind.css" rel="stylesheet" media="screen">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>

        <script src="../js/wind.js"></script>
        <script src="../js/bootstrap-tab.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
          <script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <div class="navbar navbar-default navbar-static-top headercolor" role="navigation">
            <div class="container">
                <div class="row">

                    <div class="col-md-3 header-item borderleft text-center">
                        <a href="/">
                            <h2>Wind</h2>
                        </a>
                    </div>
                    <div class="col-md-3 header-item borderleft text-center">
                        <a href="/profile">
                            <h3><span class="glyphicon glyphicon-user pull-left">&nbsp;</span></h3>
                            <h3>Profile</h3>
                        </a>
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
