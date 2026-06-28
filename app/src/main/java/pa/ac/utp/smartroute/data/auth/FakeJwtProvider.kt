package pa.ac.utp.smartroute.data.auth

object FakeJwtProvider {
    fun createSessionForRole(role: UserRole): JwtSession {
        val issuedAt = System.currentTimeMillis()
        val expiresAt = issuedAt + 3600000 // 1 hour

        return when (role) {
            UserRole.STUDENT -> JwtSession(
                token = "fake.jwt.student.token",
                userId = 1,
                name = "Ana Martínez",
                email = "ana.estudiante@universidad.edu",
                role = UserRole.STUDENT,
                issuedAt = issuedAt,
                expiresAt = expiresAt
            )
            UserRole.ADMIN -> JwtSession(
                token = "fake.jwt.admin.token",
                userId = 2,
                name = "Admin SmartRoute",
                email = "admin@smartroute.edu",
                role = UserRole.ADMIN,
                issuedAt = issuedAt,
                expiresAt = expiresAt
            )
            UserRole.DRIVER -> JwtSession(
                token = "fake.jwt.driver.token",
                userId = 3,
                name = "Carlos Pérez",
                email = "carlos.conductor@transporte.edu",
                role = UserRole.DRIVER,
                issuedAt = issuedAt,
                expiresAt = expiresAt
            )
            UserRole.OWNER -> JwtSession(
                token = "fake.jwt.owner.token",
                userId = 4,
                name = "Transporte Universitario Penonomé",
                email = "transportista@buses.edu",
                role = UserRole.OWNER,
                issuedAt = issuedAt,
                expiresAt = expiresAt
            )
        }
    }

    fun decodeFakeJwt(token: String): String {
        return "Simulated Payload for: $token"
    }
}