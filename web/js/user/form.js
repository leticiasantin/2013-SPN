
$(document).ready(function() {

    $("#dtOfBirth").datepicker({dateFormat: 'dd/mm/yy', maxDate: '-18Y',
        yearRange: '-80:+10', changeYear: true, changeMonth: true});

    $("#state").change(function() {
        var state = $("#state").val();
        loadCities(state);
    });
    $("#submit").click(function() {
        $("#password").val(CryptoJS.MD5($("#password").val()));
    });

    $("#login").focusout(function() {
        $.ajax({
            type: "POST",
            url: "Controller",
            data: {
                c: "doUserSCRUD",
                m: "loginExist",
                login: $("#login").val()
            }
        }).success(function(data) { 
        }).error(function() {
            alert('Login já existe por favor escolhar outro');
        });
    });

});


function loadCities(state) {
    var cities;
    switch (state) {
        case "PR":
            cities = new Array('', 'Abatiá', 'Londrina');
            break;
        case "SC":
            cities = new Array('', 'Florianopólis', 'Tubarão');
            break;
        default :
            break;
    }
    clearOption("city");
    insertSelected(state, cities);
}



function insertSelected(state, cities)
{
    var x = document.getElementById("city"); //retorna o objeto select
    var options = x.getElementsByTagName("option"); //pega a lista de options do select


    for (var i = 0; i < cities.length; i++) {
        var y = document.createElement('option'); //cria um novo elemento option
        y.text = cities[i]; //seta o texto do elemento option
        y.value = cities[i]; //seta o valor do elemento option
        try
        {

            x.add(y, null); // adiciona um novo option no fim da lista para navegadores complacentes
        }
        catch (ex)
        {
            x.add(y); // adiciona um novo option no fim da lista para o IE
        }
    }
}
