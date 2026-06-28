package pa.ac.utp.smartroute.ui.student

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.ui.components.PrimaryButton
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EtaScreen(
    onNavigateBack: () -> Unit,
    onViewMap: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ETA simulado", fontWeight = FontWeight.Bold) },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Graphic
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .border(1.dp, SmartBorder, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.DirectionsBus, 
                    contentDescription = null, 
                    modifier = Modifier.size(80.dp),
                    tint = SmartBlue
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Route Selection Simulation
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Ruta", fontSize = 12.sp, color = SmartGray, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, SmartBorder, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Map, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Ruta Universidad - Centro", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Stop Selection Simulation
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Parada", fontSize = 12.sp, color = SmartGray, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, SmartBorder, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AccountBalance, contentDescription = null, tint = SmartBlue, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Entrada principal universidad", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Main ETA Display
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("ETA estimado", fontSize = 12.sp, color = SmartGray)
                Text(
                    text = "5 minutos",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SmartBlue
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Details Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SmartRouteCard(modifier = Modifier.weight(1f)) {
                    Text("Distancia restante", fontSize = 10.sp, color = SmartGray)
                    Text("1.2 km", fontWeight = FontWeight.Bold, color = SmartBlue)
                }
                SmartRouteCard(modifier = Modifier.weight(1f)) {
                    Text("Estado del bus", fontSize = 10.sp, color = SmartGray)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DirectionsBus, contentDescription = null, modifier = Modifier.size(14.dp), tint = SmartBlue)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("En camino", fontWeight = FontWeight.Bold, color = SmartBlue)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Alert Info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SmartLightBlue, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = SmartBlue)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "El bus llegará aproximadamente en 5 minutos",
                    fontSize = 13.sp,
                    color = SmartBlue,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            PrimaryButton(
                text = "Ver bus en mapa",
                icon = Icons.Default.Map,
                onClick = onViewMap
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EtaScreenPreview() {
    SmartRouteTheme {
        EtaScreen(onNavigateBack = {}, onViewMap = {})
    }
}