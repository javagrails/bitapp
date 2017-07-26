
<%@ page import="bitapp.Questionnaire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'questionnaire.label', default: 'Questionnaire')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-questionnaire" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-questionnaire" class="content scaffold-show" role="main">
			<h1 class="h1center"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list questionnaire">
			
				<g:if test="${questionnaireInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="questionnaire.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${questionnaireInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionnaireInstance?.questions}">
				<li class="fieldcontain">
					<span id="questions-label" class="property-label"><g:message code="questionnaire.questions.label" default="Questions" /></span>
					
						<g:each in="${questionnaireInstance.questions}" var="q">
						<span class="property-value" aria-labelledby="questions-label"><g:link controller="question" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${questionnaireInstance?.id}" />
					<g:link class="edit" action="edit" id="${questionnaireInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<sec:access url="/questionnaire/delete">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</sec:access>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
