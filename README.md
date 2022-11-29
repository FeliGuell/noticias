## SITIO DE NOTICIAS EGG NEWS

El objetivo de este ejercicio consiste en el desarrollo de un sistema web en Java utilizando una
base de datos MySQL, JPA Repository para persistir objetos y Spring Boot como framework de
desarrollo web.

## Entidades

1. Entidad Noticia
* La entidad Noticia modela las noticias que se publicarán en la web. En esta entidad, el atributo
“título” contiene el nombre con el cuál vamos a encontrar la noticia, mientras que el atributo
“cuerpo” contiene toda la información que queremos que el usuario pueda leer.
* la entidad Noticia se va a relacionar con la entidad periodista


2. Entidad Usuario
* La entidad Usuario modela los usuarios que se registran y loguean en la web.
* Un Usuario debe loguearse para ver las noticias, y perderá la posibilidad de ingresar a la vista
panelAdmin.
* Las entidades Periodista y Administrador deben extender de Usuario.


3. Entidad Periodista
* Un Periodista debe loguearse en el sistema para poder acceder a crear y modificar Noticias. Esta
acción deberá realizarse desde la vista panelAdmin.


4. Entidad Administrador
* Podrá crear, modificar y eliminar noticias
* Dar de alta o baja a Periodistas (modificar el atributo activo).
* Indicar cuál va a ser el sueldoMensual de cada Periodista.
Un Administrador debe loguearse en el sistema para poder acceder a sus funcionalidades.


## Capa de Vistas
Esta capa tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
interactuar con el usuario. Las vistas para este proyecto tienen que estar desarrolladas en HTML5
y se debe utilizar la biblioteca Thymeleaf y CSS para implementar las plantillas. Además, se debe
utilizar el framework de Bootstrap para los componentes.
Se deben diseñar y crear todas las vistas web necesarias para llevar a cabo las siguientes
funcionalidades:
* Vista inicio: en esta vista deben estar las tarjetas(bootstrap) con el título de cada noticia,
ordenadas de más reciente a más antigua.
* Vista noticia: en esta vista tendremos el acceso a la noticia completa (cuerpo). Es la vista
que se abre cuando hacemos click en alguna tarjeta de la vista inicio.
* Vista panelAdmin: en esta vista es donde gestionaremos las noticias. Aquí encontraremos
los formularios necesarios para crear, modificar o eliminar una Noticia.


## Tecnologías 
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* MySQL
* Thymeleaf












