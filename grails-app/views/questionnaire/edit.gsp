<%@ page import="bitapp.Questionnaire" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'questionnaire.label', default: 'Questionnaire')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-questionnaire" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation">
			<ul>
				<ul>
					<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
					<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</ul>
			</ul>
		</div>
		<div id="edit-questionnaire" class="content scaffold-edit" role="main">
			<h1 class="h1center"><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${questionnaireInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${questionnaireInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${questionnaireInstance?.id}" />
				<g:hiddenField name="version" value="${questionnaireInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<sec:access url="/questionnaire/delete">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</sec:access>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
