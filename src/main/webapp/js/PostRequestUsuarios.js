// cuando el dom se cargue
document.addEventListener('DOMContentLoaded', async () => {
    // tengo que levantar los datos del formulario, validar los inputs, en caso de algun value vacio, mostrar un mensaje de error
    // y no hacer la peticion fetch
    // si los datos son correctos, realizo la peticion fetch post a la api para agregar una pelicula
    // si la respuesta es correcta, muestro un mensaje de exito y limpio los inputs del formulario
    
    //obtengo el formulario
    formNuevoUsuario = document.getElementById('formulario');

    


    //agrego el evento submit al formulario
    formNuevoUsuario.addEventListener('submit', async (event) => {
        //prevengo el comportamiento por defecto del formulario
        event.preventDefault();
        //obtengo los datos del formulario
        const formData = new FormData(formNuevoUsuario);
        //obtengo los valores de los inputs
        const nombre = formData.get('nombre');
        const apellido = formData.get('apellido');
        const email = formData.get('email');
        const password = formData.get('password');
        const fechaNacimiento = formData.get('fechaNacimiento');
        const pais = formData.get('pais');
        //valido los inputs
        if (nombre === '' || apellido === '' || email === '' || password === ''|| fechaNacimiento === ''|| pais === '') {
            alert('Todos los campos son obligatorios');
            return;
        }
      
        const options = {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "nombre": nombre,
                "apellido": apellido,
                "email":email,
                "fechaNacimiento": fechaNacimiento,
                "contrasenia": password,
                "pais": pais
                }),
          };
          
        const url = '/pelis24115/usuarios';

        const response = await fetch(url, options);
        
        const data = await response.json(); 
        
        if (response.status === 201 || response.status === 200) {
            alert('Usuario agregado correctamente');
            formulario.reset();
            window.location.href= './listarUsuarios.html';
        } else {
            alert('Error al agregar usuario');
        }
       
    });

});