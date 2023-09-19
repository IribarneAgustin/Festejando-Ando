FESTEJANDO ANDO - Sistema de Alquiler de Mobiliario Infantil
FESTEJANDO ANDO es un proyecto de sistema para alquilar mobiliario infantil que permite a los usuarios explorar y solicitar reservas de una amplia variedad de artículos organizados por temáticas. Este sistema también incluye características como sugerencias de artículos para alquilar según las temáticas configuradas por el administrador y un carrito de compras para facilitar la gestión de las reservas. Además, el sistema envía notificaciones por correo electrónico tanto al cliente como al administrador cuando se realiza una reserva.

Características Funcionales
Inicio de Sesión/Cierre de Sesión como Administrador: Los administradores pueden iniciar sesión en el sistema y cerrar sesión cuando lo deseen.

Panel de Administración: El sistema incluye un panel de administración que permite a los administradores gestionar clientes, temáticas, artículos y reservas de manera eficiente.

CRUD de Artículos: Los administradores pueden crear, leer, actualizar y eliminar (CRUD) artículos disponibles para alquilar.

CRUD de Temáticas: Los administradores pueden gestionar las temáticas que organizan los artículos, incluyendo su creación, lectura, actualización y eliminación.

CRUD de Reservas: Los administradores pueden realizar operaciones CRUD en las reservas, lo que facilita la gestión de las solicitudes de alquiler.

CRUD de Clientes: Los administradores pueden administrar la información de los clientes, incluyendo la creación, lectura, actualización y eliminación de registros.

Galería de Temáticas: Los usuarios pueden explorar una galería de temáticas para encontrar artículos relacionados con sus necesidades de alquiler.

Detalle de Temática: Cada temática muestra información detallada y artículos relacionados para una experiencia de navegación más completa.

Carrito de Compras: Los usuarios pueden agregar y gestionar artículos en su carrito de compras antes de confirmar su reserva.

Formulario de Reserva: Los usuarios pueden completar un formulario de reserva para solicitar la reserva de artículos específicos.

Captcha (Prevención de Spam): El sistema utiliza Captcha para evitar el envío automático de formularios y reducir el riesgo de spam.

Notificaciones por Correo Electrónico: Se envían notificaciones por correo electrónico tanto al cliente como al administrador cuando se realiza una reserva, solicitando confirmación.

Recursos No Funcionales
Backend API Rest Java Spring Boot: El sistema utiliza una API REST implementada en Java con Spring Boot para manejar las operaciones del servidor.

Frontend React JS: La interfaz de usuario se desarrolla utilizando React JS para proporcionar una experiencia de usuario fluida y receptiva.

Base de Datos MySQL: Se utiliza MySQL como sistema de gestión de bases de datos para almacenar y gestionar datos relacionados con clientes, temáticas, artículos y reservas.

Cómo Ejecutar el Proyecto
Para ejecutar el proyecto FESTEJANDO ANDO en su entorno local, siga estos pasos:

Clonar ambos repositorios desde GitHub, tanto el correspondiente al backend como al frontend

Frontend: https://github.com/IribarneAgustin/FestejandoAndo-FrontEnd
Backend: https://github.com/IribarneAgustin/Festejando-Ando

bash
Copy code
git clone https://github.com/tu_usuario/festejando-ando.git
Configurar la base de datos MySQL con la configuración adecuada y asegurarse de que esté en funcionamiento.

Configure las propiedades de la aplicación, como la URL de la base de datos y las credenciales, en el archivo de configuración. 
Asegúrese de crear su propio archivo .env para mapear las credenciales expuestas en el archivo application.properties del backend.

Instale las dependencias del proyecto tanto para el backend como para el frontend.

bash
Copy code
cd festejando-ando/backend
npm install

cd ../frontend
npm install
Inicie el servidor backend y el cliente frontend en dos terminales separadas.

bash
Copy code
# En la terminal 1 (backend)
cd festejando-ando/backend
Run Spring boot Application

# En la terminal 2 (frontend)
cd festejando-ando/frontend
npm start
Abra su navegador web y vaya a http://localhost:3000 para acceder a la aplicación.

¡Disfrute explorando y utilizando FESTEJANDO ANDO!

Contribuciones
Las contribuciones son bienvenidas. Si desea contribuir al proyecto, siga estos pasos:

Fork el repositorio en GitHub.

Cree una nueva rama para su función o corrección de errores.

Realice los cambios necesarios y asegúrese de seguir las mejores prácticas de codificación.

Envíe una solicitud de extracción (pull request) con una descripción detallada de los cambios que ha realizado.

Los colaboradores revisarán su solicitud y la fusionarán en el proyecto principal si es apropiado.

Problemas y Soporte
Si encuentra problemas o tiene preguntas sobre el proyecto, no dude en abrir un problema en GitHub o ponerse en contacto con el equipo de desarrollo.

¡Gracias por usar FESTEJANDO ANDO! Esperamos que este sistema de alquiler de mobiliario infantil sea útil para usted y su comunidad.
