# DevTaskFlow
Sistema de Gestión de Tareas para Desarrolladores

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-6DB33F?logo=springboot)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-005F0F?logo=thymeleaf)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql)
![Java](https://img.shields.io/badge/Java-17-007396?logo=java)
![License](https://img.shields.io/badge/License-MIT-green)

## 📋 Descripción

**DevTaskFlow** es una aplicación web completa para la gestión de tareas orientada a equipos de desarrollo. Permite asignar tareas a desarrolladores, hacer seguimiento de su estado, agregar comentarios y gestionar usuarios con diferentes roles (ADMIN y DEVELOPER).

Esta aplicación fue desarrollada como proyecto de portafolio para demostrar habilidades en **Spring Boot**, **Spring Security**, **Thymeleaf**, **JPA/Hibernate** y **MySQL**.

---

## ✨ Características

### 🔐 Autenticación y Autorización
- Registro de nuevos usuarios (rol DEVELOPER por defecto)
- Login seguro con Spring Security
- Control de acceso basado en roles (ADMIN / DEVELOPER)

### 👥 Gestión de Usuarios (solo ADMIN)
- Listar todos los usuarios registrados
- Crear nuevos usuarios (ADMIN o DEVELOPER)
- Eliminar usuarios

### 📝 Gestión de Tareas
- Crear, editar y eliminar tareas
- Asignar tareas a desarrolladores específicos
- Cambiar estado de tareas: **TODO** → **IN_PROGRESS** → **DONE**
- Establecer prioridad: **BAJA**, **MEDIA**, **ALTA**
- Fecha de vencimiento

### 💬 Sistema de Comentarios
- Agregar comentarios a cualquier tarea
- Visualizar historial de comentarios ordenado cronológicamente

### 📊 Dashboard
- Resumen de tareas por estado (TODO, IN_PROGRESS, DONE)
- Listado de tareas asignadas al usuario actual
- Estadísticas visuales

### 🎨 Interfaz
- Diseño responsive y moderno con CSS personalizado
- Navegación intuitiva según el rol del usuario

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Spring Boot | 3.1.5 | Framework principal |
| Spring Security | 3.1.5 | Autenticación y autorización |
| Spring Data JPA | 3.1.5 | Persistencia de datos |
| Thymeleaf | 3.1.2 | Motor de plantillas |
| MySQL | 8.0 | Base de datos |
| Hibernate | 6.2.13 | ORM |
| Lombok | 1.18.30 | Reducción de código boilerplate |
| Maven | 3.8+ | Gestión de dependencias |

---

## 🏗️ Estructura del Proyecto

```md
DevTaskFlow/
├── src/main/java/com/DevTaskFlow/
│   ├── config/                 # Configuración de Spring Security
│   ├── controller/             # Controladores MVC
│   ├── model/                  # Entidades JPA
│   ├── repository/             # Repositorios JPA
│   ├── service/                # Lógica de negocio
│   └── dto/                    # Objetos de transferencia de datos
├── src/main/resources/
│   ├── static/css/             # Estilos CSS
│   ├── templates/              # Plantillas Thymeleaf
│   │   ├── tasks/              # Vistas de tareas
│   │   ├── users/              # Vistas de usuarios
│   │   ├── dashboard.html
│   │   ├── login.html
│   │   └── register.html
│   └── application.properties
└── pom.xml
```

---

## 🚀 Instalación y Ejecución

### Requisitos Previos

- Java 17 o superior
- MySQL 8.0
- Maven 3.8+
- Git

### Pasos para ejecutar localmente

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/DevTaskFlow.git
cd DevTaskFlow

Crear la base de datos en MySQL
CREATE DATABASE DevTaskFlow;

Configurar la conexión a la base de datos
Edita el archivo src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/DevTaskFlow?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña

### mvn clean compile
### mvn spring-boot:run
Abre tu navegador en: http://localhost:8081

### Roles y Permisos
Rol	Permisos
ADMIN	CRUD completo de usuarios y tareas, asignar tareas, ver todas las tareas
DEVELOPER	Ver solo sus tareas asignadas, cambiar estado, agregar comentarios

### Primer Usuario ADMIN

Regístrate como usuario normal en /register

Conéctate a MySQL y actualiza el rol:

USE DevTaskFlow;
UPDATE users SET role = 'ADMIN' WHERE email = 'tu_email@ejemplo.com';

### Flujo de Trabajo
Administrador crea usuarios desarrolladores

Administrador crea tareas y las asigna a desarrolladores

Desarrollador ve sus tareas asignadas en el dashboard

Desarrollador cambia el estado de la tarea (TODO → IN_PROGRESS → DONE)

Cualquier usuario puede agregar comentarios a las tareas

Administrador puede editar/eliminar cualquier tarea o usuario

### Pruebas
Credenciales de prueba (después de configurar)
Email	Contraseña	Rol
admin@example.com	admin123	ADMIN
dev1@example.com	dev123	DEVELOPER
dev2@example.com	dev123	DEVELOPER

### Despliegue
Generar archivo JAR
mvn clean package

### Ejecutar JAR
java -jar target/DevTaskFlow-1.0.0.jar

### Desplegar en la nube (sugerencias)
### Railway - Fácil despliegue con MySQL integrado

### Render - Soporte para Spring Boot gratuito

### AWS Elastic Beanstalk - Escalabilidad profesional

### Heroku - Opción clásica (requiere configurar JAR)






