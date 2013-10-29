$(document).ready(function() {

    $("#welcome").click(function() {
         $("#c").val('setUserView');
        $("#m").val('welcome');
        $("#formHeader").submit();
       
    });
    $("#editProfile").click(function() {
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
        $("#c").val('doServiceEvaluation');
        $("#m").val('list');
        $("#formHeader").submit();
    });
    $("#divulgationPage").click(function() {
        $("#c").val('doLoadDivulgationPage');
        $("#formHeader").submit();
    });
    $("#completed").click(function() {
        $("#c").val('doServiceCrud');
        $("#m").val('completedServicesList');
        $("#formHeader").submit();
    });
});