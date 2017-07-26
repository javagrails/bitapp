


function bitAppTest(where) {
    alert("bitAppTest Called from "+where);
}

function getQuestionType(elementId) {
    var select = document.getElementById(elementId);
    var qTypeOptionValue = "";
    for (var i = 0; i < select.length; i++) {
        if (select[i].selected) {
            qTypeOptionValue = select[i].getAttribute('qtype');
        }
    }
    console.log('getQuestionType(): qTypeOptionValue: [' + qTypeOptionValue + ']  questionId: [' + select.value + ']');

    return qTypeOptionValue;
}

function questionAutoSetAnswerType() {
    var id = $('#question').val();
    var qType = getQuestionType('question');

    //alert("questionAutoSetAnswerType = "+id+" qType = "+qType);

    $("#questionType").val("");
    $("#questionType").val(qType);
}

function setOnOptions(valueIn) {
    $('#noOptions').val("");
    $('#noOptions').val(valueIn);
}

function onChangeQuestionType() {
    var type = $('#type').val();
    //alert("type = "+type);

    if(type == ""){
        $("#optionListDiv").hide();
        setOnOptions("OK");
    } else if(type == "NARRATIVE"){
        $("#optionListDiv").hide();
        setOnOptions("YES");
    } else {
        // SELECTIVE
        $("#optionListDiv").show();
        setOnOptions("NO");
    }
}