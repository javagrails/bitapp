<%@ page import="bitapp.Tools; bitapp.Answer" %>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="answer.questionType.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<select id="question" name="question.id" required="" onchange="questionAutoSetAnswerType()">
		<g:render template="dropdown_list_question"
				  model="${[list: freeQuestions, selectedItem: answerInstance?.question?.id]}"/>
	</select>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'questionType', 'error')} required">
	<label for="questionType">
		<g:message code="answer.questionType.label" default="Answer of question type" />
		<span class="required-indicator">*</span>
	</label>
	<select id="questionType" name="questionType">
		<g:render template="dropdown_list" model="${[list: bitapp.Tools.QUESTION_TYPES, selectedItem:answerInstance?.questionType]}"/>
	</select>
</div>

<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'answer', 'error')} required">
	<label for="answer">
		<g:message code="answer.answer.label" default="Answer" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="answer" required="" value="${answerInstance?.answer}"/>
</div>





