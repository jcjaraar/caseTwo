// Call the dataTables jQuery plugin
$(document).ready(function() {
    //alert("por cargar usuarios");
    cargarUsuarios();
    //alert("despues de cargar usuarios");
  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});

async function cargarUsuarios(){
    //alert("en CARGAR USUARIOS");
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
    });
    const usuarios = await request.json();

    let listadoHTML='';
    for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onClick="eliminarUsuario('+usuario.id+')" class="btn btn-dangerbtn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let telefono = usuario.telefono == null ? '-' : usuario.telefono;
        let usuarioHTML='<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+telefono+'</td><td>'+usuario.email+'</td><td>'+botonEliminar+'</td></tr>';
        listadoHTML += usuarioHTML;
    }
    //console.log(usuarios);
    document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
}

async function eliminarUsuario(id){
    if(!confirm("Desea eliminar el usuario "+id)){
        return;
    }
    const request = await fetch('api/usuarios/'+id, {
            method: 'DELETE',
            headers: getHeaders()
        });
    location.reload();
}

function getHeaders(){
return { 'Accept': 'application/json',
         'Content-Type': 'application/json',
         'Authorization': localStorage.token
        };
}

function actualizarEmailDelUsuario(){
document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}