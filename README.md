
# Bazar API

Sistema de gestión de ventas para un bazar. Permite administrar clientes, productos, ventas y generar reportes a través de una API RESTful desarrollada con Spring Boot.

## Documentación de la API

La API cuenta con documentación interactiva generada automáticamente mediante **Swagger** (OpenAPI 3.0). Esto permite visualizar de manera clara y ordenada todos los endpoints disponibles, los modelos de datos (DTOs), parámetros, códigos de respuesta y ejemplos de uso.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Postman
- Swagger/OpenAPI 3.0
  
## Instrucciones para ejecutar la aplicación

### 1. Clonar el repositorio

```bash
git clone https://github.com/SeguOostdijk/bazar-api.git
cd bazar-api
```

### 2. Crear una base de datos MySQL

```sql
CREATE DATABASE bazar_db;
```

### 3. Configurar credenciales

Editá el archivo `src/main/resources/application.properties` con los datos de tu entorno:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 4. Compilar y ejecutar la aplicación

Desde consola o tu IDE:

```bash
mvn clean install
mvn spring-boot:run
```

La aplicación quedará corriendo en: [http://localhost:8080](http://localhost:8080)


## Probar la API

Tenés dos opciones para probar los endpoints de la API:

#### - Swagger UI

La aplicación expone automáticamente la documentación interactiva en Swagger. Una vez que la aplicación esté corriendo, podés acceder a:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Desde ahí podés visualizar todos los endpoints disponibles, probar las peticiones (GET, POST, PUT, DELETE), y ver los modelos de datos utilizados.

#### - Postman

También podés usar Postman para probar los endpoints. Para eso:

1. Abrí Postman.
2. Importá la colección incluida en el proyecto (`/postman/bazar.postman_collection.json`).
3. Asegurate de que el entorno de la API esté apuntando a:  
   `http://localhost:8080`
4. Ejecutá las peticiones disponibles y observá las respuestas.

## Autor

**Segundo Oostdijk**  
Desarrollador Backend  
Proyecto final - Curso de Desarrollo de Apis con Spring Boot - TodoCode
