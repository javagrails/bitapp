<%@ page import="bitapp.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="question.title.label" default="Title"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" maxlength="250" required="" value="${questionInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'type', 'error')} required">
    <label for="type">
        <g:message code="question.type.label" default="Question type"/>
        <span class="required-indicator">*</span>
    </label>
    <select id="type" name="type" onchange="onChangeQuestionType()" required="required">
        <g:render template="dropdown_list"
                  model="${[list: bitapp.Tools.QUESTION_TYPES, selectedItem: questionInstance?.type]}"/>
    </select>
    %{--<g:select name="type" from="${questionInstance.constraints.type.inList}" required="" value="${questionInstance?.type}" valueMessagePrefix="question.type"/>--}%
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'options', 'error')} required" id="optionListDiv">
    <label for="options">
        <g:message code="question.options.label" default="Options"/>
        <span class="required-indicator">*</span>
    </label>

    <div style="margin-left: 215px;" id="optionsHidden">
        <g:each in="${1..4}" var="index">
            <g:if test="${questionInstance?.id != null && questionInstance?.options?.split(",")?.size()>1}">
                ${index}. <g:textField id="options_${index}" name="options" required=""
                                       value="${questionInstance?.options?.split(",")[index - 1]}"/><br/><br/>
            </g:if>
            <g:else>
                ${index}. <g:textField id="options_${index}" name="options" required="" value=""/><br/><br/>
            </g:else>
        </g:each>
    </div>
</div>

<input name="noOptions" id="noOptions" type="hidden" value="OK"/>

<script type="text/javascript" language="JavaScript">
    var typeInObject = '${questionInstance?.type}';
    //alert('typeInObject = '+typeInObject);
    if(typeInObject == "SELECTIVE"){
        $("#optionListDiv").show();
        $('#noOptions').val("");
        $('#noOptions').val("NO");
    } else {
        $("#optionListDiv").hide();
        $('#noOptions').val("");
        $('#noOptions').val("YES");
    }

</script>

%{--<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'answer', 'error')} required">
    <label for="answer">
        <g:message code="question.answer.label" default="Answer"/>
        <span class="required-indicator">*</span>
    </label>
    <select id="answer" name="answer.id" required="">
        <g:render template="dropdown_list_answer"
                  model="${[list: bitapp.Answer.list(), selectedItem: questionInstance?.answer?.id]}"/>
    </select>
</div>--}%



