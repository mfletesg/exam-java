# Examen en Java
Este proyecto se desarrolló en Java 21 usando el Framework Spring Boot 3 con mysql. El lado visual se desarrollo con JavaScript en react.

<div style="text-align: center;">
    <img src="./img/java-logo.svg" alt="java-logo" width="200" height="100">
    <img src="./img/Spring-Boot.svg" alt="spring-logo" width="90" height="90">
</div>

## Requerimientos
<div style="text-align: center;">


<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white" alt="MySQL">
<img src="https://img.shields.io/badge/Spring%20Boot%203-6DB33F?style=flat-square&logo=spring&logoColor=white" alt="Spring Boot 3">
</div>


## Estructura del proyecto
El proyecto utiliza MySQL como soporte para la base de datos. Las tablas se crean automaticamente cuado se ejecuta el servicio ya que el ORM de spring las crea

## Instrucciones

1. Clonar el proyecto desde Git

    ```bash
    git clone https://github.com/mfletesg/exam-java.git
    ```

2. Instalar dependencias

    ```bash
    cd tu-proyecto
    ./mvnw clean install
    ```

3. Configurar MySQL

    - Crear una base de datos llamada `tu_base_de_datos`
    - Agregar un usuario con permisos de acceso a la base de datos
    - Actualizar las propiedades de conexión en el archivo `application.properties`

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

4. Ejecutar el proyecto

    ```bash
    ./mvnw spring-boot:run
    ```

5. Acceder a la aplicación

    Abre un navegador y accede a [http://localhost:8080](http://localhost:8080)

## Notas

- Asegúrate de tener Java 21 o superior instalado en tu sistema.
- Asegúrate de tener MySQL instalado y configurado correctamente.
- Puedes personalizar las propiedades de conexión a la base de datos en el archivo `application.properties`