# 🍽️ AppRestaurante

Una aplicación de escritorio para la gestión integral de restaurantes, desarrollada en Java con interfaz gráfica Swing. Permite administrar mesas, platos, pedidos y personal del restaurante de manera eficiente.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Stack Tecnológico](#stack-tecnológico)
- [Instalación](#instalación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Uso](#uso)
- [Arquitectura](#arquitectura)
- [Componentes Principales](#componentes-principales)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

## ✨ Características

- **Autenticación de Usuarios**: Sistema de login seguro con cifrado de contraseñas (BCrypt)
- **Gestión de Mesas**: Crear, editar y eliminar mesas del restaurante
- **Catálogo de Platos**: Administrar el menú con información de platos
- **Gestión de Pedidos**: Crear y seguimiento de pedidos en tiempo real
- **Gestión de Personal**: Administración de meseros y usuarios del sistema
- **Interfaz Dual**: Vistas diferenciadas para administrador y mesero
- **Base de Datos MySQL**: Persistencia de datos segura y confiable
- **Interfaz Moderna**: Diseño amigable con FlatLaf theme

## 🔧 Requisitos Previos

- **Java**: JDK 23 o superior
- **MySQL**: Servidor MySQL 8.0 o superior
- **Maven**: 3.6.0 o superior
- **Base de Datos**: Configurada con las tablas requeridas

## 💻 Stack Tecnológico

- **Lenguaje**: Java 23
- **Framework UI**: Swing + FlatLaf 3.7
- **Persistencia**: MySQL Connector J 8.4.0
- **Seguridad**: JBCrypt 0.4
- **Build**: Maven
- **Diseño Gráfico**: Generador de formularios NetBeans

### Dependencias Principales

```xml
<!-- Base de Datos -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>

<!-- Seguridad -->
<dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
</dependency>

<!-- UI Moderna -->
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.7</version>
</dependency>
```

## 📦 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/JuanaCa19/AppRestaurante.git
cd AppRestaurante
```

### 2. Configurar la Base de Datos

- Crear una base de datos en MySQL
- Ejecutar el script de inicialización (si existe)
- Configurar credenciales en `connectionBD/Conexion.java`

### 3. Compilar el proyecto

```bash
mvn clean install
```

### 4. Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="com.mycompany.apprestaurante.AppRestaurante"
```

O ejecutar el archivo JAR generado:

```bash
java -jar target/AppRestaurante-1.0-SNAPSHOT.jar
```

## 📁 Estructura del Proyecto

```
src/main/java/com/mycompany/apprestaurante/
├── AppRestaurante.java              # Punto de entrada de la aplicación
│
├── Controlador/                     # Lógica de negocio y control
│   ├── dishController/              # Controlador de platos
│   ├── orderController/             # Controlador de pedidos
│   ├── orderDishController/         # Controlador de relación pedido-plato
│   ├── tableController/             # Controlador de mesas
│   ├── userController/              # Controlador de usuarios
│   └── waiterController/            # Controlador de meseros
│
├── Modelo/                          # Capa de datos y lógica de persistencia
│   ├── connectionBD/                # Conexión a la base de datos
│   ├── Seguridad/                   # Funciones de autenticación y cifrado
│   ├── dao/                         # Data Access Objects
│   │   ├── DishDAO.java             # Operaciones CRUD de platos
│   │   ├── OrderDAO.java            # Operaciones CRUD de pedidos
│   │   ├── OrderDishDAO.java        # Operaciones de relación pedido-plato
│   │   ├── TableDAO.java            # Operaciones CRUD de mesas
│   │   ├── UserDAO.java             # Operaciones CRUD de usuarios
│   │   └── WaiterDAO.java           # Operaciones CRUD de meseros
│   ├── dto/                         # Data Transfer Objects
│   ├── entities/                    # Entidades del dominio
│   │   ├── Dish.java                # Entidad Plato
│   │   ├── Order.java               # Entidad Pedido
│   │   ├── OrderDish.java           # Entidad Relación Pedido-Plato
│   │   ├── Table.java               # Entidad Mesa
│   │   ├── User.java                # Entidad Usuario
│   │   └── Waiter.java              # Entidad Mesero
│   └── interfaces/                  # Contratos de negocio
│
└── Vista/                           # Interfaz de usuario (Swing)
    ├── Login/                       # Pantallas de autenticación
    │   ├── login.java               # Formulario de inicio de sesión
    │   └── inicio.java              # Pantalla de inicio
    ├── viewAdmin/                   # Vista administrador
    │   ├── viewMainAdmin.java       # Pantalla principal admin
    │   ├── dishForm.java            # Formulario de platos
    │   ├── tableForm.java           # Formulario de mesas
    │   └── waiterForm.java          # Formulario de meseros
    └── viewWaiter/                  # Vista mesero
        └── viewMainWaiter.java      # Pantalla principal mesero
```

## 🚀 Uso

### Flujo de la Aplicación

1. **Inicio de Sesión**
   - Ejecuta la aplicación
   - Ingresa credenciales de usuario
   - Las contraseñas se validan contra la BD con cifrado BCrypt

2. **Panel de Administrador**
   - Gestión completa de mesas, platos y meseros
   - Visualización de pedidos
   - Administración de usuarios del sistema

3. **Panel de Mesero**
   - Visualización de mesas disponibles
   - Creación y seguimiento de pedidos
   - Gestión de productos por mesa

### Flujo de un Pedido

1. Mesero selecciona una mesa
2. Crea un nuevo pedido
3. Agrega platos del menú
4. Confirma el pedido
5. Pedido se guarda en BD
6. Administrador puede hacer seguimiento

## 🏗️ Arquitectura

La aplicación sigue el patrón **MVC (Model-View-Controller)**:

- **Modelo (Modelo/)**: Gestiona datos, lógica de negocio y acceso a BD
  - DAOs: Abstracción de acceso a datos
  - Entities: Objetos de dominio
  - Security: Gestión de autenticación y cifrado
  
- **Vista (Vista/)**: Interfaz gráfica con Swing
  - Formularios autogenerados con NetBeans
  - Temas personalizados con FlatLaf
  - Pantallas específicas por rol de usuario

- **Controlador (Controlador/)**: Orquesta la comunicación entre Vista y Modelo
  - Maneja eventos de usuario
  - Valida datos
  - Coordina operaciones de negocio

### Base de Datos

Entidades principales y relaciones:

```
Users (id, username, password, role)
    └─ Waiter (id, name, fk_user)
    
Dishes (id, name, price, description)

Tables (id, table_number, status)

Orders (id, fk_table, fk_waiter, date, total_price, status)
    └─ OrderDish (id, fk_order, fk_dish, quantity, price)
```

## 🔑 Componentes Principales

### DAOs (Data Access Objects)
- **UserDAO**: Gestión de usuarios del sistema
- **DishDAO**: Catálogo de platos
- **TableDAO**: Control de mesas
- **OrderDAO**: Registros de pedidos
- **OrderDishDAO**: Detalles de pedidos
- **WaiterDAO**: Información de meseros

### Controllers
- Manejan la lógica de negocio
- Validan datos de entrada
- Coordinan operaciones entre múltiples DAOs

### Security
- Cifrado de contraseñas con BCrypt
- Validación de sesiones
- Control de acceso por roles

## 🤝 Contribuir

Las contribuciones son bienvenidas. Para cambios mayores:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo licencia MIT. Ver `LICENSE` para más detalles.

---

**Autor**: JuanaCa19  
**Última actualización**: 2026-06-16  
**Estado**: En desarrollo
