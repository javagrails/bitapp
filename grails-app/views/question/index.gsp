
<%@ page import="bitapp.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-question" class="content scaffold-list" role="main">
			<h1 class="h1center"><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-striped" rows="${questionInstanceTotal}">
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'question.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'question.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="options" title="${message(code: 'question.options.label', default: 'Options')}" />
					
						<th><g:message code="question.answer.label" default="Answer" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${questionInstanceList}" status="i" var="questionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${questionInstance.id}">${fieldValue(bean: questionInstance, field: "title")}</g:link></td>
					
						<td>${questionInstance?.type?.toLowerCase()?.capitalize()}</td>
					
						<td>${fieldValue(bean: questionInstance, field: "options")}</td>
					
						<td>${fieldValue(bean: questionInstance, field: "answer")}</td>
					

					</tr>
				</g:each>
				</tbody>
			</table>
			<g:if test="${questionInstanceTotal > 9}">
				<div class="pagination">
					<g:paginate total="${questionInstanceTotal}" />
				</div>
			</g:if>

		</div>
	</body>
</html>
