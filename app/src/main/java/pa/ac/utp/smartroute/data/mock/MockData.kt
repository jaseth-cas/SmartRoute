package pa.ac.utp.smartroute.data.mock

import pa.ac.utp.smartroute.data.auth.UserRole
import pa.ac.utp.smartroute.data.model.*

object MockData {
    val users = listOf(
        UserMock(1, "Ana Martínez", "ana.estudiante@universidad.edu", UserRole.STUDENT),
        UserMock(2, "Admin SmartRoute", "admin@smartroute.edu", UserRole.ADMIN),
        UserMock(3, "Carlos Pérez", "carlos.conductor@transporte.edu", UserRole.DRIVER),
        UserMock(4, "Transporte Universitario Penonomé", "transportista@buses.edu", UserRole.OWNER)
    )

    val routes = listOf(
        RouteMock(1, "Ruta Universidad - Centro", "Universidad", "Centro", "Activa", 5),
        RouteMock(2, "Ruta Universidad - Terminal", "Universidad", "Terminal", "Activa", 8),
        RouteMock(3, "Ruta Universidad - Penonomé Norte", "Universidad", "Penonomé Norte", "Inactiva", null)
    )

    val stops = listOf(
        StopMock(1, "Entrada principal universidad", "Frente al acceso principal", 8.5167, -80.3600, 1),
        StopMock(2, "Terminal de buses", "Plataforma principal", 8.5181, -80.3572, 1),
        StopMock(3, "Parque central", "Frente al parque", 8.5140, -80.3580, 1),
        StopMock(4, "Supermercado cercano", "Entrada lateral", 8.5200, -80.3550, 2),
        StopMock(5, "Penonomé Norte", "Sector norte de Penonomé", 8.5300, -80.3500, 3)
    )

    val buses = listOf(
        BusMock(1, "BUS-01", "123456", "Toyota Coaster", 30, "En recorrido", "Carlos Pérez", "Transporte Universitario Penonomé", "Ruta Universidad - Centro", true),
        BusMock(2, "BUS-02", "654321", "Hyundai County", 28, "Disponible", "Luis Gómez", "Transporte Universitario Penonomé", null, false)
    )

    val notifications = listOf(
        NotificationMock(1, "Bus próximo", "El bus de la Ruta 1 está a 5 minutos de tu parada", "Hace 1 min", false),
        NotificationMock(2, "Ruta iniciada", "La ruta ha iniciado", "Hace 10 min", false),
        NotificationMock(3, "Recorrido finalizado", "El bus ha finalizado recorrido", "Hace 1 hora", true),
        NotificationMock(4, "Ubicación actualizada", "Nueva actualización de ubicación disponible", "Hace 15 min", true)
    )
}