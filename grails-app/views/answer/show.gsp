
<%@ page import="bitapp.Answer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-answer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation" >
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<sec:access url="/answer/index">
					<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				</sec:access>
				<sec:access url="/answer/create">
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:access>
			</ul>
		</div>
		<div id="show-answer" class="content scaffold-show" role="main">
			<h1 class="h1center"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list answer">
			
				<g:if test="${answerInstance?.answer}">
				<li class="fieldcontain">
					<span id="answer-label" class="property-label"><g:message code="answer.answer.label" default="Answer" /></span>
					
						<span class="property-value" aria-labelledby="answer-label"><g:fieldValue bean="${answerInstance}" field="answer"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${answerInstance?.questionType}">
				<li class="fieldcontain">
					<span id="questionType-label" class="property-label"><g:message code="answer.questionType.label" default="Question Type" /></span>
					
						<span class="property-value" aria-labelledby="questionType-label">${answerInstance?.questionType?.toLowerCase()?.capitalize()}</span>
					
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${answerInstance?.id}" />
					<sec:access url="/answer/edit">
						<g:link class="edit" action="edit" id="${answerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					</sec:access>
					<sec:access url="/answer/delete">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</sec:access>
					&nbsp;
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
