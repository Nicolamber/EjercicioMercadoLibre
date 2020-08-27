# Ejercicio Mercado Libre
Aplicación para ingresar a Mercado Libre, consiste en hacer una aplicacion usando las APIs publicas de la empresa y le permita al usuario poder buscar un producto, elegirlo y ver su detalle.

## Arquitectura

![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

- Para la aplicacion decidí usar la arquitectura MVVM (Model-View-ViewModel) que mejor maneja la separacion de responsabilidades dentro de la aplicacion, ya que permite separar la lógica de la UI del modelo de negocio.

- MVVM documentation: https://developer.android.com/jetpack/guide

- LiveData se usa para las vistas que es una clase que nos permite observar mientras somos conscientes del ciclo de vida de activities, fragments, servicios, etc. De esta manera con LiveData solo vamos a ir actualizando los observadores de los componentes de la aplicacion que esten en un ciclo de vida activo.

Para el manejo asíncrono de las peticiones a la API de Mercado Libre decidí usar coroutines de Kotlin que, al trabajar en forma asíncrona evitan que se bloquee la ui durante el procesamiento de las peticiones. Tiene como ventajas:
- Rapidez: Muchas coroutinas en un mismo proceso
- Optimiza la memoria
- Cancelacion: Si cancelas, esa accion se propaga por toda la estructura de coroutinas
- Integracion con Jetpack.

## Librerías usadas
- Glide: Usada para el procesamiento de las imagenes correspondientes a los productos, la elegi por que es la mas completa ya que puedo ir configurando la imagen a insertar acorde a lo que pasa (si no viene imagen pongo una, mientras carga otra y asi).

## Errores desde el lado del programador
Use Logs, en los metodos encargados de manejar las peticiones a la API.

![alt text](https://github.com/Nicolamber/EjercicioMercadoLibre/blob/master/capturas/LogsConsola.png)

## Capturas de la app
![alt text](https://github.com/Nicolamber/EjercicioMercadoLibre/blob/master/capturas/SplashScreenYPrimerIngreso.png)
La primera vez que el usuario ingresa vera un mensaje indicando que no ha realizado busquedas.

![alt text](https://github.com/Nicolamber/EjercicioMercadoLibre/blob/master/capturas/ProductoyDetalle.png)
Cuando hace la busqueda verá una lista de productos que contiene el nombre, el precio y la imagen si es que esta existe.

![alt text](https://github.com/Nicolamber/EjercicioMercadoLibre/blob/master/capturas/appHorizontal.png)
Aplicacion horizontal.

![alt text](https://github.com/Nicolamber/EjercicioMercadoLibre/blob/master/capturas/ErrorHandlerInternet.png)
Manejo de errores, en este caso me centré en la conexion a internet ya que siempre que hacemos una busqueda, algo retorna.

