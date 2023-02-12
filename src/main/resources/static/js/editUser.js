// Call the dataTables jQuery plugin
$(document).ready(function() {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const param = urlParams.get('param')
    console.log(param);

    loadUsers(param);

    let emailsArray = [];

    $("#idUser").val(param);
    $("#idUser").hide();
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

       addEmails();

    });

    function addEmails(){

        $('#email').empty();

        let emails = "";

        for(email in emailsArray ){
            let valueEmail = emailsArray[email];

            if( valueEmail !== null && valueEmail !== undefined && valueEmail !== "" ){
                $('#email').append('<li width="100%" class = "liEmail" id ="'+valueEmail+'" value ="'+valueEmail+'">&nbsp&nbsp&nbsp'+valueEmail+'&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary" style="font-size:8px;">Eliminar</button></li>');

                emails = emails + ", " + valueEmail;

            }
        }

        if( emails.substring(0,1) == ","){

            emails = emails.substring(1);
        }
        console.log("Emails limpios:"+emails);
        $('#newEmail').val("");
        $('#emails').val(emails);

        console.log("Nuevos emails: "+$('#emails').val());


        $('.liEmail').click(function(){
            let valueEmail = $(this).attr("value");
            deleteEmail(valueEmail);
        });
    }

    function deleteEmail(valueEmail){

        console.log("Eliminando: "+valueEmail);

        const filteredEmailsArray = emailsArray.filter((item) => item !== valueEmail);
        emailsArray = filteredEmailsArray;

        $('#email').empty();
        $("#emails").val("");

        for(email in emailsArray ){
            let valueEmail = emailsArray[email];

            if( valueEmail !== null && valueEmail !== undefined && valueEmail !== "" ){
                $('#email').append('<li width="100%" class = "liEmail" id ="'+valueEmail+'" value ="'+valueEmail+'">&nbsp&nbsp&nbsp'+valueEmail+'&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary" style="font-size:8px;">Eliminar</button></li>');
            }
        }

        $('.liEmail').click(function(){
            let valueEmail = $(this).attr("value");
            deleteEmail(valueEmail);
        });

    }

     async function loadUsers(param){

        const response = await fetch("api/getUser?id="+param,{
            method : 'GET',
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            }
        });

        const responseJson = await response.json();
        console.log(responseJson);

        if( responseJson.succed == true && responseJson.code == 400 && responseJson.entity != null ){

            $("#txtName").val(responseJson.entity.name);
            $("#gender").val(responseJson.entity.gender);
            $("#statusSelector").val(responseJson.entity.status);
            let image = document.getElementById("imageProfile");

           if( responseJson.entity.base64Image == null ||
               responseJson.entity.base64Image == undefined ||
               responseJson.entity.base64Image == ""){
               image.src = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
            } else {
                 image.src = "data:image/png;base64," + responseJson.entity.base64Image;
            }

            image.style.width = '300px';
            image.style.height = 'auto';

            let emailsFound = responseJson.entity.email;
            let arrayEmailsFound = emailsFound.split(", ");

            if( arrayEmailsFound == null || arrayEmailsFound.length == 0){
                arrayEmailsFound.push(emailsFound);
            }
            const filteredEmailsArray = arrayEmailsFound.filter((item) => item !== "");
            emailsArray = filteredEmailsArray;

            addEmails();
        }

    }

});
