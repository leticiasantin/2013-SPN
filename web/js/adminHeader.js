$(document).ready(function() {

    $("#welcome").click(function() {
        $(window.document.location).attr('href', "adminWelcome.jsp");
    });
    $("#category").click(function() {
          $(window.document.location).attr('href', "Controller?c=doCategoryCrud&m=list");
    });
    $("#logout").click(function() {
        $("#c").val('doLog');
        $("#m").val('logout');
        $("#formHeader").submit();
    });
    $("#delUsers").click(function() {
        $(window.document.location).attr('href', "Controller?c=doUserSCRUD&m=deletedUsers");
    });

});

