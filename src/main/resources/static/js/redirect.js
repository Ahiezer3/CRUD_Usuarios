$(document).ready(function() {

    $("#reloadText").html("Se redireccionará a la página de usuarios en: 3 segundos.");

    let seconds = 2;
    setInterval(function(){

        $("#reloadText").html("Se redireccionará a la página de usuarios en: "+seconds+" segundos.");


        if( seconds == 0){
            window.location.href = "users.html";
        }

        seconds = seconds-1;
    },2000);

});