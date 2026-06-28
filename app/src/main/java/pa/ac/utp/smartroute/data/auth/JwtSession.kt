package pa.ac.utp.smartroute.data.auth

data class JwtSession(
    val token: String,
    val userId: Int,
    val name: String,
    val email: String,
    val role: UserRole,
    val issuedAt: Long,
    val expiresAt: Long
)