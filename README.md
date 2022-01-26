# Amplify
Aplicación web dedicada a la reproducción de música

## Descripción general
Amplify sirve, como muchas de las diferentes aplicaciones web del estilo, para reproducir y disfrutar de la música; aunque con algunas características adicionales, siendo las principales, basarse en contenido libre de derechos de autor (copyright) y tener las diferentes canciones clasificadas según su uso: Streaming, relajación, podcast, deporte...

## Funcionalidades públicas
- Buscar una canción, artista o albúm en concreto
- Reproducir cualquier canción
- Controlar la reproducción
- Ver las valoraciones de las diferentes pistas
- Crear o iniciar sesión en una cuenta

## Funcionalidades privadas
- Crear listas de reproducción personalizadas
- Valorar las diferentes canciones
- Guardar canciones, álbumes, artistas y playlists favoritos
- Modificar la cuenta de usuario

## Entidades y relaciones
- Usuario: recoge el perfil de un usuario, dejando registrado los artistas, álbumes y canciones favoritos. Tiene asociado las reviews y playlists que ha realizado
- Canción: archivo de audio junto a información identificativa. Tiene un conjunto de reviews, y sabe cual es su álbum.
- Artista: recoge los álbumes que ha publicado un artista.
- Album: engloba todas las canciones de un álbum y conoce al artista.
- Playlist: conjunto determinado de canciones que puede haber creado un usuario.
- Review: aporta una opinión sobre una canción por parte de un usuario concreto.

## Servicio interno
El servicio de uso exclusivo interno es capaz de __permitir descargas__ dentro de la aplicación (canciones, playlists enteras...) además de realizar otros servicios básicos como el login de los diferentes usuarios.
  
## Trello
> https://trello.com/b/UwQOWvWy/amplify-dad
