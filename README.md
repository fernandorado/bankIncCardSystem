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

### Flujo de Ejecución de la Aplicación

El flujo de ejecución de la aplicación sigue los siguientes pasos:

1. **Generar Número de Tarjeta:** Se genera un número de tarjeta a partir del identificador de producto y se guarda en la base de datos.
   
2. **Activar Tarjeta:** Se activa la tarjeta, que previamente se había generado.

3. **Bloquear Tarjeta:** Opcionalmente, se puede bloquear la tarjeta si se desea.

4. **Recargar Saldo:** Se recarga el saldo de la tarjeta.

5. **Consulta de Saldo:** Se consulta el saldo actual de la tarjeta.

6. **Transacción de Compra:** Se realiza una transacción de compra utilizando la tarjeta.

### Descripción del Código

#### `CardService`

Este servicio se encarga de generar números de tarjeta, activar tarjetas, bloquear tarjetas y recargar saldo.

#### `TransactionService`

Este servicio se encarga de realizar transacciones de compra, consultar transacciones y cancelar transacciones.

### Pruebas Unitarias

Las pruebas unitarias se encuentran en el paquete `com.example.BankincCardSystem.service`. Puede consultar los archivos de prueba para ver cómo se realizaron las pruebas y asegurarse de que el código funcione según lo esperado.

## Colección de Postman

Se proporciona una colección de Postman llamada `BankInc.postman_collection.json`, que contiene una serie de solicitudes predefinidas para probar la solución. Importe esta colección en su cliente de Postman para comenzar a realizar solicitudes a la API.


##Con estos pasos, podrá integrar y utilizar fácilmente el API de Bank Inc en sus aplicaciones, asegurando un proceso fluido y sin problemas de integración.
