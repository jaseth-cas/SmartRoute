package pa.ac.utp.smartroute.ui.student

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun StudentHomeScreen(
    onNavigate: (String) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.DirectionsBus, 
                            contentDescription = null, 
                            tint = SmartBlue,
                            modifier = Modifier.size(24.dp).background(SmartLightBlue, RoundedCornerShape(4.dp)).padding(2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SmartRoute", fontWeight = FontWeight.Bold, color = SmartBlue)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Simulated */ }) {
                        BadgedBox(badge = { Badge { Text("") } }) {
                            Icon(Icons.Default.NotificationsNone, contentDescription = "Alertas")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Map, contentDescription = null) },
                    label = { Text("Rutas") },
                    selected = false,
                    onClick = { onNavigate("routes") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Explore, contentDescription = null) },
                    label = { Text("Mapa") },
                    selected = false,
                    onClick = { onNavigate("simulated_map") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Timer, contentDescription = null) },
                    label = { Text("ETA") },
                    selected = false,
                    onClick = { onNavigate("eta") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.NotificationsNone, contentDescription = null) },
                    label = { Text("Alertas") },
                    selected = false,
                    onClick = { onNavigate("notifications") }
                )
            }
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
                        text = "Hola, ${SessionManager.getCurrentUserName()} 👋",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SmartText
                    )
                    Text("Bienvenida de nuevo a SmartRoute", fontSize = 12.sp, color = SmartGray)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Suggested Route Card with Design Graphic
            SmartRouteCard(containerColor = Color(0xFFF0F7FF)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Map, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ruta sugerida", fontSize = 12.sp, color = SmartBlue, fontWeight = FontWeight.Medium)
                }
                Text("Ruta Universidad - Centro", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 4.dp))
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Próximo bus", fontSize = 11.sp, color = SmartGray)
                        }
                        Text("BUS-01", fontWeight = FontWeight.Bold, color = SmartBlue)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Timer, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("ETA", fontSize = 11.sp, color = SmartGray)
                        }
                        Text("5 minutos", fontWeight = FontWeight.Bold, color = SmartBlue)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Debug Info Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SmartLightGreen.copy(alpha = 0.5f))
                    .border(1.dp, SmartGreen.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = SmartGreen, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row {
                            Text("Rol actual: ", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text(SessionManager.getCurrentRole()?.name ?: "", fontSize = 11.sp, color = SmartGreen, fontWeight = FontWeight.Bold)
                        }
                        Text("JWT simulado: ${SessionManager.getToken()}", fontSize = 10.sp, color = SmartGray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            MenuOption("Consultar rutas", "Explora rutas disponibles", Icons.Default.Map) { onNavigate("routes") }
            MenuOption("Ver paradas", "Consulta paradas cercanas", Icons.Default.Place) { onNavigate("stops") }
            MenuOption("Ver bus en tiempo real", "Sigue la ubicación de los buses", Icons.Default.Explore) { onNavigate("simulated_map") }
            MenuOption("Consultar ETA", "Obtén tiempos estimados de llegada", Icons.Default.Timer) { onNavigate("eta") }
            MenuOption("Notificaciones", "Revisa alertas y avisos importantes", Icons.Default.Notifications) { onNavigate("notifications") }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(contentColor = SmartRed)
            ) {
                Icon(Icons.Default.Logout, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar sesión")
            }
        }
    }
}

@Composable
fun MenuOption(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit) {
    SmartRouteCard(
        modifier = Modifier.padding(vertical = 6.dp).clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon, 
                contentDescription = null, 
                tint = Color.White,
                modifier = Modifier
                    .size(36.dp)
                    .background(SmartBlue, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SmartText)
                Text(subtitle, fontSize = 12.sp, color = SmartGray)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentHomeScreenPreview() {
    SessionManager.login(UserRole.STUDENT)
    SmartRouteTheme {
        StudentHomeScreen(onNavigate = {}, onLogout = {})
    }
}