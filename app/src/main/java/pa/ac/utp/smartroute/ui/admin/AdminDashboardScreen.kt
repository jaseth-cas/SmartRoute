package pa.ac.utp.smartroute.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.data.auth.SessionManager
import pa.ac.utp.smartroute.data.auth.UserRole
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigate: (String) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartRoute Admin", fontWeight = FontWeight.Bold, color = SmartBlue) },
                actions = {
                    IconButton(onClick = { /* Simulated alerts */ }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SmartBackground)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(SmartLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = SmartBlue)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Bienvenido,",
                        fontSize = 14.sp,
                        color = SmartGray
                    )
                    Text(
                        text = SessionManager.getCurrentUserName(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SmartBlue
                    )
                    Text("Aquí tienes un resumen general del sistema.", fontSize = 11.sp, color = SmartGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Admin Role Card
            SmartRouteCard(containerColor = SmartLightBlue.copy(alpha = 0.5f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row {
                            Text("Rol actual: ", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            Text("ADMIN", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SmartBlue)
                        }
                        Text("JWT simulado: ${SessionManager.getToken()}", fontSize = 10.sp, color = SmartGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Metrics Grid
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AdminMetricCard(Icons.Default.Group, "12", "Usuarios registrados", Modifier.weight(1f))
                AdminMetricCard(Icons.Default.Map, "2", "Rutas activas", Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AdminMetricCard(Icons.Default.DirectionsBus, "2", "Autobuses registrados", Modifier.weight(1f))
                AdminMetricCard(Icons.Default.SettingsInputAntenna, "1", "GPS simulados activos", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation Options
            AdminNavOption("Gestionar rutas", "Crear, editar y eliminar rutas", Icons.Default.Map) { onNavigate("manage_routes") }
            AdminNavOption("Gestionar paradas", "Administrar paradas del sistema", Icons.Default.Place) { onNavigate("manage_stops") }
            AdminNavOption("Gestionar autobuses", "Administrar autobuses registrados", Icons.Default.DirectionsBus) { onNavigate("manage_buses") }

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
            ) {
                Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Cerrar sesión", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AdminMetricCard(icon: ImageVector, value: String, label: String, modifier: Modifier = Modifier) {
    SmartRouteCard(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(SmartLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = SmartText)
            Text(text = label, fontSize = 11.sp, color = SmartGray, fontWeight = FontWeight.Medium, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
fun AdminNavOption(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit) {
    SmartRouteCard(
        modifier = Modifier.padding(vertical = 4.dp).clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(SmartLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SmartText)
                Text(subtitle, fontSize = 11.sp, color = SmartGray)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminDashboardScreenPreview() {
    SessionManager.login(UserRole.ADMIN)
    SmartRouteTheme {
        AdminDashboardScreen(onNavigate = {}, onLogout = {})
    }
}