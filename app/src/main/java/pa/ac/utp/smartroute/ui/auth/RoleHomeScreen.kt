package pa.ac.utp.smartroute.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import pa.ac.utp.smartroute.data.auth.SessionManager
import pa.ac.utp.smartroute.data.auth.UserRole
import pa.ac.utp.smartroute.ui.components.PrimaryButton
import pa.ac.utp.smartroute.ui.theme.SmartRouteTheme

@Composable
fun RoleHomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRoleHome: (UserRole) -> Unit
) {
    val session = SessionManager.currentSession.value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (session == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No hay sesión activa", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(text = "Ir al Login", onClick = onNavigateToLogin)
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("¡Bienvenido!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(session.name, fontSize = 20.sp)
                Text("Rol: ${session.role.displayName}", fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("JWT Simulado:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text(session.token, fontSize = 10.sp)
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                PrimaryButton(
                    text = "Entrar al Panel",
                    onClick = { onNavigateToRoleHome(session.role) }
                )
                
                TextButton(onClick = {
                    SessionManager.logout()
                    onNavigateToLogin()
                }) {
                    Text("Cerrar sesión")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoleHomeScreenPreview() {
    SessionManager.login(UserRole.STUDENT)
    SmartRouteTheme {
        RoleHomeScreen(onNavigateToLogin = {}, onNavigateToRoleHome = {})
    }
}