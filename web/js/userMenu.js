$(document).ready(function() {

    $("#welcome").click(function() {
        $(window.document.location).attr('href', "welcome.jsp");
    });
    $("#profile").click(function() {
        $("#c").val('setUserView');
        $("#m").val('edit');
        $("#formHeader").submit();
    });
    $("#logout").click(function() {
        $("#c").val('doLog');
        $("#m").val('logout');
        $("#formHeader").submit();
    });
    $("#category").click(function() {
        $("#c").val('doCategoryCrud');
        $("#m").val('listUserCategories');
        $("#formHeader").submit();
    });
    $("#solicitation").click(function() {
        $("#c").val('doServiceCrud');
        $("#m").val('solicitationPendeciesList');
        $("#formHeader").submit();
    });
    $("#evaluation").click(function() {
        alert("hello");
        $("#c").val('doServiceEvaluation');
        $("#m").val('list');
        $("#formHeader").submit();
    });
    $("#divulgationPage").click(function() {
        $("#c").val('doLoadDivulgationPage');
        $("#formHeader").submit();
    });
    
    
    

});


