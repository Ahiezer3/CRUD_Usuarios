// Call the dataTables jQuery plugin
$(document).ready(function() {
  /*$('#usersTable').DataTable();*/
  loadUsers(10,0);

    $('#btnRegisterUser').click(function(){
          window.location.href="register.html";
    });

    $('#usersTable_next').click(function(){
              let currentPage = Number($('#usersTable_currentPage').text());
              currentPage = currentPage+1;
              console.log("Página siguiente: "+currentPage);

              let finalPage = Number($('#usersTable_finallPageVal').text());
              if( currentPage >= 0 && currentPage <= finalPage ){
                 loadUsers(10,currentPage);
              }
      });

      $('#usersTable_previous').click(function(){
              let currentPage = Number($('#usersTable_currentPage').text());
              currentPage = currentPage-1;
              console.log("Página anterior: "+currentPage);

              let finalPage = Number($('#usersTable_finallPageVal').text());
              if( currentPage >= 0 && currentPage <= finalPage ){
                 loadUsers(10,currentPage);
              }
      });

      $('#usersTable_currentPageText').click(function(){
                let currentPage = Number($('#usersTable_currentPage').text());

                console.log("Página actual: "+currentPage+", refrescando");

                let finalPage = Number($('#usersTable_finallPageVal').text());
                if( currentPage >= 0 && currentPage <= finalPage ){
                   loadUsers(10,currentPage);
                }
      });

      $('#usersTable_currentPage').click(function(){
               let currentPage = Number($('#usersTable_currentPage').text());

               console.log("Página actual: "+currentPage+", refrescando");

               loadUsers(10,currentPage);
     });

     $('#usersTable_refresh').click(function(){
                let currentPage = Number($('#usersTable_currentPage').text());

                console.log("Página actual: "+currentPage+", refrescando");

                let finalPage = Number($('#usersTable_finallPageVal').text());
                if( currentPage >= 0 && currentPage <= finalPage ){
                   loadUsers(10,currentPage);
                }
      });

      $('#usersTable_finallPageVal').click(function(){
              let currentPage = Number($('#usersTable_finallPageVal').text());

              console.log("Página final: "+currentPage+"");

              let finalPage = Number($('#usersTable_finallPageVal').text());
              if( currentPage >= 0 && currentPage <= finalPage ){
                 loadUsers(10,currentPage);
              }
      });

 async function loadUsers(pageSize,pageNo){

    /*document.getElementById("spanEmail").outerHTML = localStorage.email;*/
    const response = await fetch("api/getAllUsers"+"?pageSize="+pageSize+"&pageNo="+pageNo,{
        method : 'GET',
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
            /*'Authorization' : localStorage.token*/
        }
    });

    const responseJson = await response.json();
    const numberPage = responseJson.pageNo;
    let totalPages = Number(responseJson.totalPages);
    totalPages = totalPages-1;
    let newRows = '';

    if( responseJson.succed == true && responseJson.code == 300 ){

        let entities = responseJson.entities;

        for(let user of entities){

            let deleteButton = '<a href="#" id="'+user.id+'" class ="delete" title = "Eliminar usuario">'+
                                             '<i class="fas fa-trash"></i>'+
                                           '</a>';
            let showDetailsButton = ' <a href="#" id="'+user.id+'" class ="search" title = "Editar usuario">'+
                                                       '<i class="fas fa-search"></i>'+
                                                     '</a>';
            let imageSrc = "";

            if( user.base64Image == null ||
                user.base64Image == undefined ||
                user.base64Image == ""){
                imageSrc = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
            } else {
                 imageSrc = "data:image/png;base64," + user.base64Image;
            }

            let newRow = '<tr>'+
                              '<td><img class="centerImage" id = "imageProfile" src="'+imageSrc+'"alt="Imagen de usuario" title="Imagen usuario"></td>'+
                              '<td>'+user.id+'</td>'+
                              '<td>'+user.name+'</td>'+
                              '<td>'+user.email+'</td>'+
                              '<td>'+user.gender+'</td>'+
                              '<td>'+user.status+'</td>'+
                              '<td>'+deleteButton+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+showDetailsButton+'</td>'+
                          '</tr>';

                          newRows += newRow;
        }

    } else {
        newRows = '<tr><td colspan = "6">'+responseJson.message+'</td></tr>';
    }

    document.querySelector("#usersTable tbody").outerHTML = newRows;
    $("#usersTable_currentPage").html('<a href="#" aria-controls="usersTable" data-dt-idx="1" tabindex="0" class="page-link"> '+numberPage+' </a>');
    $("#usersTable_finallPageVal").html('<a href="#" aria-controls="usersTable" data-dt-idx="1" tabindex="0" class="page-link"> '+totalPages+' </a>');

    $('.delete').click(function(){
        let id = $(this).attr('id');
        deleteUser(id);
        console.log("Eliminar id: "+id);
    });

    $('.search').click(function(){
        let id = $(this).attr('id');

        console.log("Editar id: "+id);

        window.location.href = "edit.html?param="+id;
    });


  };

  function deleteUser(id){

       if( !confirm("¿Desea eliminar este id usuario: '"+id+"'?")){
           return;
       }

      const response = fetch("api/deleteUser?id="+id,{
           method : 'DELETE',
           headers : {
               'Accept' : 'application/json',
               'Content-Type' : 'application/json'
               /*'Authorization' : localStorage.token*/
           }
       });

       location.reload();
 }
});
