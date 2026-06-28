package pa.ac.utp.smartroute.ui.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.BusMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerHomeScreen(onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartRoute Transportista", fontWeight = FontWeight.Bold) },
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
                    Text("¡Bienvenido!", fontSize = 12.sp, color = SmartGray)
                    Text(
                        text = SessionManager.getCurrentUserName(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = SmartBlue,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            SmartRouteCard(containerColor = SmartLightBlue.copy(alpha = 0.5f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row {
                            Text("Rol actual: ", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Text("OWNER", fontSize = 11.sp, color = SmartBlue, fontWeight = FontWeight.Bold)
                        }
                        Text("JWT simulado: ${SessionManager.getToken()}", fontSize = 10.sp, color = SmartGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Visual */ },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
            ) {
                Icon(Icons.Default.DirectionsBus, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text("Registrar nuevo autobús", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Mis Autobuses", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = SmartText)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(MockData.buses) { bus ->
                    OwnerBusItemImproved(bus)
                }
                
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Historial básico de recorridos", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    SmartRouteCard {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.History, contentDescription = null, tint = SmartGray, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                HistoryRow("BUS-01 completó recorrido Universidad - Centro")
                                Spacer(modifier = Modifier.height(4.dp))
                                HistoryRow("BUS-02 disponible para asignación")
                            }
                        }
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = onLogout,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SmartRed)
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Cerrar sesión", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun OwnerBusItemImproved(bus: BusMock) {
    SmartRouteCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(SmartLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(24.dp))
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = bus.code, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = SmartText)
                    Box(
                        modifier = Modifier
                            .background(if (bus.status.contains("recorrido") || bus.status == "Disponible") SmartLightGreen else Color(0xFFF3F4F6), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(text = bus.status, fontSize = 10.sp, color = if (bus.status.contains("recorrido") || bus.status == "Disponible") SmartGreen else SmartGray, fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(modifier = Modifier.fillMaxWidth()) {
                    OwnerDetailCol(Icons.Default.Badge, "Placa:", bus.plate, Modifier.weight(1f))
                    OwnerDetailCol(Icons.Default.Person, "Conductor:", bus.driverName, Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OwnerDetailCol(Icons.Default.FiberManualRecord, "Estado:", bus.status, Modifier.weight(1f), iconColor = if(bus.status == "Disponible") SmartBlue else SmartGreen)
                    OwnerDetailCol(Icons.Default.Route, "Ruta:", bus.routeName ?: "No asignada", Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun OwnerDetailCol(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier, iconColor: Color = SmartGray) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(12.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 11.sp, color = SmartGray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = SmartText, maxLines = 1)
    }
}

@Composable
fun HistoryRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(SmartBlue))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 12.sp, color = SmartText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OwnerHomeScreenPreview() {
    SessionManager.login(UserRole.OWNER)
    SmartRouteTheme {
        OwnerHomeScreen(onLogout = {})
    }
}