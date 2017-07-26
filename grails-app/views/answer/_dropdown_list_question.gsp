<option value="">-Select Any-</option>
<g:each var="question" in="${list}">
    <option qtype="${question?.type}" value="${question?.id}" <g:if test="${selectedItem?.equals(question?.id)}"> selected="selected" </g:if> >${question?.title}</option>
</g:each>