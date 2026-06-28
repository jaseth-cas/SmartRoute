package pa.ac.utp.smartroute.data.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

object SessionManager {
    private val _currentSession = mutableStateOf<JwtSession?>(null)
    val currentSession: State<JwtSession?> = _currentSession

    fun login(role: UserRole) {
        _currentSession.value = FakeJwtProvider.createSessionForRole(role)
    }

    fun logout() {
        _currentSession.value = null
    }

    fun isLoggedIn(): Boolean = _currentSession.value != null

    fun getCurrentRole(): UserRole? = _currentSession.value?.role

    fun getCurrentUserName(): String = _currentSession.value?.name ?: "Invitado"

    fun getToken(): String? = _currentSession.value?.token
}