
<%@ page import="bitapp.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard_layout">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav mb" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/app/dashboard')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-question" class="content scaffold-show" role="main">
			<h1 class="h1center"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list question">
			
				<g:if test="${questionInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="question.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${questionInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="question.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label">${questionInstance?.type?.toLowerCase()?.capitalize()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.options}">
				<li class="fieldcontain">
					<span id="options-label" class="property-label"><g:message code="question.options.label" default="Options" /></span>
					
						<span class="property-value" aria-labelledby="options-label"><g:fieldValue bean="${questionInstance}" field="options"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.answer}">
				<li class="fieldcontain">
					<span id="answer-label" class="property-label"><g:message code="question.answer.label" default="Answer" /></span>
					
						<span class="property-value" aria-labelledby="answer-label"><g:link controller="answer" action="show" id="${questionInstance?.answer?.id}">${questionInstance?.answer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.nextId}">
				<li class="fieldcontain">
					<span id="nextId-label" class="property-label"><g:message code="question.nextId.label" default="Next Id" /></span>
					
						<span class="property-value" aria-labelledby="nextId-label"><g:fieldValue bean="${questionInstance}" field="nextId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.onCondition}">
				<li class="fieldcontain">
					<span id="onCondition-label" class="property-label"><g:message code="question.onCondition.label" default="On Condition" /></span>
					
						<span class="property-value" aria-labelledby="onCondition-label"><g:fieldValue bean="${questionInstance}" field="onCondition"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.previousId}">
				<li class="fieldcontain">
					<span id="previousId-label" class="property-label"><g:message code="question.previousId.label" default="Previous Id" /></span>
					
						<span class="property-value" aria-labelledby="previousId-label"><g:fieldValue bean="${questionInstance}" field="previousId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.questionnaire}">
				<li class="fieldcontain">
					<span id="questionnaire-label" class="property-label"><g:message code="question.questionnaire.label" default="Questionnaire" /></span>
					
						<span class="property-value" aria-labelledby="questionnaire-label"><g:link controller="questionnaire" action="show" id="${questionInstance?.questionnaire?.id}">${questionInstance?.questionnaire?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${questionInstance?.id}" />
					<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<sec:access url="/question/delete">
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</sec:access>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
