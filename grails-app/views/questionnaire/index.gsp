
<%@ page import="bitapp.Questionnaire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'questionnaire.label', default: 'Questionnaire')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-questionnaire" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-questionnaire" class="content scaffold-list" role="main">
			<h1 class="h1center"><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-striped" rows="${questionnaireInstanceTotal}">
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'questionnaire.title.label', default: 'Title')}" />
						<g:sortableColumn property="questions" title="${message(code: 'questionnaire.questions.label', default: 'Number of Questions')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${questionnaireInstanceList}" status="i" var="questionnaireInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${questionnaireInstance.id}">${fieldValue(bean: questionnaireInstance, field: "title")}</g:link></td>
						<td>${questionnaireInstance?.questions?.size()}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<g:if test="${questionnaireInstanceTotal > 9}">
				<div class="pagination">
					<g:paginate total="${questionnaireInstanceTotal}" />
				</div>
			</g:if>

		</div>
	</body>
</html>
