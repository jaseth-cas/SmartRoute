package pa.ac.utp.smartroute.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import pa.ac.utp.smartroute.data.mock.MockData
import pa.ac.utp.smartroute.data.model.StopMock
import pa.ac.utp.smartroute.ui.components.SmartRouteCard
import pa.ac.utp.smartroute.ui.theme.SmartRouteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageStopsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de paradas") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Visual */ }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar parada")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(MockData.stops) { stop ->
                ManageStopItem(stop)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageStopsScreenPreview() {
    SmartRouteTheme {
        ManageStopsScreen(onNavigateBack = {})
    }
}

@Composable
fun ManageStopItem(stop: StopMock) {
    SmartRouteCard(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                val routeName = MockData.routes.find { it.id == stop.routeId }?.name ?: "Sin ruta"
                Text(text = stop.name, fontWeight = FontWeight.Bold)
                Text(text = "Referencia: ${stop.reference}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Ruta: $routeName", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = { /* Visual */ }) {
                Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = { /* Visual */ }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}