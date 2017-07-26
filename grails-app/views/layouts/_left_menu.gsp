<div class="col-xs-6 col-sm-2 col-sm-pull-9 sidebar-offcanvas" id="sidebar">
    <div class="list-group">
        <a href="#" class="list-group-item active" style="background-color: #101010;">Dashboard of (${session?.username})</a>

        <g:each var="req" in="${session?.reqMaps}">
            <a href="${req?.url}" class="list-group-item" style="background-color: mintcream;">${req?.featureName}</a>
        </g:each>

        <a href="/logout" class="list-group-item active" style="background-color: #101010;">Logout</a>
    </div>
</div><!--/.sidebar-offcanvas-->