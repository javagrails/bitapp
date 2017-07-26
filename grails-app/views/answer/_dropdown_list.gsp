<option value="">-Select Any-</option>
<g:each var="data" in="${list}">
    <option value="${data}" <g:if test="${selectedItem?.equals(data)}"> selected="selected" </g:if> >${data?.toLowerCase()?.capitalize()}</option>
</g:each>