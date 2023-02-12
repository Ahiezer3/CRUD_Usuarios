// Call the dataTables jQuery plugin
$(document).ready(function() {

    let emailsArray = [];

    $("#emails").hide();

    $('#btnAddEmail').click(function(){

         let newEmail = $('#newEmail').val();

         var validEmail =  /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

         if( !validEmail.test(newEmail) ){
            alert('El email es inválido.');
            return false;
         }

        if( newEmail !== null && newEmail !== undefined && newEmail !== "" ){
            emailsArray.push(newEmail);
        }
        console.log(emailsArray);

        $('#email').empty();

        let emails = "";

        for(email in emailsArray ){
            let valueEmail = emailsArray[email];

            if( valueEmail !== null && valueEmail !== undefined && valueEmail !== "" ){
                $('#email').append('<li width="100%" class = "liEmail" id ="'+valueEmail+'" value ="'+valueEmail+'">'+valueEmail+'&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary" style="font-size:8px;">Eliminar</button></li>');

                emails = emails + ", " + valueEmail;

            }
        }

        if( emails.substring(0,1) == ","){

            emails = emails.substring(1);
        }

        $('#newEmail').val("");
        $('#emails').val(emails);

        console.log("Nuevos emails: "+$('#emails').val());


        $('.liEmail').click(function(){
            let valueEmail = $(this).attr("value");
            deleteEmail(valueEmail);
        });

    });

    function deleteEmail(valueEmail){

        console.log("Eliminando: "+valueEmail);

        const filteredEmailsArray = emailsArray.filter((item) => item !== valueEmail);
        emailsArray = filteredEmailsArray;

        $('#email').empty();
        $("#emails").val("");

        for(email in emailsArray ){
            let valueEmail = emailsArray[email];

            if( valueEmail !== null && valueEmail !== undefined && valueEmail !== "" ){
                $('#email').append('<li width="100%" class = "liEmail" id ="'+valueEmail+'" value ="'+valueEmail+'">'+valueEmail+'&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary" style="font-size:8px;">Eliminar</button></li>');
            }
        }

        $('.liEmail').click(function(){
            let valueEmail = $(this).attr("value");
            deleteEmail(valueEmail);
        });
    }

});
