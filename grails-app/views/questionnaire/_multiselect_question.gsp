<g:each var="question" in="${questions}">
    <option qytpe="${question?.type}" value="${question?.id}" <g:if test="${question?.isSelected}"> selected="selected" </g:if> >${question?.title}</option>
</g:each>