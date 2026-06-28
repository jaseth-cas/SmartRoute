package pa.ac.utp.smartroute.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.BusMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageBusesScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de autobuses simulados", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
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
        ) {
            Text(
                "Administra los autobuses registrados del sistema.",
                fontSize = 12.sp,
                color = SmartGray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Button(
                onClick = { /* Visual */ },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Registrar autobús", fontWeight = FontWeight.Bold)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(MockData.buses) { bus ->
                    ManageBusItemImproved(bus)
                }
            }
        }
    }
}

@Composable
fun ManageBusItemImproved(bus: BusMock) {
    SmartRouteCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(SmartLightBlue, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.DirectionsBus, contentDescription = null, tint = SmartBlue)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = bus.code, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = SmartText)
                    Spacer(modifier = Modifier.width(8.dp))
                    BusStatusBadge(bus.status)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Placa: ", fontSize = 12.sp, color = SmartGray)
                    Text(text = bus.plate, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Modelo: ", fontSize = 12.sp, color = SmartGray)
                    Text(text = bus.model, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BusActionInfo(Icons.Default.Person, "Conductor:", bus.driverName)
            BusActionInfo(Icons.Default.Business, "Transportista:", bus.ownerName)
            BusActionInfo(Icons.Default.AltRoute, "Ruta:", bus.routeName ?: "No asignada")
            BusActionInfo(Icons.Default.SettingsInputAntenna, "GPS simulado:", if (bus.simulatedGpsActive) "En línea" else "Fuera de línea", if (bus.simulatedGpsActive) SmartGreen else SmartGray)
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { /* Visual */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SmartBorder)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp), tint = SmartBlue)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Editar", fontSize = 12.sp, color = SmartBlue)
            }
            
            Button(
                onClick = { /* Visual */ },
                modifier = Modifier.weight(1.5f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SmartLightBlue, contentColor = SmartBlue)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Asignar conductor", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun BusActionInfo(icon: ImageVector, label: String, value: String, valueColor: Color = SmartText) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = SmartGray, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = 12.sp, color = SmartGray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = valueColor)
    }
}

@Composable
fun BusStatusBadge(status: String) {
    val isOnline = status == "En recorrido" || status == "Activo" || status == "En servicio"
    val color = if (isOnline) SmartGreen else SmartGray
    val bgColor = if (isOnline) SmartLightGreen else Color(0xFFF3F4F6)
    
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text = status, fontSize = 10.sp, color = color, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageBusesScreenPreview() {
    SmartRouteTheme {
        ManageBusesScreen(onNavigateBack = {})
    }
}