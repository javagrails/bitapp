
<%@ page import="bitapp.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation" >
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-answer" class="content scaffold-list" role="main">
			<h1 class="h1center"><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-striped" rows="${answerInstanceTotal}">
				<thead>
					<tr>

						<g:sortableColumn property="question" title="${message(code: 'answer.question.label', default: 'Question')}" />

						<g:sortableColumn property="answer" title="${message(code: 'answer.answer.label', default: 'Answer')}" />
					
						<g:sortableColumn property="questionType" title="${message(code: 'answer.questionType.label', default: 'Question and Answer Type')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${answerInstanceList}" status="i" var="answerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${answerInstance?.question?.title}</td>

						<td><g:link action="show" id="${answerInstance.id}">${fieldValue(bean: answerInstance, field: "answer")}</g:link></td>
					
						<td>${answerInstance?.questionType?.toLowerCase()?.capitalize()}</td>

					</tr>
				</g:each>
				</tbody>
			</table>

			<g:if test="${answerInstanceTotal > 9}">
				<div class="pagination">
					<g:paginate total="${answerInstanceTotal}" />
				</div>
			</g:if>

		</div>
	</body>
</html>
