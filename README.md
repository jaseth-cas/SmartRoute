# SmartRoute

**SmartRoute** es una aplicación Android académica orientada al monitoreo inteligente de buses universitarios mediante GPS simulado.  
El proyecto busca representar visualmente cómo funcionaría un sistema de transporte universitario con rutas, paradas, buses, ETA, notificaciones y control de usuarios por rol.

Esta primera versión corresponde a un **maquetado/prototipo funcional** desarrollado en **Kotlin** con **Jetpack Compose**, utilizando únicamente datos simulados/locales.

---

## Contexto del proyecto

SmartRoute nace como una propuesta para mejorar la experiencia de estudiantes universitarios al momento de consultar el estado y ubicación de los buses institucionales.

El sistema permite que los estudiantes puedan consultar rutas disponibles, seleccionar paradas, visualizar la ubicación simulada de un bus, revisar el tiempo estimado de llegada y recibir notificaciones simuladas.

Además, el prototipo contempla módulos diferenciados para otros actores del sistema:

- Estudiante
- Administrador
- Conductor
- Transportista o dueño del bus

En esta etapa, el sistema **no utiliza servicios reales externos**. Todo el comportamiento se simula dentro de la aplicación.

---

## Estado actual del proyecto

La versión actual es un **prototipo visual funcional**.

Actualmente el proyecto incluye:

- Pantallas principales de la aplicación
- Navegación por roles
- Autenticación simulada tipo JWT local
- Datos simulados de usuarios, rutas, paradas, autobuses y notificaciones
- Interfaz moderna usando Jetpack Compose y Material 3
- Control visual de acceso según el rol del usuario

---

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- Datos locales simulados

---

## Tecnologías no utilizadas en esta versión

Por tratarse de una primera versión académica, esta app **no utiliza todavía**:

- Base de datos real
- PostgreSQL
- PostGIS
- API backend
- Firebase
- GPS real
- Google Maps
- Servicios externos
- Internet
- Autenticación real
- JWT firmado real

Todo el flujo se maneja localmente para fines de demostración.

---

## Objetivo de esta versión

El objetivo principal de esta primera versión es construir una base visual y funcional que permita demostrar el funcionamiento general de SmartRoute.

Esta versión permite validar:

- Diseño de interfaz
- Flujo de navegación
- Separación de módulos por rol
- Simulación de autenticación
- Visualización de rutas y paradas
- Simulación de seguimiento GPS
- Consulta de ETA simulado
- Gestión administrativa simulada

---

## Actores del sistema

### 1. Estudiante

El estudiante puede:

- Iniciar sesión de forma simulada
- Consultar rutas disponibles
- Ver paradas de una ruta
- Seleccionar una parada
- Ver el bus en un mapa simulado
- Consultar ETA simulado
- Ver notificaciones simuladas

### 2. Administrador

El administrador puede:

- Ver un dashboard general
- Consultar métricas simuladas del sistema
- Gestionar rutas simuladas
- Gestionar paradas simuladas
- Gestionar autobuses simulados

### 3. Conductor

El conductor puede:

- Ver su bus asignado
- Ver su ruta asignada
- Activar GPS simulado
- Finalizar ruta
- Consultar el estado del recorrido

### 4. Transportista

El transportista o dueño del bus puede:

- Consultar autobuses registrados
- Registrar visualmente nuevos autobuses
- Ver conductor asignado
- Ver ruta asignada
- Consultar historial básico de recorridos simulados

---

## Autenticación simulada tipo JWT

SmartRoute implementa una autenticación simulada basada en un token local tipo JWT.

Esta autenticación **no es real** y no se conecta con ningún servidor. Su propósito es representar cómo funcionaría el control de sesión y permisos en una versión futura.

Al iniciar sesión, el usuario selecciona un rol:

- Estudiante
- Administrador
- Conductor
- Transportista

Luego la aplicación genera una sesión simulada con datos como:

```kotlin
data class JwtSession(
    val token: String,
    val userId: Int,
    val name: String,
    val email: String,
    val role: UserRole,
    val issuedAt: Long,
    val expiresAt: Long
)
