// Call the dataTables jQuery plugin
$(document).ready(function() {
  //on ready
});

async function registrarUsuario(){
    //alert("en REGISTRAR USUARIO");
    let datos={};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    let rePassword = document.getElementById("txtRepassword").value;

    if(datos.password != rePassword){
        alert("los valores de contraseñas no coinciden");
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert('La cuenta fue creada con exito!')
    window.location.href = 'login.html';
}
