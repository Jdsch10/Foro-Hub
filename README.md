# ForoHub

## Descripción

ForoHub es una aplicación web que permite a los usuarios interactuar con un foro de discusión. Los usuarios pueden registrar, listar, detallar, actualizar y eliminar tópicos relacionados con cursos. La aplicación está protegida por JWT (JSON Web Tokens) para garantizar que solo los usuarios autenticados puedan interactuar con los recursos.

### Requisitos Previos
Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

Java 23.0.1: Se utiliza para compilar y ejecutar el proyecto.
Maven 4.0.0-rc-2: Se utiliza para la gestión de dependencias y construcción del proyecto.
MySQL 8+: Se utiliza para la base de datos.
Postman o cliente REST: Para probar la API.

### Instalación
1. Clonar el Repositorio
Clona el proyecto desde GitHub:

git clone https://github.com/tuusuario/foro-hub.git
cd foro-hub

2. Configurar MySQL
Crea una base de datos en MySQL llamada foro_hub.

CREATE DATABASE foro_hub;

Asegúrate de configurar las credenciales de tu base de datos en el archivo src/main/resources/application.properties:
properties

spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true

3. Dependencias
El proyecto usa Maven para manejar las dependencias. Si aún no has descargado las dependencias, puedes hacerlo ejecutando:

mvn clean install

### Ejecutar la Aplicación
Para iniciar la aplicación, ejecuta el siguiente comando:

mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080.

### Endpoints de la API
1. Autenticación (/auth/login)
Método: POST
Descripción: Permite a los usuarios autenticarse y obtener un token JWT.

Cuerpo de la solicitud:

{
  "username": "usuario",
  "password": "contraseña"
}

Respuesta:

{
  "token": "jwt_token"
}

2. Crear un Tópico (/topicos)
Método: POST
Descripción: Permite crear un nuevo tópico.
Autenticación: Requiere un token JWT en los encabezados.
Cuerpo de la solicitud:

{
  "titulo": "Introducción a Java",
  "mensaje": "Este es el mensaje del tópico",
  "autor": "Juan Pérez",
  "curso": "Programación Básica"
}

Encabezado:

Authorization: Bearer <jwt_token>

3. Listar Todos los Tópicos (/topicos)
Método: GET
Descripción: Obtiene todos los tópicos registrados en la base de datos.
Autenticación: Requiere un token JWT en los encabezados.

4. Detalle de un Tópico (/topicos/{id})
Método: GET
Descripción: Obtiene los detalles de un tópico específico.
Autenticación: Requiere un token JWT en los encabezados.
Parámetros: {id} - El ID del tópico.

5. Actualizar un Tópico (/topicos/{id})
Método: PUT
Descripción: Actualiza un tópico existente.
Autenticación: Requiere un token JWT en los encabezados.
Cuerpo de la solicitud:

{
  "titulo": "Java Avanzado",
  "mensaje": "Mensaje actualizado",
  "autor": "Juan Pérez",
  "curso": "Programación Avanzada"
}

6. Eliminar un Tópico (/topicos/{id})
Método: DELETE
Descripción: Elimina un tópico por su ID.
Autenticación: Requiere un token JWT en los encabezados.

7. Mostrar los Primeros 10 Tópicos Ordenados por Fecha de Creación
Método: GET
Descripción: Muestra los primeros 10 tópicos ordenados por fecha de creación, de forma ascendente.

### Seguridad y Control de Acceso
La aplicación utiliza Spring Security y JWT para proteger los endpoints. El flujo de trabajo es el siguiente:

Los usuarios inician sesión enviando sus credenciales en el endpoint /auth/login.
Si las credenciales son correctas, la API devuelve un token JWT.
El token JWT debe ser incluido en el encabezado Authorization de todas las solicitudes subsecuentes (por ejemplo, al crear o listar tópicos).
El token JWT es validado por el filtro JwtAuthenticationFilter, que se asegura de que el usuario esté autenticado antes de acceder a los recursos protegidos.

### Estructura del Proyecto
src/main/java: Contiene el código fuente de la aplicación.
com.foro.hub.controller: Controladores REST para manejar las solicitudes.
com.foro.hub.model: Entidades que representan los datos de la aplicación.
com.foro.hub.repository: Repositorios para la interacción con la base de datos.
com.foro.hub.service: Lógica de negocios y servicios, incluyendo la autenticación y generación de tokens JWT.
com.foro.hub.config: Configuración de seguridad y otros aspectos relacionados.
com.foro.hub.filter: Filtro para validar el token JWT.
src/main/resources:
application.properties: Configuración de la aplicación, como la base de datos y las credenciales de seguridad.

¡Gracias por usar ForoHub!