El problema de querer añadir subida de imagenes a la hora de crear películas es que en SpringBoot la mejor manera de hacerlo es con
un formulario multi parte, es decir: enviar por un lado un json con los datos textuales de la pelicula y por otro lado los archivos.

[ESTO NO ES NECESARIO SI QUEREMOS CREAR PELICULAS SIN NECESIDAD DE SUBIR ARCHIVOS]

Por tanto, para evitar errores y unificar las pruebas desde Postman se han creado estos archivos.

- createMovie.json -> Añadir este archivo JSON para añadir una nueva pelicula
- updateMovie.json -> Añadir este para modificar la pelicula creada con el JSON anterior

Para probar la subida de archivos, se adjuntan dos imagenes de ejemplo que pueden usarse como el poster y la captura necesaria