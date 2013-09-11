$(document).ready(function() {

    $("#welcome").click(function() {
        $(window.document.location).attr('href', "welcome.jsp");
    });
    $("#profile").click(function() {
        $("#c").val('doLoadProfile');
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
    
    
    

});


