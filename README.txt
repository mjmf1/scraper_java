# Web Scraping con Selenium y Jsoup

Este proyecto es un raspador web que utiliza Selenium y Jsoup para extraer información de una página web y almacenarla en una base de datos MySQL. El proyecto ha sido desarrollado en Java y utiliza Maven para gestionar dependencias y construir el proyecto.

## Requisitos Previos

Antes de ejecutar este proyecto, asegúrate de que tienes lo siguiente instalado:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Mozilla Firefox](https://www.mozilla.org/firefox/)
- [Geckodriver](https://github.com/mozilla/geckodriver/releases) (Asegúrate de agregar el directorio de Geckodriver al sistema PATH)
- [MySQL](https://dev.mysql.com/downloads/mysql/) (y las credenciales necesarias)

## Configuración de la Base de Datos

Crea una base de datos MySQL y una tabla llamada `productos`. Puedes hacerlo utilizando una herramienta como phpMyAdmin o ejecutando las siguientes instrucciones SQL en una consola MySQL:

```sql
CREATE DATABASE datos;
USE datos;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    codigo VARCHAR(255) NOT NULL
);

Se deja archivo de la base de datos en la raiz del proyecto

## Configuración

1. Clona este repositorio a tu sistema local o descarga los archivos en un directorio.

2. Asegúrate de que la base de datos MySQL esté instalada y en ejecución. Puedes utilizar phpMyAdmin u otra herramienta de administración para crear una base de datos y una tabla para almacenar los datos de productos.

3. Abre el archivo (src/main/java/WebScraper.java) y modifica las siguientes líneas con tus propios valores:
 
   ```java
   String dbUrl = "jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos";
   String username = "tu_usuario";
   String password = "tu_contraseña";

			Ejecución

Para ejecutar la aplicación, sigue estos pasos:

Abre una terminal y navega al directorio raíz del proyecto.

Compila el proyecto utilizando Maven:

mvn clean install
mvn clean compile

Luego, ejecuta la aplicación con el siguiente comando para correr el programa:

mvn exec:java

La aplicación comenzará a recopilar datos de productos desde el sitio web especificado y los almacenará en la base de datos.


