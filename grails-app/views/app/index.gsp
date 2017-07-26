<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Bit App</title>
</head>
<body>


<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome to Bit App (${count})</h1>

        <div style="background-color: #269abc; padding-left: 500px;">
            <g:each in="${list?.sort()}" var="data">
                <span style="color: whitesmoke; font-weight: bold;">${data}</span><br/>
            </g:each>
        </div>
    </section>
</div>

</body>
</html>
