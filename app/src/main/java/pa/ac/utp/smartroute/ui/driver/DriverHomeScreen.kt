package pa.ac.utp.smartroute.ui.driver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import pa.ac.utp.smartroute.ui.components.StatusChip
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverHomeScreen(onLogout: () -> Unit) {
    var routeStatus by remember { mutableStateOf("En recorrido") }
    var gpsActive by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartRoute Conductor", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* Alerts */ }) {
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
                    modifier = Modifier.size(56.dp).clip(CircleShape).background(SmartLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Hola, ${SessionManager.getCurrentUserName()}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SmartBlue
                    )
                    Text("¡Buen día! Conduce seguro.", fontSize = 12.sp, color = SmartGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Driver Role Card
            SmartRouteCard(containerColor = SmartLightBlue.copy(alpha = 0.5f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row {
                            Text("Rol actual: ", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            Text("DRIVER", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SmartBlue)
                        }
                        Text("JWT simulado: ${SessionManager.getToken()}", fontSize = 10.sp, color = SmartGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Main Trip Info Card
            SmartRouteCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = SmartBlue)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Información del recorrido", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                DriverDetailRow(Icons.Default.DirectionsBus, "Bus asignado:", "BUS-01")
                DriverDetailRow(Icons.Default.Badge, "Placa:", "123456")
                DriverDetailRow(Icons.Default.AltRoute, "Ruta asignada:", "Ruta Universidad - Centro")
                
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = SmartBorder, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.StackedLineChart, contentDescription = null, tint = SmartGray, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Estado del recorrido:", fontSize = 13.sp, color = SmartGray)
                    }
                    StatusChip(text = routeStatus)
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = SmartGray, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("GPS simulado:", fontSize = 13.sp, color = SmartGray)
                    }
                    StatusChip(text = if (gpsActive) "Activo" else "Inactivo")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Actions
            Button(
                onClick = { gpsActive = !gpsActive },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
            ) {
                Icon(Icons.Default.SettingsInputAntenna, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text(if (gpsActive) "Desactivar GPS simulado" else "Activar GPS simulado", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Button(
                onClick = { routeStatus = "Finalizado"; gpsActive = false },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartRed)
            ) {
                Icon(Icons.Default.Flag, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Finalizar ruta", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Update, contentDescription = null, tint = SmartGray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Última ubicación simulada: Hace 1 minuto", fontSize = 12.sp, color = SmartGray)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, SmartBorder)
            ) {
                Icon(Icons.Default.Logout, contentDescription = null, modifier = Modifier.size(18.dp), tint = SmartBlue)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Cerrar sesión", color = SmartBlue, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun DriverDetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = SmartGray, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, fontSize = 13.sp, color = SmartGray, modifier = Modifier.weight(1f))
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SmartText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DriverHomeScreenPreview() {
    SessionManager.login(UserRole.DRIVER)
    SmartRouteTheme {
        DriverHomeScreen(onLogout = {})
    }
}