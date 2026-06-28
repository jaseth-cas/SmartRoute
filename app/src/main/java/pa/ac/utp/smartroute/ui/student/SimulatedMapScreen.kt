package pa.ac.utp.smartroute.ui.student

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulatedMapScreen(
    onNavigateBack: () -> Unit,
    onConsultEta: () -> Unit
) {
    var lastUpdate by remember { mutableStateOf("Hace 1 minuto") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguimiento GPS simulado", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Simulated menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
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
            // Simulated Info Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(SmartLightGreen, RoundedCornerShape(8.dp))
                    .border(1.dp, SmartGreen.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SmartGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Ubicación GPS simulada para demostración académica",
                        fontSize = 11.sp,
                        color = SmartGreen,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Map Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE5E7EB)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("MAPA SIMULADO", fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {
                        // Simulated Path
                        Icon(
                            imageVector = Icons.Default.DirectionsBus, 
                            contentDescription = null, 
                            tint = SmartBlue,
                            modifier = Modifier.size(48.dp).align(Alignment.TopStart)
                        )
                        Icon(
                            imageVector = Icons.Default.Place, 
                            contentDescription = null, 
                            tint = SmartBlue,
                            modifier = Modifier.size(48.dp).align(Alignment.BottomEnd)
                        )
                    }
                }
            }

            // Bus Detail Card
            SmartRouteCard(
                modifier = Modifier.padding(16.dp)
            ) {
                BusDetailItem(Icons.Default.DirectionsBus, "Bus:", "BUS-01")
                BusDetailItem(Icons.Default.Badge, "Placa:", "123456")
                BusDetailItem(Icons.Default.Map, "Ruta:", "Ruta Universidad - Centro")
                BusDetailItem(Icons.Default.Person, "Conductor:", "Carlos Pérez")
                BusDetailItem(Icons.Default.Update, "Última actualización:", lastUpdate)
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Box(
                    modifier = Modifier
                        .background(SmartLightGreen, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SmartGreen, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("GPS simulado: Activo", fontSize = 11.sp, color = SmartGreen, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { lastUpdate = "Recién actualizado" },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, SmartBlue)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = SmartBlue)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Actualizar ubicación", color = SmartBlue, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = onConsultEta,
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SmartLightBlue, contentColor = SmartBlue)
                ) {
                    Icon(Icons.Default.Timer, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Consultar ETA", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun BusDetailItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = SmartGray, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, fontSize = 13.sp, color = SmartGray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SmartText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimulatedMapScreenPreview() {
    SmartRouteTheme {
        SimulatedMapScreen(onNavigateBack = {}, onConsultEta = {})
    }
}