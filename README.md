# Amplify
Aplicación web dedicada a la reproducción de música

## Descripción general
Amplify sirve, como muchas de las diferentes aplicaciones web del estilo, para disfrutar de la música; aunque con algunas características adicionales, siendo las principales, basarse en contenido libre de derechos de autor (copyright) y tener las diferentes canciones clasificadas según su uso: Streaming, relajación, podcast, deporte...

## Video-explicación del funcionamiento de la aplicación
https://www.youtube.com/watch?v=hp9Pj-uQg_4

## Funcionalidades públicas
- Buscar una canción, artista o albúm en concreto
- Descargar cualquier canción
- Crear o iniciar sesión en una cuenta

## Funcionalidades privadas
- Crear listas de reproducción personalizadas
- Guardar canciones, álbumes, artistas y playlists favoritos
- Recibir estadísticas de un usuario y enviar recomendaciones (Administrador)

## Entidades y relaciones
- Usuario: recoge el perfil de un usuario, dejando registrado los artistas, álbumes y canciones favoritos. Tiene asociado las reviews y playlists que ha realizado
- Canción: archivo de audio junto a información identificativa. Tiene un conjunto de reviews, y sabe cual es su álbum.
- Artista: recoge los álbumes que ha publicado un artista.
- Album: engloba todas las canciones de un álbum y conoce al artista.
- Playlist: conjunto determinado de canciones que puede haber creado un usuario.
- Songfile: se utiliza de forma conjunta a "Canción" para agilizar el manejo de los archivos de audio.

## Servicio interno
- Enviar correos de forma asíncrona (Tanto entregar a administradores de la aplicación datos de usuarios, como mandarles recomendaciones a los usuarios directamente).
- Recibir peticiones de descargas de archivos para poder buscar estas canciones en la base de datos y poder permitir descargas.
  
## Trello
> https://trello.com/b/dPt5uWWU/amplify

---
      
# Desarrollo (Fase 2)
## Capturas de pantalla 
### Página principal
En primer lugar, tenemos una página de bienvenida que muestra un texto explicando el uso de la página web así como canciones de muestra y capacidad para poder navegar por el resto de páginas (Inicio de sesión y registro, creación de nuevas playlists, acceso a elementos guardados y otro botón adicional que nos permite volver a esta página de inicio cuando nos encontremos en otra página.

![1](https://user-images.githubusercontent.com/56352638/155112327-7e1e3217-50aa-41ba-a04c-83599aad1cb8.png)

### Login/Registro
A continuación tenemos una página sencilla de registro/login que sirve para poder logearnos/registrarnos en el entorno. Podemos retornar a la página principal si hemos decidido no realizar acciones. Nuestro usuario logueado se verá reflejado en el botón de acceso a esta página de login/registro mientras permanezca en su sesión y su función será la de redirigir a la biblioteca del propio usuario.

![2](https://user-images.githubusercontent.com/56352638/155112833-7d3fee78-4416-4489-a38d-0e6516229b49.png)
![3](https://user-images.githubusercontent.com/56352638/155113001-0910d236-dad1-4fda-8f83-087dcbced3c9.png)

### Biblioteca
En la biblioteca se muestra toda la colección de favoritos del usuario dado: artistas, playlists, canciones y álbumes favoritos quedan guardados y clasificados (mediante los botones de acceso correspondientes) en su biblioteca personal del usuario.

![image](https://user-images.githubusercontent.com/56352638/155113339-f22cce47-22a0-4ac4-b729-e0903ad23566.png)

![image](https://user-images.githubusercontent.com/56352638/155113726-78ccd7d9-f427-492a-8525-c01d8f6ab00e.png)

![image](https://user-images.githubusercontent.com/56352638/155113864-2faabdd9-eb52-4f03-be60-9f82e2233eac.png)

### Nueva playlist
Los diferentes usuarios tienen la capacidad de, además de añadir contenido a su biblioteca/favoritos, crear sus propias playlists. Para ello se proporciona una página sencilla donde creamos una playlist dotándola de un nombre y, de forma implícita, un creador.

![4](https://user-images.githubusercontent.com/56352638/155114403-a36c78ad-e6e5-4850-83d3-f9bf1f41a8e1.png)

![image](https://user-images.githubusercontent.com/56352638/155114383-e1b816c4-924e-4ceb-a99a-200e0c306e90.png)

### Playlist
Template para mostrar el contenido de una playlist.

![image](https://user-images.githubusercontent.com/56352638/155116072-89efed42-577b-4e32-ac5f-5fc1ab1b5f54.png)

### Álbum
Template para mostrar el contenido de un álbum.

![image](https://user-images.githubusercontent.com/56352638/155116220-7e9bc9d0-1c70-4908-af40-904ca525ed39.png)

### Artista
Template para mostrar el contenido de un artista (álbumes y canciones).

![image](https://user-images.githubusercontent.com/56352638/155115644-144dcc49-a0f0-4f5d-8715-3b2f963d3bd1.png)

### Error
Se ha personalizado el mensaje de error para que responda a un template personalizado.

![image](https://user-images.githubusercontent.com/56352638/155114680-4a7ecf3a-edbd-4b06-a504-a92a24b94f99.png)

## Navegación
La navegación, aunque de forma esquemática puede ser algo confusa, es muy intuitiva y la página es bastante usable; al fin y al cabo el usuario tiene la capacidad (en la mayoría de las pantallas) de unos botones de navegación que permiten ir a cualquier otra página.

![image](https://user-images.githubusercontent.com/56352638/155116888-d873a57b-69da-4f83-8cb2-9b97fc4967b6.png)

![image](https://user-images.githubusercontent.com/56352638/155119670-2d2f7895-e6b0-48b2-b93a-a7eecea85c2a.png)

## Diagrama E/R (Base de datos)
![image](https://user-images.githubusercontent.com/56352638/155110484-4ee35071-48a6-4116-afa5-0c1eeb679f0e.png)

## Diagrama de clases UML
![estructura_general](https://user-images.githubusercontent.com/56352638/155112509-16e41668-6151-4713-9716-b3b49a66e0e5.png)

![java](https://user-images.githubusercontent.com/56352638/155110781-5cf71c0d-3f85-43ae-9102-5903e2918566.png)

# Desarrollo (Fase 3)
## Navegación (Nuevos elementos)
Se han incluido diversos elementos (templates nuevas y elementos de navegación) para poder visitar de forma correcta los diferentes espacios públicos y privados de la aplicación web:
### Página principal
La página principal no ha cambiado en demasía así como las páginas creadas en la anterior fase de desarrollo; simplemente se han actualizado los campos pertinentes (por ejemplo la adición de un botón de descarga para poder descargar las diferentes canciones mediante el servicio interno).

![image](https://user-images.githubusercontent.com/56352638/162077330-9b1660ad-bf94-4249-88cf-d0c1a58c0ad3.png)

### Login y registro
Se han añadido y actualizado diferentes páginas asociadas al login y el registro (errores de login/registro y actualización de estos templates).

![image](https://user-images.githubusercontent.com/56352638/162077508-f80a9d49-bb9b-4f58-8a28-2c86f1ee2190.png)

![image](https://user-images.githubusercontent.com/56352638/162077661-4fb6a454-b3fb-4fff-a651-368a0c8d7390.png)

![image](https://user-images.githubusercontent.com/56352638/162078114-9dc8d623-7b7c-487d-8fb7-3e55e8d9a8dc.png)

### Servicio interno
De forma similar, se ha añadido un espacio para usuarios "ADMIN" en el cual es posible utilizar el servicio interno de correo para recibir estadísticas o enviar recomendaciones.

![image](https://user-images.githubusercontent.com/56352638/162078364-a1bfbc62-850c-4f2f-abd1-e14d430611e7.png)

## Diagrama de clases
Destacamos la adición de clases para controlar la seguridad y el propio servicio interno así como pequeñas adiciones en la estructura general de controladores para  manejar diferentes tipos de errores.

![image](https://user-images.githubusercontent.com/56352638/162081770-c536ff36-0eed-40b6-9ec4-34447494fdef.png)

![image](https://user-images.githubusercontent.com/56352638/162081659-a3c8701f-9222-4d37-94c9-5e40740c801c.png)

### Diagrama completo (Aplicación y servicio interno)

![java](https://user-images.githubusercontent.com/56352638/162081999-5ab91129-5f57-41cf-8240-d4122f5b8ffa.png)

![java2](https://user-images.githubusercontent.com/56352638/162083112-cddb3c5d-7649-44c3-8149-02656fe0a3df.png)

## Despliegue de aplicación

### MySQL
1. Sudo apt-get install mysql-server
2. Mysql -u root -p cacapoop

SQL
  - Create database amplifydb;
  - Use database amplifydb;
  - Create user ‘root’@’localhost’ identified by ‘cacapoop’;
  - Grant all privileges on amplify.* to ‘root’@’localhost’;
  - Flush privileges;
  - quit;
  
### Java
1. Sudo apt-get install java default-jre
2. Java -version # Solo para comprobar
  
### Maven
En la carpeta del proyecto, por cada sub-proyecto:
- Mvn dependency:resolve
- Mvn compile
- Maven package

### Aplicación
En la carpeta de proyecto:
- Ir al proyecto del servicio / target
- Java -jar “Aplicacion.jar”
- Ir al proyecto de la aplicación / target en otro cmd(o ejecutar en segundo plano a partir de aquí con &)
- Java -jar “Servicio.jar”

# Desarrollo (Fase 4)
## Despliegue de la aplicación en Docker
