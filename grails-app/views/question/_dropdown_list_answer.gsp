<option value="">-Select Any-</option>
<g:each var="answer" in="${list}">
    <option value="${answer?.id}" <g:if test="${selectedItem?.equals(answer?.id)}"> selected="selected" </g:if> >${answer?.answer}</option>
</g:each>