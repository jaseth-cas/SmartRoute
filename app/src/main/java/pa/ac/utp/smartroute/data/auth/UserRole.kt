package pa.ac.utp.smartroute.data.auth

enum class UserRole(val displayName: String) {
    STUDENT("Estudiante"),
    ADMIN("Administrador"),
    DRIVER("Conductor"),
    OWNER("Transportista")
}