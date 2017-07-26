<!doctype html>
<html>
<head>
    <meta name="layout" content="dashboard_layout"/>
    <title>Welcome to Bit App</title>
</head>
<body>


<div id="content" role="main">
    <section class="row colset-2-its">
        <h1 style="text-align: center; border-bottom: 1px solid #5e5e5e;;font-size: 35px; color: #2e6da4;">Welcome to Bit App</h1>

        <div style="padding-left: 5px; border-bottom: 1px solid #5e5e5e;">
            You are logged in as ( <b>${userType} </b>) type user, having below authorities.
            <br/>
            <br/>
            <g:each status="i"  in="${authorities?.sort()}" var="authoritiy">
                <span style="color: #5e5e5e; font-weight: bold;">${i+1}. ${authoritiy}</span><br/>
            </g:each>
        </div>
    </section>
</div>

</body>
</html>
