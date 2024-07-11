// cuando el dom se cargue
document.addEventListener('DOMContentLoaded', async () => {
    // tengo que levantar los datos del formulario, validar los inputs, en caso de algun value vacio, mostrar un mensaje de error
    // y no hacer la peticion fetch
    // si los datos son correctos, realizo la peticion fetch post a la api para agregar una pelicula
    // si la respuesta es correcta, muestro un mensaje de exito y limpio los inputs del formulario
    
    //obtengo el formulario
    formNuevaPelicula = document.getElementById('formulario');

    const directorSelect = document.getElementById('director');

    // Llenar el select con los directores
    try {
        const response = await fetch('/pelis24115/directores');
        const directores = await response.json();
        directores.forEach(director => {
            const option = document.createElement('option');
            option.value = director.id;
            option.textContent = `${director.nombre} ${director.apellido}`;
            directorSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al cargar los directores:', error);
    }


    //agrego el evento submit al formulario
    formNuevaPelicula.addEventListener('submit', async (event) => {
        //prevengo el comportamiento por defecto del formulario
        event.preventDefault();
        //obtengo los datos del formulario
        const formData = new FormData(formNuevaPelicula);
        //obtengo los valores de los inputs
        const nombre = formData.get('nombre');
        const descripcion = formData.get('descripcion');
        const calificacion = formData.get('calificacion');
        const anio = formData.get('anio');
        const director = formData.get('director');
        const estrellas = formData.get('estrellas');
        const genero = formData.get('genero');
        //valido los inputs
        if (nombre === '' || genero === '' || descripcion === '' || anio === ''|| director === ''|| estrellas === ''|| calificacion === '') {
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
                "descripcion": descripcion,
                "genero": genero,
                "calificacion": calificacion,
                "anio": anio,
                "estrellas": estrellas,
                "director": director
                }),
          };
          
        const url = '/pelis24115/movies';

        const response = await fetch(url, options);
        //obtengo la respuesta
        const data = await response.json(); 
        //si la respuesta es correcta, muestro un mensaje de exito y limpio los inputs del formulario
        // si el codigo es 201, la pelicula se agrego correctamente
        if (response.status === 201) {
            alert('Pelicula agregada correctamente');
            formulario.reset();
            // que se recargue la pagina para ver la pelicula agregada
            window.location.href= '../index.html';
        } else {
            alert('Error al agregar la pelicula');
        }
       
    });

});