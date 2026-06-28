package pa.ac.utp.smartroute.ui.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.RouteMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.components.StatusChip
import pa.ac.utp.smartroute.ui.theme.SmartBackground
import pa.ac.utp.smartroute.ui.theme.SmartRouteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesScreen(
    onNavigateBack: () -> Unit,
    onViewStops: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rutas disponibles", fontWeight = FontWeight.Bold) },
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
            items(MockData.routes) { route ->
                RouteItem(route, onViewStops)
            }
        }
    }
}

@Composable
fun RouteItem(route: RouteMock, onViewStops: (Int) -> Unit) {
    SmartRouteCard {
        Text(text = route.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
        Text(text = "${route.origin} → ${route.destination}", style = MaterialTheme.typography.bodyMedium)
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatusChip(text = route.status)
            if (route.etaMinutes != null) {
                StatusChip(text = "ETA: ${route.etaMinutes} min")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { onViewStops(route.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver paradas")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoutesScreenPreview() {
    SmartRouteTheme {
        RoutesScreen(onNavigateBack = {}, onViewStops = {})
    }
}