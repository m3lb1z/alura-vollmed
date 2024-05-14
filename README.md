# VollMed API

VollMed API es una aplicación de Spring Boot que proporciona una API REST para la gestión de una clínica médica.

## Tecnologías utilizadas

- Java
- SQL
- Spring Boot
- Maven

## Configuración

La configuración de la aplicación se encuentra en los archivos `application.yml` y `application-test.yml` en el directorio `src/main/resources`.

### Configuración de Spring

El perfil activo y el nombre de la aplicación se configuran en `application.yml`:

```yaml
spring:
  profile.active: dev, test, prod
  application:
    name: alura-voll-clinica
  profiles:
    active: dev
```

### Configuración de la base de datos

La configuración de la base de datos para el entorno de prueba se encuentra en `application-test.yml`:

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3307}/vollmed_api_test?createDatabaseIfNotExist=true
    username: ${DB_MYSQL_USER:admin}
    password: ${DB_MYSQL_PASS:admin}
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

## Configuración de CORS

La configuración de CORS se encuentra en `CorsConfiguration.java` en el directorio `src/main/java/dev/emrx/med/api/web/config`. Esta configuración permite solicitudes desde `http://localhost:5500` y permite los métodos "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT".

## Ejecución

Para ejecutar la aplicación, ejecute la clase `VollMedApplication.java` en el directorio `src/main/java/dev/emrx/med/api`.

```java
@SpringBootApplication
public class VollMedApplication {

 public static void main(String[] args) {
  SpringApplication.run(VollMedApplication.class, args);
 }

}
```

## Pruebas

Las pruebas se pueden ejecutar utilizando el comando `mvn test`.

## Contribución

Las contribuciones son bienvenidas. Por favor, haga un fork del repositorio y cree una pull request para cualquier cambio que desee proponer.
