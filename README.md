HibernateAS
===========

Práctica AS con Hibernate

## Requisitos
JDK 1.6+

Eclipse

## Hibernate
Descargad lo siguiente

1. Hibernate: `http://sourceforge.net/projects/hibernate/files/hibernate3/3.6.10.Final/`

2. Anotaciones: `http://sourceforge.net/projects/hibernate/files/hibernate-annotations/3.4.0.GA/`

## Servidor de BBDD
Lo primero es instalar un servidor de BBDD relacional, SQL en este caso. Hay un montón de alternativas pero Apache tiene el proyecto Derby que es un servidor super lightweight que nos va de lujo para montarlo rápido.

1. Descargar la última versión (1.9.1.0):
`http://db.apache.org/derby/derby_downloads.html`

2. Arrancad el servidor ejecutando `startNetworkServer` dentro de la carpeta `bin` donde hayáis descomprimido el zip.

## Conexión a la BBDD desde Eclipse
En la raíz del repo encontraréis un fichero que se llama `DerbyConnectionProfile`.

Para importarlo intentad lo siguiente. Desde Eclipse:
1. Window -> Open perspective -> Other... Seleccionad *Database development*

2. Os aparecerá el panel de "Data Source Explorer"

3. Importar la configuración con el botón de importar (es una flecha que entra en una caja)

4. Comprobad que funciona haciendo un Ping desde el menú contextual del recurso recien creado.

## Configurar Hibernate en el proyecto
No se hasta que punto con la configuración que trae lo que os he subido tendréis algo, pero como mínimo los paths a las librerías estarán mal seguro, con lo que tendréis que seguir los pasos descritos en este video:

`http://www.youtube.com/watch?v=4oaVSnfiTE0`




