<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="bit-app-layout.css"/>
    <asset:javascript src="common.js"/>

    <script type="application/javascript">
        $(document).ready(function () {
            $('[data-toggle="offcanvas"]').click(function () {
                $('.row-offcanvas').toggleClass('active')
            });
        });
    </script>

    <style type="text/css">

    </style>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Bit App</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/app/dashboard">Home</a></li>
                %{--<li><a href="#about">About</a></li>--}%
                %{--<li><a href="#contact">Contact</a></li>--}%
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container-fluid">
    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9 col-sm-push-2">
            <button type="button" class="pull-left btn btn-default visible-xs" data-toggle="offcanvas" aria-expanded="false" aria-controls="navbar">
                <i class="fa fa-navicon"></i>
            </button>

            <g:layoutBody/>

        </div><!--/.col-xs-12.col-sm-9-->

    <g:render template="/layouts/left_menu"/>
    </div><!--/row-->

    <hr>

    <footer>
        <p>&copy; 2017 Bit App, Inc.</p>
    </footer>
    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

</div><!--/.container-->

<asset:javascript src="bit-app.js"/>
<script type="application/javascript">
    //bitAppTest("Bottom of dashboard_layout 2");
</script>
</body>
</html>
