<%@ page import="bitapp.Questionnaire" %>



<div class="fieldcontain ${hasErrors(bean: questionnaireInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="questionnaire.title.label" default="Title"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" maxlength="250" required="" value="${questionnaireInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionnaireInstance, field: 'questions', 'error')} ">
    <label for="questions">
        <g:message code="questionnaire.questions.label" default="Questions"/>

    </label>
    <select id="questionList" name="questionList" required="required" multiple>
        <g:render template="multiselect_question"
                  model="${[questions: questions]}"/>
    </select>

    %{--<ul class="one-to-many">
        <g:each in="${questionnaireInstance?.questions ?}" var="q">
            <li><g:link controller="question" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="question" action="create"
                    params="['questionnaire.id': questionnaireInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'question.label', default: 'Question')])}</g:link>
        </li>
    </ul>--}%

</div>

