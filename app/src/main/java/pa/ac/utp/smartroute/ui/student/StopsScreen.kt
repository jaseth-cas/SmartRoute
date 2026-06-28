package pa.ac.utp.smartroute.ui.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.StopMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopsScreen(
    routeId: Int,
    onNavigateBack: () -> Unit,
    onSelectStop: (Int) -> Unit
) {
    val stops = MockData.stops.filter { it.routeId == routeId }
    val routeName = MockData.routes.find { it.id == routeId }?.name ?: "Ruta"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text("Paradas simuladas", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(routeName, style = MaterialTheme.typography.bodySmall, color = SmartGray)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(SmartBackground),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(stops) { stop ->
                StopItem(stop, onSelectStop)
            }
        }
    }
}

@Composable
fun StopItem(stop: StopMock, onSelectStop: (Int) -> Unit) {
    SmartRouteCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(SmartLightBlue, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Place, contentDescription = null, tint = SmartBlue)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stop.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = SmartText)
                Text(text = stop.reference, style = MaterialTheme.typography.bodySmall, color = SmartGray)
                Text(
                    text = "Lat: ${stop.latitude}, Lon: ${stop.longitude}",
                    style = MaterialTheme.typography.labelSmall,
                    color = SmartBlue.copy(alpha = 0.7f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { onSelectStop(stop.id) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SmartBlue)
        ) {
            Text("Seleccionar parada", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StopsScreenPreview() {
    SmartRouteTheme {
        StopsScreen(routeId = 1, onNavigateBack = {}, onSelectStop = {})
    }
}