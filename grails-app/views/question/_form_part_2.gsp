
<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'previousId', 'error')} required">
	<label for="previousId">
		<g:message code="question.previousId.label" default="Previous question" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="previousId" type="number" value="${questionInstance.previousId}" required=""/>
</div>


<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'onCondition', 'error')} ">
	<label for="onCondition">
		<g:message code="question.onCondition.label" default="Condition for next question" />

	</label>
	<g:textField name="onCondition" value="${questionInstance?.onCondition}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'nextId', 'error')} required">
	<label for="nextId">
		<g:message code="question.nextId.label" default="Next question" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nextId" type="number" value="${questionInstance.nextId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'questionnaire', 'error')} required">
	<label for="questionnaire">
		<g:message code="question.questionnaire.label" default="Questionnaire" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="questionnaire" name="questionnaire.id" from="${bitapp.Questionnaire.list()}" optionKey="id" required="" value="${questionInstance?.questionnaire?.id}" class="many-to-one"/>
</div>