# Bank Inc API

Bienvenido al repositorio de Bank Inc API. Este API proporciona funcionalidades para administrar tarjetas y transacciones en Bank Inc.

## Manual de Integración

Este manual proporciona instrucciones detalladas sobre cómo integrar y utilizar el API de Bank Inc en sus aplicaciones. Antes de comenzar, asegúrese de seguir los pasos a continuación para configurar su entorno de desarrollo.

### Configuración del Entorno

1. **Base de Datos PostgreSQL:**
   Antes de iniciar la aplicación, asegúrese de crear una base de datos en PostgreSQL con la siguiente configuración:
   - **URL:** jdbc:postgresql://localhost:5432/bankInc_db
   - **Usuario:** postgres
   - **Contraseña:** admin
   - **Driver:** org.postgresql.Driver
   
2. **Swagger:**
   Puede acceder a la documentación de la API utilizando Swagger a través del siguiente enlace:
   [http://localhost:8080/doc/swagger-ui/index.html#/](http://localhost:8080/doc/swagger-ui/index.html#/)

### Ejecución de la Aplicación (Local)

Para ejecutar la aplicación localmente, siga estos pasos:

1. Clone el repositorio del código fuente desde el repositorio privado en GitHub.

2. Importe el proyecto en su IDE preferido (por ejemplo, IntelliJ IDEA, Eclipse).

3. Asegúrese de tener una instancia de PostgreSQL en ejecución y configurada correctamente.

4. Abra el archivo `application.properties` y actualice la configuración de la base de datos según sea necesario.

5. Inicie la aplicación ejecutando la clase principal `BankIncApplication`.

6. Una vez que la aplicación esté en ejecución, puede acceder a la documentación de la API utilizando Swagger o realizar solicitudes utilizando Postman.

### Colección de Postman

Se proporciona una colección de Postman llamada `BankInc.postman_collection.json`, que contiene una serie de solicitudes predefinidas para probar la solución. Importe esta colección en su cliente de Postman para comenzar a realizar solicitudes a la API.

### Documentación Adicional

La documentación adicional, que incluye el flujo de datos, el modelo de base de datos, los requisitos obtenidos y las buenas prácticas de desarrollo, se encuentra en el repositorio del código fuente. Consulte los archivos relevantes para obtener más detalles sobre la solución implementada.

Con estos pasos, podrá integrar y utilizar fácilmente el API de Bank Inc en sus aplicaciones, asegurando un proceso fluido y sin problemas de integración.
