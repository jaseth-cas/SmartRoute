package pa.ac.utp.smartroute.data.model

import pa.ac.utp.smartroute.data.auth.UserRole

data class UserMock(
    val id: Int,
    val name: String,
    val email: String,
    val role: UserRole
)