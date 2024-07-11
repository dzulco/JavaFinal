console.log("Entro a GetRequestUsuarios");
document.addEventListener("DOMContentLoaded", function() {

  const usuarioSection = document.getElementById("usuarios");
  const usuarios = [];

  function loadUsuarioList() {
    console.log("Entro a loadUsuariosList");
    fetch("/pelis24115/usuarios")
      .then(response => response.json())
      .then(data => {
        console.log("data : "+data);
        data.forEach(usuario =>{
          usuarios.push(usuario);
          usuarioSection.innerHTML += `
            <tr>
              <td>${usuario.nombre}</td>
              <td>${usuario.apellido}</td>
              <td>${usuario.email}</td>
              <td>*****************</td>
              <td>${usuario.fechaNacimiento}</td>
              <td>${usuario.pais}</td>
            </tr>
          `
        });
      });
  }
  loadUsuarioList();
});