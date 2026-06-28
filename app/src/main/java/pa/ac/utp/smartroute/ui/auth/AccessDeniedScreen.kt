package pa.ac.utp.smartroute.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import pa.ac.utp.smartroute.data.auth.SessionManager
import pa.ac.utp.smartroute.ui.components.PrimaryButton
import pa.ac.utp.smartroute.ui.theme.SmartRouteTheme

@Composable
fun AccessDeniedScreen(
    onNavigateHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Acceso no autorizado",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Tu rol no tiene permisos para acceder a este módulo.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            PrimaryButton(
                text = "Volver al inicio",
                onClick = onNavigateHome
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(onClick = {
                SessionManager.logout()
                onNavigateToLogin()
            }) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccessDeniedScreenPreview() {
    SmartRouteTheme {
        AccessDeniedScreen(onNavigateHome = {}, onNavigateToLogin = {})
    }
}